package aniela.remanent.pozycje;

import aniela.remanent.position.abstraction.Position;
import aniela.remanent.position.brutto.PozycjaDoRaportuBrutto;
import aniela.remanent.position.netto.PozycjaDoRaportuNetto;
import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;
import aniela.remanent.pozycje.helperyZapytanDoBazy.expression.evaluator.ExpressionEvaluator;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BazaService {
    private final static Logger LOGGER = Logger.getLogger(BazaService.class);
    private static final String SPACE = " ";
    private static final int iloscPozycjiNaStronie = 55;

    public PozycjaBazy dodajIloscDoPozycjiDoBazy(Session sesja, int numerPozycji, double ilosc) {
        String hqlUpdatePozycja = "UPDATE PozycjaBazy AS P SET P.ilosc = :ilosc WHERE P.id = :numerPozycji";
        List<PozycjaBazy> pozycje = new ArrayList<>();
        PozycjaBazy pozycjaBazyIn;
        LOGGER.info("dodaj do pozycji");
        pozycje = sesja.createQuery("FROM PozycjaBazy AS P WHERE P.id = :numerPozycji ")
                .setParameter("numerPozycji", numerPozycji).list();
        pozycjaBazyIn = pozycje.get(0);
        System.out.println("ilosc z weba == " + ilosc);
        System.out.println("ilosc z bazy == " + pozycjaBazyIn.getIlosc());
        double zmianaIlosci = pozycjaBazyIn.getIlosc() + ilosc;

        double zmianaIlosciRound = Math.round(zmianaIlosci * 100) / 100.0d; // do dwoch miejsc po przecinku
        System.out.println("zmianaKoncowa == " + zmianaIlosciRound);

        int wynik = sesja.createQuery(hqlUpdatePozycja).
                setParameter("ilosc", zmianaIlosciRound).setParameter("numerPozycji", numerPozycji).executeUpdate();
        System.out.println(wynik);

        pozycje = sesja.createQuery("FROM PozycjaBazy AS P WHERE P.id = :numerPozycji ")
                .setParameter("numerPozycji", numerPozycji).list();
        LOGGER.info("pobrano pozycji: " + pozycje.size());
        return pozycje.get(0);

    }

    public List<PozycjaBazy> advancedSearch(Session session,
                                            String nazwaTowaru,
                                            String uzytkownik,
                                            double cenaNetto,
                                            int radioNetto,
                                            double cenaBrutto,
                                            int radioBrutto,
                                            double ilosc,
                                            int radioIlosc

    ) {

        LOGGER.info("advancedSearch motoda USER param ======= " + uzytkownik);
        CriteriaQuery<PozycjaBazy> criteriaQuery = new ExpressionEvaluator(session.getCriteriaBuilder()).
                withTowar(nazwaTowaru).
                withUzytkownik(uzytkownik).
                withCenaNetto(radioNetto, cenaNetto).
                withCenaBrutto(radioBrutto, cenaBrutto).
                withIlosc(radioIlosc, ilosc).
                evaluate();

        List<PozycjaBazy> result = session.createQuery(criteriaQuery).getResultList();
        LOGGER.info("Advanced search result size is " + result.size());
        return result;
    }


    public int dodajPozycjeDoBazy(Session sesja, PozycjaBazy pozycja) {
        int numer = 0;
        LOGGER.info("dodaj pozycje: " + pozycja);

        try {
            sesja.save(pozycja);
            LOGGER.info("wpis dodany");
            String hql = utworzHQLDoPobraniaNumeruPozycjiH(pozycja);
            List<Integer> lista = sesja.createQuery(hql).list();
            numer = lista.get(0);
            LOGGER.info("zwrocono numer pozycji z bazy= " + numer);

        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {
            sesja.close();
        }
        return numer;
    }

    public List<PozycjaBazy> listaZnalezionychPozycjiWBazie(Session sesja, String pobranaNazwa, String radioValue) {
        List<PozycjaBazy> pozycje = new ArrayList<>();
        String hqlSzukajTowar = "FROM PozycjaBazy AS P WHERE P.nazwa_towaru LIKE CONCAT('%', :pobranaNazwa , '%')";
        String hqlSzukajTowarPoUserze = "FROM PozycjaBazy AS P WHERE P.uzytkownik LIKE CONCAT('%', :pobranaNazwa , '%')";
        LOGGER.info("szukaj pozycji");
        try {
            if (radioValue.equalsIgnoreCase("nazwa")) {
                pozycje = sesja.createQuery(hqlSzukajTowar).setParameter("pobranaNazwa", pobranaNazwa).list();
            } else {
                pozycje = sesja.createQuery(hqlSzukajTowarPoUserze).setParameter("pobranaNazwa", pobranaNazwa).list();
                pozycje = pozycje.stream().skip(Math.max(0, pozycje.size() - 100)).collect(Collectors.toList());
            }
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {
            sesja.close();
        }
        LOGGER.info("pobrano pozycji: " + pozycje.size());
        return pozycje;
    }

    public PozycjaBazy pobierzPozycjeDoZmien(Session sesja, int numer) {
        PozycjaBazy pozycja = null;
        pozycja = sesja.get(PozycjaBazy.class, numer);
        LOGGER.info("Znaleziono pozycje do zmien: " + pozycja);
        return pozycja;
    }

    public int zmienPozycjeWBazie(Session sesja, PozycjaBazy pozycja) {
        LOGGER.info("Pozycja do zmiany: " + pozycja);
        try {
            sesja.update(pozycja);

        } catch (HibernateException e) {
            LOGGER.info("Exception in Hibernate " + e);
        } finally {
            sesja.close();
        }
        LOGGER.info("Zmieniono pozycje na: " + pozycja);
        return pozycja.getId();
    }

    public int usunPozycjeZBazy(Session sesja, int numerDoUsunieciaZBazy) {
        //Session sesja = entityManager.unwrap(Session.class);
        int numer = 0;
        PozycjaBazy pozycja = sesja.get(PozycjaBazy.class, numerDoUsunieciaZBazy);
        sesja.delete(pozycja);
        numer = numerDoUsunieciaZBazy;
        LOGGER.info("NumerPozycji usunietej z bazy : " + numer);
        return numer;
    }

    public int obliczIloscPozycji(Session sesja) {
        long iloscPozycji = 0;
        iloscPozycji = (long) sesja.createQuery("SELECT count(*) FROM PozycjaBazy").list().get(0);
        LOGGER.info("Zwrocona ilosc pozycji : " + iloscPozycji);
        return (int) iloscPozycji;
    }

    public List<Position> przygotujPozycjeDoRaportuNetto(Session sesja) {
        List<Position> pozycjeRaportu = new ArrayList<>();
        List<PozycjaBazy> pozycjeBazy = sesja.createQuery("FROM PozycjaBazy ORDER BY id ASC").list();
        for (PozycjaBazy pozycjaBazy : pozycjeBazy) {
            PozycjaDoRaportuNetto pozycjaRaport = new PozycjaDoRaportuNetto();
            pozycjaRaport.setCenaNetto(pozycjaBazy.getCena_netto());
            pozycjaRaport.setIlosc(pozycjaBazy.getIlosc());
            pozycjaRaport.setJednostka(pozycjaBazy.getJednostka());
            pozycjaRaport.setNazwaTowaru(pozycjaBazy.getNazwa_towaru());
            pozycjaRaport.setPozycjaWRaporcie(pozycjaBazy.getId());
            pozycjaRaport.setSuma(pozycjaBazy.getCena_netto(), pozycjaBazy.getIlosc());
            pozycjeRaportu.add(pozycjaRaport);
        }
        LOGGER.info("Ilosc pozycji w liscie " + pozycjeRaportu.size());
        return pozycjeRaportu;
    }

    public List<PozycjaBazy> getAllPozycjeBazy(Session sesja) {
        List<PozycjaBazy> pozycjeBazy = sesja.createQuery("FROM PozycjaBazy ORDER BY id ASC").list();
        return pozycjeBazy;
    }

    public List<Position> przygotujPozycjeDoRaportuBrutto(Session sesja) {
        List<Position> pozycjeRaportu = new ArrayList<>();
        List<PozycjaBazy> pozycjeBazy = new ArrayList<>();
        pozycjeBazy.addAll(sesja.createQuery("FROM PozycjaBazy ORDER BY nazwa_towaru ASC").list());
        for (PozycjaBazy pozycjaBazy : pozycjeBazy) {
            PozycjaDoRaportuBrutto pozycjaRaport = new PozycjaDoRaportuBrutto();
            pozycjaRaport.setCenaNetto(pozycjaBazy.getCena_netto());
            pozycjaRaport.setIlosc(pozycjaBazy.getIlosc());
            pozycjaRaport.setJednostka(pozycjaBazy.getJednostka());
            pozycjaRaport.setNazwaTowaru(pozycjaBazy.getNazwa_towaru());
            pozycjaRaport.setPozycjaWRaporcie(pozycjaBazy.getId());
            pozycjaRaport.setCenaBrutto(pozycjaBazy.getCena_brutto());
            pozycjaRaport.setSuma(pozycjaBazy.getCena_brutto(), pozycjaBazy.getIlosc());
            pozycjeRaportu.add(pozycjaRaport);
        }
        LOGGER.info("ilosc pozycji w liscie " + pozycjeRaportu.size());
        return pozycjeRaportu;
    }

    public String generujStatystyki(Session sesja) {
        String htmlStatystyka = null;
        double iloscStron = 0;
        double sumaNetto = 0;
        double sumaBrutto = 0;
        double iloscPozycji = 0;
        iloscPozycji = (long) sesja.createQuery("SELECT count(*) FROM PozycjaBazy").list().get(0);
        sumaNetto = (double) sesja.createQuery("SELECT sum(P.cena_netto*P.ilosc) FROM PozycjaBazy as P").list().get(0);
        sumaBrutto = (double) sesja.createQuery("SELECT sum(P.cena_brutto*P.ilosc) FROM PozycjaBazy as P").list().get(0);
        iloscStron = Math.ceil((iloscPozycji / iloscPozycjiNaStronie));
        htmlStatystyka = "<tr><td>" + (int) iloscPozycji + "</td><td>" + sumaNetto + "</td><td>" + sumaBrutto + "</td><td>" + (int) iloscStron + "</td></tr>";
        LOGGER.info("generujStatystyki: " + htmlStatystyka);

        return htmlStatystyka;
    }

    //TODO marcin
    public List<PozycjaBazy> pobierzZeroweCeny(Session sesja) {
        List<PozycjaBazy> pozycje = new ArrayList<>();
        pozycje.addAll(sesja.createQuery("FROM PozycjaBazy AS P WHERE P.cena_netto = 0 OR P.cena_brutto = 0 OR P.ilosc = 0").list());
        LOGGER.info("Ilosc pozycji z pustymi cenami w liscie " + pozycje.size());
        return pozycje;
    }

    private String utworzHQLDoPobraniaNumeruPozycjiH(PozycjaBazy pozycja) {
        String hql = "SELECT" + SPACE + pozycja.getId() + SPACE + "FROM PozycjaBazy" + SPACE + "WHERE" + SPACE +
                "nazwa_towaru =" + SPACE + "'" + pozycja.getNazwa_towaru() + "'" + SPACE + "AND" + SPACE +
                "cena_netto =" + SPACE + pozycja.getCena_netto() + SPACE + "AND" + SPACE +
                "cena_brutto =" + SPACE + pozycja.getCena_brutto() + SPACE + "AND" + SPACE +
                "jednostka =" + SPACE + "'" + pozycja.getJednostka() + "'" + SPACE + "AND" + SPACE +
                "ilosc =" + SPACE + pozycja.getIlosc() + SPACE + "AND" + SPACE +
                "uzytkownik =" + SPACE + "'" + pozycja.getUzytkownik() + "'";
        LOGGER.info("HQL do pobrania numeru pozycji = " + hql);
        return hql;
    }

    public int merge(Session session, List<Integer> ids) {
        List<PozycjaBazy> positions = new ArrayList<>();
        for (int id : ids) {
            positions.add(this.pobierzPozycjeDoZmien(session, id));
        }
        PozycjaBazy positionToUpdate = this.pobierzPozycjeDoZmien(session, ids.get(0));
        double amount = positionToUpdate.getIlosc();
        for (int pointer = 1; pointer < ids.size(); pointer++) {
            PozycjaBazy candidateToMerge = this.pobierzPozycjeDoZmien(session, ids.get(pointer));
            //TODO czy dodajemy ten check czy na palke mergujemy
            if (candidateToMerge.getCena_netto() == positionToUpdate.getCena_netto() &&
                    candidateToMerge.getCena_brutto() == positionToUpdate.getCena_brutto()
            ) {
                amount = amount + candidateToMerge.getIlosc();
            }
        }
        for (int pointer = 1; pointer < ids.size(); pointer++) {
            this.usunPozycjeZBazy(session, ids.get(pointer));
        }
        positionToUpdate.setIlosc(amount);
        this.zmienPozycjeWBazie(session, positionToUpdate);
        return ids.get(0);
    }
}

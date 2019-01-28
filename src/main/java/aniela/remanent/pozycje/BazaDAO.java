package aniela.remanent.pozycje;

import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;
import aniela.remanent.pozycje.helperyZapytanDoBazy.expression.evaluator.ExpressionEvaluator;
import aniela.remanent.raport.raportBrutto.PozycjaDoRaportuBrutto;
import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class BazaDAO {
    private final static Logger LOGGER = Logger.getLogger(BazaDAO.class);
    private static final String SPACE = " ";
    private static final int iloscPozycjiNaStronie = 55;

    @PersistenceContext
    private EntityManager entityManager;

    public List<PozycjaBazy> advancedSearch(String nazwaTowaru,
                                            String uzytkownik,
                                            double cenaNetto,
                                            int radioNetto,
                                            double cenaBrutto,
                                            int radioBrutto,
                                            double ilosc,
                                            int radioIlosc

    ) {
        Session session = entityManager.unwrap(Session.class);

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
        session.close();
        return result;
    }


    public int dodajPozycjeDoBazy(PozycjaBazy pozycja) {
//        Transaction tranzakcja = null;
        Session sesja = entityManager.unwrap(Session.class);
        int numer = 0;
        LOGGER.info("dodaj pozycje: " + pozycja);

        try {
//            tranzakcja = sesja.beginTransaction();
            sesja.save(pozycja);
//            tranzakcja.commit();
            LOGGER.info("wpis dodany");

            String hql = utworzHQLDoPobraniaNumeruPozycjiH(pozycja);
            List<Integer> lista = sesja.createQuery(hql).list();
            numer = lista.get(0);
            LOGGER.info("zwrocono numer pozycji z bazy= " + numer);

        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
//            if (tranzakcja != null) tranzakcja.rollback();
            e.printStackTrace();
        } finally {
            sesja.close();
        }
        return numer;
    }

    public List<PozycjaBazy> listaZnalezionychPozycjiWBazie(String pobranaNazwa, String radioValue) {
        List<PozycjaBazy> pozycje = new ArrayList<>();
        String hqlSzukajTowar = "FROM PozycjaBazy AS P WHERE P.nazwa_towaru LIKE CONCAT('%', :pobranaNazwa , '%')";
        String hqlSzukajTowarPoUserze = "FROM PozycjaBazy AS P WHERE P.uzytkownik LIKE CONCAT('%', :pobranaNazwa , '%')";
        Session sesja = entityManager.unwrap(Session.class);
        LOGGER.info("szukaj pozycji");
        try {
            if (radioValue.equalsIgnoreCase("nazwa")) {
                pozycje = sesja.createQuery(hqlSzukajTowar).setParameter("pobranaNazwa", pobranaNazwa).list();
            } else {
                pozycje = sesja.createQuery(hqlSzukajTowarPoUserze).setParameter("pobranaNazwa", pobranaNazwa).list();
                pozycje = pozycje.stream().limit(50).collect(Collectors.toList());
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

    public PozycjaBazy pobierzPozycjeDoZmien(int numer) {
        PozycjaBazy pozycja = null;
//        Transaction tranzakcja = null;
        Session sesja = entityManager.unwrap(Session.class);

        try {
//            tranzakcja = sesja.beginTransaction();
            pozycja = sesja.get(PozycjaBazy.class, numer);

            LOGGER.info("Znaleziono pozycje do zmien: " + pozycja);

//            tranzakcja.commit();

        } catch (HibernateException e) {
            LOGGER.info("Exception in Hibernate " + e);
        } finally {
            sesja.close();
            return pozycja;
        }

    }


    public int zmienPozycjeWBazie(PozycjaBazy pozycja) {
//        PozycjaBazy pozycjaZwrotna = null;
//        Transaction tranzakcja = null;
        Session sesja = entityManager.unwrap(Session.class);

        LOGGER.info("Pozycja do zmiany: " + pozycja);

        try {
//            tranzakcja = sesja.beginTransaction();
            sesja.update(pozycja);
//            tranzakcja.commit();

        } catch (HibernateException e) {
            LOGGER.info("Exception in Hibernate " + e);

        } finally {
            sesja.close();
        }
        LOGGER.info("Zmieniono pozycje na: " + pozycja);
        return pozycja.getId();

    }


    public int usunPozycjeZBazy(int numerDoUsunieciaZBazy) {
//        Transaction tranzakcja = null;
        Session sesja = entityManager.unwrap(Session.class);
        int numer = 0;

        try {
//            tranzakcja = sesja.beginTransaction();
            PozycjaBazy pozycja = sesja.get(PozycjaBazy.class, numerDoUsunieciaZBazy);
            sesja.delete(pozycja);
//            tranzakcja.commit();
            numer = numerDoUsunieciaZBazy;
        } catch (HibernateException e) {
            LOGGER.info("Exception in Hibernate " + e);
//            if (tranzakcja != null) tranzakcja.rollback();
        } finally {
            sesja.close();
        }
        LOGGER.info("NumerPozycji usunietej z bazy : " + numer);

        return numer;
    }


    public int obliczIloscPozycji() {
        Session sesja = entityManager.unwrap(Session.class);
        long iloscPozycji = 0;

        try {
            iloscPozycji = (long) sesja.createQuery("SELECT count(*) FROM PozycjaBazy").list().get(0);
        } catch (HibernateException e) {
            LOGGER.info("Exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {
            sesja.close();
        }
        LOGGER.info("Zwrocona ilosc pozycji : " + iloscPozycji);
        return (int) iloscPozycji;
    }


    public List<PozycjaDoRaportuNetto> przygotujPozycjeDoRaportuNetto() {
        List<PozycjaDoRaportuNetto> pozycjeRaportu = new ArrayList<>();
        Session sesja = entityManager.unwrap(Session.class);

        try {
            List<PozycjaBazy> pozycjeBazy = sesja.createQuery("FROM PozycjaBazy").list();

            //TODO froeach
            for (PozycjaBazy pozycjaBazy : pozycjeBazy) {
                PozycjaDoRaportuNetto pozycjaRaport = new PozycjaDoRaportuNetto();
                pozycjaRaport.setCenaNetto(pozycjaBazy.getCena_netto());
                pozycjaRaport.setIlosc(pozycjaBazy.getIlosc());
                pozycjaRaport.setJednostka(pozycjaBazy.getJednostka());
                pozycjaRaport.setNazwaTowaru(pozycjaBazy.getNazwa_towaru());
                pozycjaRaport.setPozycjaWRaporcie(pozycjaBazy.getId());
                pozycjaRaport.setSumaNetto(pozycjaBazy.getCena_netto(), pozycjaBazy.getIlosc());
                pozycjeRaportu.add(pozycjaRaport);
            }
        } catch (Throwable ex) {
            LOGGER.info("Exception in Hibernate " + ex);
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            sesja.close();
        }
        LOGGER.info("Ilosc pozycji w liscie " + pozycjeRaportu.size());
        return pozycjeRaportu;
    }


    public List<PozycjaDoRaportuBrutto> przygotujPozycjeDoRaportuBrutto() {
        List<PozycjaDoRaportuBrutto> pozycjeRaportu = new ArrayList<>();
        List<PozycjaBazy> pozycjeBazy = new ArrayList<>();
        Session sesja = entityManager.unwrap(Session.class);

        try {
            pozycjeBazy.addAll(sesja.createQuery("FROM PozycjaBazy ORDER BY nazwa_towaru ASC").list());
            //TODO froeach
            for (PozycjaBazy pozycjaBazy : pozycjeBazy) {
                PozycjaDoRaportuBrutto pozycjaRaport = new PozycjaDoRaportuBrutto();
                pozycjaRaport.setCenaNetto(pozycjaBazy.getCena_netto());
                pozycjaRaport.setIlosc(pozycjaBazy.getIlosc());
                pozycjaRaport.setJednostka(pozycjaBazy.getJednostka());
                pozycjaRaport.setNazwaTowaru(pozycjaBazy.getNazwa_towaru());
                pozycjaRaport.setPozycjaWRaporcie(pozycjaBazy.getId());
                pozycjaRaport.setCenaBrutto(pozycjaBazy.getCena_brutto());
                pozycjeRaportu.add(pozycjaRaport);
            }
        } catch (Throwable ex) {
            LOGGER.info("Exception in Hibernate " + ex);
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            sesja.close();
        }
        LOGGER.info("ilosc pozycji w liscie " + pozycjeRaportu.size());
        return pozycjeRaportu;
    }

    public String generujStatystyki() {
        String htmlStatystyka = null;
        Session sesja = entityManager.unwrap(Session.class);
        double iloscStron = 0;
        double sumaNetto = 0;
        double sumaBrutto = 0;
        double iloscPozycji = 0;

        try {
            iloscPozycji = (long) sesja.createQuery("SELECT count(*) FROM PozycjaBazy").list().get(0);
            sumaNetto = (double) sesja.createQuery("SELECT sum(P.cena_netto*P.ilosc) FROM PozycjaBazy as P").list().get(0);
            sumaBrutto = (double) sesja.createQuery("SELECT sum(P.cena_brutto*P.ilosc) FROM PozycjaBazy as P").list().get(0);
        } catch (Throwable ex) {
            LOGGER.info("exception in HIBERNATE " + ex);
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            sesja.close();
        }

        iloscStron = Math.ceil((iloscPozycji / iloscPozycjiNaStronie));
        htmlStatystyka = "<tr><td>" + (int) iloscPozycji + "</td><td>" + sumaNetto + "</td><td>" + sumaBrutto + "</td><td>" + (int) iloscStron + "</td></tr>";
        LOGGER.info("generujStatystyki: " + htmlStatystyka);

        return htmlStatystyka;
    }

    //TODO marcin
    public List<PozycjaBazy> pobierzZeroweCeny() {
        List<PozycjaBazy> pozycje = new ArrayList<>();
        Session sesja = entityManager.unwrap(Session.class);

        try {
            pozycje.addAll(sesja.createQuery("FROM PozycjaBazy AS P WHERE P.cena_netto = 0 OR P.cena_brutto = 0 OR P.ilosc = 0").list());
        } catch (Throwable ex) {
            LOGGER.info("Exception in HIBERNATE " + ex);
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        } finally {
            sesja.close();
        }

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


}

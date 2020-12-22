package aniela.remanent.pozycje;

import aniela.remanent.position.abstraction.Position;
import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class BazaRepository {

    private final static Logger LOGGER = Logger.getLogger(BazaService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BazaService bazaService;

    public PozycjaBazy dodajIloscDoPozycjiDoBazy(int numerPozycji, double ilosc) {
        PozycjaBazy pozycjaBazy = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            pozycjaBazy = bazaService.dodajIloscDoPozycjiDoBazy(sesja, numerPozycji, ilosc);
            sesja.close();
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {
            return pozycjaBazy;
        }
    }

    public List<PozycjaBazy> advancedSearch(
            String nazwaTowaru,
            String uzytkownik,
            double cenaNetto,
            int radioNetto,
            double cenaBrutto,
            int radioBrutto,
            double ilosc,
            int radioIlosc
    ) {

        List<PozycjaBazy> pozcyje = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            pozcyje = bazaService.advancedSearch(sesja, nazwaTowaru, uzytkownik, cenaNetto, radioNetto, cenaBrutto, radioBrutto, ilosc, radioIlosc);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {

            return pozcyje;
        }
    }

    public int dodajPozycjeDoBazy(PozycjaBazy pozycja) {
        int value = 0;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            value = bazaService.dodajPozycjeDoBazy(sesja, pozycja);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {
            return value;
        }
    }

    public List<PozycjaBazy> listaZnalezionychPozycjiWBazie(String pobranaNazwa, String radioValue) {
        List<PozycjaBazy> lista = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            lista = bazaService.listaZnalezionychPozycjiWBazie(sesja, pobranaNazwa, radioValue);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {
            return lista;
        }
    }

    public Integer zmienPozycjeWBazie(PozycjaBazy pozycja) {
        Integer result = 0;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            result = bazaService.zmienPozycjeWBazie(sesja, pozycja);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public Integer usunPozycjeZBazy(int numerDoUsunieciaZBazy) {
        Integer result = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            result = bazaService.usunPozycjeZBazy(sesja, numerDoUsunieciaZBazy);

        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {

            return result;
        }
    }

    public PozycjaBazy pobierzPozycjeDoZmien(int numerDoUsuniecia) {
        PozycjaBazy result = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            result = bazaService.pobierzPozycjeDoZmien(sesja, numerDoUsuniecia);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {

            return result;
        }
    }

    public int merge(List<Integer> ids) {
        int result = 0;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            result = bazaService.merge(sesja, ids);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    public List<Position> przygotujPozycjeDoRaportuBrutto() {
        List<Position> result = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            result = bazaService.przygotujPozycjeDoRaportuBrutto(sesja);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {

            return result;
        }
    }

    public List<Position> przygotujPozycjeDoRaportuNetto() {
        List<Position> result = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            result = bazaService.przygotujPozycjeDoRaportuNetto(sesja);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {

            return result;
        }
    }

    public String generujStatystyki() {
        String result = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            LOGGER.info("Baza repository generuj statystyki");
            result = bazaService.generujStatystyki(sesja);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {

            return result;
        }
    }

    public List<PozycjaBazy> pobierzZeroweCeny() {
        List<PozycjaBazy> result = null;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            result = bazaService.pobierzZeroweCeny(sesja);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {

            return result;
        }
    }

    public int obliczIloscPozycji() {
        int result = 0;
        try (Session sesja = entityManager.unwrap(Session.class)) {
            result = bazaService.obliczIloscPozycji(sesja);
        } catch (HibernateException e) {
            LOGGER.info("exception in HIBERNATE " + e);
            e.printStackTrace();
        } finally {

            return result;
        }

    }


}


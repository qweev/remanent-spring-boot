package aniela.remanent.raport.raportDoDruku;

import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.stronyExcela.Skoroszyt;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class Raport {
    final static Logger logger = Logger.getLogger(Raport.class);
    private static final int iloscPozycjiNaStronie = 55;
    private static double iloscPozycjiOstatniaStrona;
    private double iloscPozycji;
    private double iloscStron;
    private List<PozycjaDoRaportu> pozycje;

    private BazaDAO baza;
    public int numerWiersza = 0; // 0 = pierwszy wiersz
    private Skoroszyt skoroszyt;
    private int strona = 1;
    private int numerPozycji = 0;


    @Autowired
    public Raport(BazaDAO baza) {
        this.baza = baza;

        iloscPozycji = this.baza.obliczIloscPozycji();
        pozycje = this.baza.przygotujPozycjeDoRaportu();
        obliczIloscStron();
        obliczIloscPozycjiNaOstatniejStronie();

    }


    public String generujRaport(String sciezka) {

        numerPozycji = 0; // trzeba bo to jest bean singleton i druga generacje raportu robi error bo licznik zostaje
        numerWiersza = 0; // trzeba bo to jest bean singleton i druga generacje raportu robi error bo licznik zostaje

        System.out.println("++++++++++++++ ----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ ----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ ----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ ----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ ----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ ----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ ----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ ----------------- numerPozycji" + numerPozycji);

        System.out.println("++++++++++++++ ----------------- numerWiersza" + numerWiersza);
        System.out.println("++++++++++++++ ----------------- numerWiersza" + numerWiersza);
        System.out.println("++++++++++++++ ----------------- numerWiersza" + numerWiersza);
        System.out.println("++++++++++++++ ----------------- numerWiersza" + numerWiersza);
        System.out.println("++++++++++++++ ----------------- numerWiersza" + numerWiersza);
        System.out.println("++++++++++++++ ----------------- numerWiersza" + numerWiersza);

        this.skoroszyt = new Skoroszyt();

        generujPierwszaStrone();
        logger.info("pierwsza strona zrobiona");

        for (int i = 2; i < (iloscStron); i++) {
            generujSrodkoweStrony();
            logger.info("strona " + i + " zrobiona");
        }

        generujOstatniaStrone();
        logger.info("ostatnia strona zrobiona");

        generujPodsumowanie();
        logger.info("podsumowanie zrobione");

        try {
            logger.info("tworze plik, sciezka " + sciezka);
            utworzPlikExcela(sciezka, skoroszyt);
            logger.info("remanent zrobiony");
        } catch (Exception e) {
            e.printStackTrace();
        }

        numerPozycji = 0; // trzeba bo to jest bean singleton i druga generacje raportu robi error bo licznik zostaje
        numerWiersza = 0; // trzeba bo to jest bean singleton i druga generacje raportu robi error bo licznik zostaje

        return "OK";
    }


    private void generujPodsumowanie() {
        skoroszyt.utworzArkuszPodsumowanie();
    }


    private void generujPierwszaStrone() {

        ///czemu to sie printuje tyle razy?
        System.out.println("++++++++++++++  1----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ 1----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ 1----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ 1----------------- numerPozycji" + numerPozycji);
        System.out.println("++++++++++++++ 1----------------- numerPozycji" + numerPozycji);

        System.out.println("++++++++++++++ 1----------------- numerWiersza" + numerWiersza);
        System.out.println("++++++++++++++ 1----------------- numerWiersza" + numerWiersza);
        System.out.println("++++++++++++++ 1----------------- numerWiersza" + numerWiersza);
        System.out.println("++++++++++++++ ----------------- pozycje" + pozycje.size());

        System.out.println("++++++++++++++ ----------------- pozycje" + pozycje.size());
        System.out.println("++++++++++++++ ----------------- pozycje" + pozycje.size());


        int wierszPierwszejPozycjiNaStronie = numerWiersza + 4;
        int wierszOstatniejPozycjiNaStronie = wierszPierwszejPozycjiNaStronie + (iloscPozycjiNaStronie - 1);
        skoroszyt.utworzTytul(numerWiersza);
        skoroszyt.utworzNumerStrony(++numerWiersza, strona++);
        skoroszyt.utworzNaglowkiTabeli(++numerWiersza);
        skoroszyt.utworzPierwszyWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));
        ++numerPozycji;

        for (int i = 1; i < (iloscPozycjiNaStronie - 1); i++) {
            skoroszyt.utworzWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));
            ++numerPozycji;
        }

        skoroszyt.utworzOstatniWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));

        skoroszyt.utworzSumaTabeli(++numerWiersza, wierszPierwszejPozycjiNaStronie, wierszOstatniejPozycjiNaStronie);
    }


    private void generujSrodkoweStrony() {
        int wierszPierwszejPozycjiNaStronie = numerWiersza + 5;
        int wierszOstatniejPozycjiNaStronie = wierszPierwszejPozycjiNaStronie + (iloscPozycjiNaStronie - 1);
        skoroszyt.utworzPustyWiersz(++numerWiersza);
        skoroszyt.utworzNumerStrony(++numerWiersza, strona++);
        skoroszyt.utworzNaglowkiTabeli(++numerWiersza);

        ++numerPozycji;
        skoroszyt.utworzPierwszyWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));
        ++numerPozycji;

        for (int i = 1; i < (iloscPozycjiNaStronie - 1); i++) {
            skoroszyt.utworzWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));
            ++numerPozycji;
        }

        skoroszyt.utworzOstatniWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));
        skoroszyt.utworzSumaTabeli(++numerWiersza, wierszPierwszejPozycjiNaStronie, wierszOstatniejPozycjiNaStronie);
    }


    private void generujOstatniaStrone() {
        int wierszPierwszejPozycjiNaStronie = numerWiersza + 5;
        int wierszOstatniejPozycjiNaStronie = wierszPierwszejPozycjiNaStronie + ((int) iloscPozycjiOstatniaStrona) - 1;
        skoroszyt.utworzPustyWiersz(++numerWiersza);
        skoroszyt.utworzNumerStrony(++numerWiersza, strona);
        skoroszyt.utworzNaglowkiTabeli(++numerWiersza);

        ++numerPozycji;

        for (int i = 1; i < ((int) iloscPozycjiOstatniaStrona); i++) {
            skoroszyt.utworzWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));
            ++numerPozycji;
        }

        skoroszyt.utworzOstatniWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));

        skoroszyt.utworzSumaTabeli(++numerWiersza, wierszPierwszejPozycjiNaStronie, wierszOstatniejPozycjiNaStronie);

        skoroszyt.utworzKoncoweNapisy(numerPozycji, numerWiersza);
    }


    private void utworzPlikExcela(String sciezka, Skoroszyt skoroszyt) throws Exception {
        FileOutputStream out = new FileOutputStream(new File(sciezka));
        skoroszyt.pobierzSkoroszyt().write(out);
        out.close();
        System.out.println("xlsx written successfully");
    }


    public void obliczIloscStron() {
        iloscStron = Math.ceil((iloscPozycji / iloscPozycjiNaStronie));
        logger.info("ilosc stron: " + iloscStron);
    }


    private void obliczIloscPozycjiNaOstatniejStronie() {
        double iloscPozycjiSrodkoweStrony = (iloscStron - 1) * (iloscPozycjiNaStronie);
        iloscPozycjiOstatniaStrona = iloscPozycji - iloscPozycjiSrodkoweStrony;
    }

}

package aniela.remanent.raport.raportBrutto;

import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.raportDoDruku.Raport;
import aniela.remanent.raport.stronyExcela.SkoroszytBrutto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

@Service
public class RaportBrutto {
    final static Logger logger = Logger.getLogger(Raport.class);
    private static final int iloscPozycjiNaStronie = 55;
    private static double iloscPozycjiOstatniaStrona;
    private double iloscPozycji;
    private double iloscStron;
    private ArrayList<PozycjaDoRaportuBrutto> pozycje;


    private BazaDAO baza;
    public int numerWiersza = 0; // 0 = pierwszy wiersz
    private SkoroszytBrutto skoroszyt;
    private int strona = 1;
    private int numerPozycji = 0;


    @Autowired
    public RaportBrutto(BazaDAO baza) {
        this.baza = baza;
        iloscPozycji = this.baza.obliczIloscPozycji();
        pozycje = (ArrayList<PozycjaDoRaportuBrutto>) baza.przygotujPozycjeDoRaportuBrutto();
        obliczIloscStron();
        obliczIloscPozycjiNaOstatniejStronie();
    }


    public String generujRaport(String sciezka) {

        numerPozycji = 0; // trzeba bo to jest bean singleton i druga generacje raportu robi error bo licznik zostaje
        numerWiersza = 0; // trzeba bo to jest bean singleton i druga generacje raportu robi error bo licznik zostaje

        this.skoroszyt = new SkoroszytBrutto();

        generujPierwszaStrone();
        logger.info("pierwsza strona zrobiona");

        for (int i = 2; i < (iloscStron); i++) {
            generujSrodkoweStrony();
            logger.info("strona " + i + " zrobiona");
        }

        generujOstatniaStrone();
        logger.info("ostatnia strona zrobiona");


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


    private void generujPierwszaStrone() {
        //int wierszPierwszejPozycjiNaStronie = numerWiersza + 4;
        //int wierszOstatniejPozycjiNaStronie = wierszPierwszejPozycjiNaStronie + (iloscPozycjiNaStronie-1);
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

        //skoroszyt.utworzSumaTabeli(++numerWiersza, wierszPierwszejPozycjiNaStronie, wierszOstatniejPozycjiNaStronie);
        skoroszyt.utworzSumaTabeliPusta(++numerWiersza);
    }


    private void generujSrodkoweStrony() {
        //int wierszPierwszejPozycjiNaStronie = numerWiersza +5;
        //int wierszOstatniejPozycjiNaStronie = wierszPierwszejPozycjiNaStronie + (iloscPozycjiNaStronie-1);
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

        //skoroszyt.utworzSumaTabeli(++numerWiersza, wierszPierwszejPozycjiNaStronie, wierszOstatniejPozycjiNaStronie);
        skoroszyt.utworzSumaTabeliPusta(++numerWiersza);
    }


    private void generujOstatniaStrone() {
        //int wierszPierwszejPozycjiNaStronie = numerWiersza +5;
        //int wierszOstatniejPozycjiNaStronie = wierszPierwszejPozycjiNaStronie + ((int) iloscPozycjiOstatniaStrona) -1;
        skoroszyt.utworzPustyWiersz(++numerWiersza);
        skoroszyt.utworzNumerStrony(++numerWiersza, strona);
        skoroszyt.utworzNaglowkiTabeli(++numerWiersza);

        ++numerPozycji;

        for (int i = 1; i < ((int) iloscPozycjiOstatniaStrona); i++) {
            skoroszyt.utworzWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));
            ++numerPozycji;
        }

        skoroszyt.utworzOstatniWierszTabeli(++numerWiersza, numerPozycji, pozycje.get(numerPozycji));

        //skoroszyt.utworzSumaTabeli(++numerWiersza, wierszPierwszejPozycjiNaStronie, wierszOstatniejPozycjiNaStronie);
        skoroszyt.utworzSumaTabeliPusta(++numerWiersza);
        //skoroszyt.utworzKoncoweNapisy(numerPozycji, numerWiersza);
    }


    private void utworzPlikExcela(String sciezka, SkoroszytBrutto skoroszyt) throws Exception {
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

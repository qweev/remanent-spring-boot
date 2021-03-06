package aniela.remanent.report.excel.brutto;

import aniela.remanent.position.abstraction.Position;
import aniela.remanent.pozycje.BazaRepository;
import aniela.remanent.report.excel.netto.RaportExcelNetto;
import aniela.remanent.report.excel.stronyExcela.SkoroszytBrutto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class RaportExcelBrutto {
    private final static Logger LOG = Logger.getLogger(RaportExcelNetto.class);
    private static final int iloscPozycjiNaStronie = 55;
    private static double iloscPozycjiOstatniaStrona;
    private double iloscPozycji;
    private double iloscStron;
    private List<Position> pozycje;
    public int numerWiersza = 0; // 0 = pierwszy wiersz
    private SkoroszytBrutto skoroszyt;
    private int strona = 1;
    private int numerPozycji = 0;

    @Autowired
    private BazaRepository BazaRepository;

    public String generujRaport(String sciezka) {

        pozycje = BazaRepository.przygotujPozycjeDoRaportuBrutto();

        numerPozycji = 0; // trzeba bo to jest bean singleton i druga generacje raportu robi error bo licznik zostaje
        numerWiersza = 0; // trzeba bo to jest bean singleton i druga generacje raportu robi error bo licznik zostaje

        this.skoroszyt = new SkoroszytBrutto();

        generujPierwszaStrone();
        LOG.info("pierwsza strona zrobiona");

        for (int i = 2; i < (iloscStron); i++) {
            generujSrodkoweStrony();
            LOG.info("strona " + i + " zrobiona");
        }

        generujOstatniaStrone();
        LOG.info("ostatnia strona zrobiona");


        try {
            LOG.info("tworze plik, sciezka " + sciezka);
            utworzPlikExcela(sciezka, skoroszyt);
            LOG.info("remanent zrobiony");
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
        LOG.info("ilosc stron: " + iloscStron);
    }


    private void obliczIloscPozycjiNaOstatniejStronie() {
        double iloscPozycjiSrodkoweStrony = (iloscStron - 1) * (iloscPozycjiNaStronie);
        iloscPozycjiOstatniaStrona = iloscPozycji - iloscPozycjiSrodkoweStrony;
    }

}

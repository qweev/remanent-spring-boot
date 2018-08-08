package aniela.remanent.E2E_web_UI_Tab_Szukaj;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.SzukajTowarProste_UI_TabComponents.SzukajTab;
import aniela.remanent.SzukajTowarProste_UI_TabComponents.TabelkaWynikiSzukaj;
import aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class ZaawansowaneSzukanie_IT {
    @Autowired
    private WebDriver driver;

    @Autowired
    private SzukajTab szukajTab;
    @Autowired
    private TabelkaWynikiSzukaj tabelkaWynikiSzukaj;
    @Autowired
    private PrzyciskSzukajZaawansowane przyciskSzukajZaawansowane;
    @Autowired
    private PrzyciskSzukanieZaawansowanePokazUkryj przyciskSzukanieZaawansowanePokazUkryj;
    @Autowired
    private CheckboxNazwaTowaru checkboxNazwaTowaru;
    @Autowired
    private CheckboxCenaBrutto checkboxCenaBrutto;
    @Autowired
    private CheckboxCenaNetto checkboxCenaNetto;
    @Autowired
    private CheckboxUzytkownik checkboxUzytkownik;
    @Autowired
    private CheckboxIlosc checkboxIlosc;
    @Autowired
    private RadioCenaBrutto radioCenaBrutto;
    @Autowired
    private RadioCenaNetto radioCenaNetto;
    @Autowired
    private RadioIlosc radioIlosc;
    @Autowired
    private PoleZaawansowanaNazwaTowaru poleZaawansowanaNazwaTowaru;
    @Autowired
    private PoleZaawansowanyUzytkownik poleZaawansowanyUzytkownik;
    @Autowired
    private PoleZaawansowanaCenaBrutto poleZaawansowanaCenaBrutto;
    @Autowired
    private PoleZaawansowanaCenaNetto poleZaawansowanaCenaNetto;
    @Autowired
    private PoleZaawansowaIlosc poleZaawansowaIlosc;
    @Autowired
    private ModalSzukajDialog modalSzukajDialog;

    private String stylCzerwony = "czerwony";

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
        szukajTab.kliknijTab();
    }


    @Test
    public void zaznaczenieCheckboxow(){
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();

        zaznaczWszystkieCheckboxy();

        assertEquals(true, czyWszystkieCheckboxyZaznaczone());
    }

    @Test
    public void zerowanieCheckboxow(){
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();
        zaznaczWszystkieCheckboxy();

        ukryjIpokarzCheckboxy();

        assertEquals(true, czyWszystkieCheckboxyNiezaznaczone());
    }

    @Test
    public void szukanieBezZaznaczonychCheckboxow(){
        String modalDialogText = "puste wszystkie pola w szukaniu zaawansowanym !";
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();

        przyciskSzukajZaawansowane.kliknijSzukaj();

        assertEquals(modalDialogText, modalSzukajDialog.pobierzText());
    }

    @Test
    public void szukaniePrzyPustychPolach(){
        String modalDialogText = "walidajca zaawansowane szukanie";
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();

        zaznaczWszystkieCheckboxy();
        przyciskSzukajZaawansowane.kliknijSzukaj();

        assertEquals(modalDialogText, modalSzukajDialog.pobierzText());
    }

    @Test
    public void szukaniePrzWpisanychZlychLiczbach(){
        String modalDialogText = "walidajca zaawansowane szukanie";
        String zlaLiczba ="1..2.";
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();

        zaznaczCheckboxyLiczbowe();
        wpiszZlaLiczbeDoPolLiczbowych(zlaLiczba);
        przyciskSzukajZaawansowane.kliknijSzukaj();

        assertEquals(modalDialogText, modalSzukajDialog.pobierzText());
    }

    @Test
    public void szukanieZeWszystkimiKryteriami(){
        int liczbaWidocznychWierszy = 3;
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();

        zaznaczWszystkieCheckboxy();
        uzupelnijWszystkiePolaDoSzukania();
        ustawRadioButtony();
        przyciskSzukajZaawansowane.kliknijSzukaj();
//        czekajNaWyniki();

        assertEquals(liczbaWidocznychWierszy, tabelkaWynikiSzukaj.liczbaWidocznychWierszy());
    }

    @Test
    public void szukaniePoCenieNettoITowarze(){
        int liczbaWidocznychWierszy = 3;
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();

        wpiszNeneNettoITowar();
        radioCenaNetto.kliknijMniejsze();
        przyciskSzukajZaawansowane.kliknijSzukaj();
//        czekajNaWyniki();

        assertEquals(liczbaWidocznychWierszy, tabelkaWynikiSzukaj.liczbaWidocznychWierszy());
    }

    private void wpiszNeneNettoITowar() {
        checkboxNazwaTowaru.kliknij();
        poleZaawansowanaNazwaTowaru.wpiszTekst("zaawansowane");
        checkboxCenaNetto.kliknij();
        poleZaawansowanaCenaNetto.wpiszTekst("2");
    }

    private void uzupelnijWszystkiePolaDoSzukania() {
        poleZaawansowanaNazwaTowaru.wpiszTekst("zaawansowane");
        poleZaawansowanyUzytkownik.wpiszTekst("zaawansowany");
        poleZaawansowanaCenaBrutto.wpiszTekst("2");
        poleZaawansowanaCenaNetto.wpiszTekst("2");
        poleZaawansowaIlosc.wpiszTekst("9");
    }

    private void ustawRadioButtony() {
        radioCenaBrutto.kliknijWieksze();
        radioCenaNetto.kliknijMniejsze();
        radioIlosc.kliknijMniejsze();
    }


    private void czekajNaWyniki() {
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekajNaPobranie = new WebDriverWait(driver, 20, 2000);
        czekajNaPobranie.until(ExpectedConditions.stalenessOf(blockUI));
    }

    private void wpiszZlaLiczbeDoPolLiczbowych(String zlaLiczba) {
        poleZaawansowanaCenaBrutto.wpiszTekst(zlaLiczba);
        poleZaawansowanaCenaNetto.wpiszTekst(zlaLiczba);
        poleZaawansowaIlosc.wpiszTekst(zlaLiczba);
    }

    private void zaznaczCheckboxyLiczbowe() {
        checkboxCenaBrutto.kliknij();
        checkboxCenaNetto.kliknij();
        checkboxIlosc.kliknij();
    }

    private void ukryjIpokarzCheckboxy() {
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();
        przyciskSzukanieZaawansowanePokazUkryj.kliknijSzukaj();
    }


    private boolean czyWszystkieCheckboxyZaznaczone() {
        if ( (checkboxNazwaTowaru.czyZaznaczony() &&
                checkboxCenaBrutto.czyZaznaczony() &&
                checkboxCenaNetto.czyZaznaczony() &&
                checkboxUzytkownik.czyZaznaczony() &&
                checkboxIlosc.czyZaznaczony()) == true
                )
            return true;
        else
            return false;
    }

    private boolean czyWszystkieCheckboxyNiezaznaczone() {
        if ( (checkboxNazwaTowaru.czyZaznaczony() ||
                checkboxCenaBrutto.czyZaznaczony() ||
                checkboxCenaNetto.czyZaznaczony() ||
                checkboxUzytkownik.czyZaznaczony() ||
                checkboxIlosc.czyZaznaczony()) == false
                )
            return true;
        else
            return false;
    }

    private void zaznaczWszystkieCheckboxy() {
        checkboxNazwaTowaru.kliknij();
        checkboxCenaBrutto.kliknij();
        checkboxCenaNetto.kliknij();
        checkboxUzytkownik.kliknij();
        checkboxIlosc.kliknij();
    }

}

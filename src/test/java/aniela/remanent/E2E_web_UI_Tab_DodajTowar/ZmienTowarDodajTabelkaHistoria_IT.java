package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.*;
import aniela.remanent.Zmien_UI_TabComponents.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class ZmienTowarDodajTabelkaHistoria_IT {



    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private ZapisDoBazyPrzycisk przyciskZapisDoBazy;
    @Autowired
    private PoleNazwaTowaru poleNazwaTowaru;
    @Autowired
    private PoleUzytkownik poleUzytkownik;
    @Autowired
    private PoleUzytkownik poleUzytkowni;
    @Autowired
    private PoleCenaBrutto poleCenaBrutto;
    @Autowired
    private PoleCenaNetto poleCenaNetto;
    @Autowired
    private PoleIlosc poleIlosc;
    @Autowired
    private PoleJednostka poleJednostka;
    @Autowired
    private PoleDodajWHistori poleDodajWHistori;
    @Autowired
    private TabelkaHistoriaWpisow tabelkaHistoriaWpisow;
    @Autowired
    private PrzyciskZmien przyciskZmien;
    @Autowired
    private PoleWpiszNumerPozycjiZmien poleWpiszNumerPozycjiZmien;
    @Autowired
    private PoleIloscZmien poleIloscZmien;
    @Autowired
    private PoleNazwaTowaruZmien poleNazwaTowaruZmien;
    @Autowired
    private ZapiszZmianyDoBazyPrzyciskZmien zapiszZmianyDoBazyPrzycisk;
    @Autowired
    private ModalZmienDialog modalZmienDialog;
    @Autowired
    private DodajTab dodajTab;
    @Autowired
    private DodajWHistoriPrzycisk dodajWHistoriPrzycisk;



    @Before
    public void setUp() {
        driver.get("http://localhost:8080/index.html");

        uzupelnijPola1();
        przyciskZapisDoBazy.kliknijPrzycisk();
        waitForJavaScriptToFinish(3);
    }

    @Test
    public void zmienTowarNaDodaj() {
        przyciskZmien.kliknijZmien();

        assertTrue(poleWpiszNumerPozycjiZmien.jestWidoczne());

        zmienPolaPozycji("Zmieniony po dodaj", "100");

        dodajTab.kliknijTab();

        List<WebElement> komorki = tabelkaHistoriaWpisow.zbierzKomorkiPozycjiZWierszaTabelki(1);
        String nazwaTowaruPoZmianie = komorki.get(1).getText();
        String iloscPoZmianie = komorki.get(5).getText();

        assertEquals(nazwaTowaruPoZmianie, "Zmieniony po dodaj");
        assertEquals(iloscPoZmianie, "100");
        assertTrue(dodajWHistoriPrzycisk.jestWidoczny());
        assertTrue(przyciskZmien.jestWidoczny());
    }


    private void uzupelnijPola1() {
        poleNazwaTowaru.wpiszNazweTowaru("towar zmien na dodaj");
        poleUzytkownik.wpiszUzytkownika("zmienuser");
        poleCenaBrutto.wpiszCeneBrutto("32,51");
        poleCenaNetto.wpiszCeneNetto("22.45");
        poleIlosc.wpiszIlosc("32");
        poleJednostka.wybierz("op.");
    }


    private void waitForJavaScriptToFinish(int timeoutInSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(timeoutInSeconds, TimeUnit.SECONDS)
                    .pollingEvery(500, TimeUnit.MILLISECONDS)
                    .withMessage("###### Wait for page to be ready failed, trying to finish test execution anyway");

            wait.until(ExpectedConditions.jsReturnsValue("return (document.readyState == 'complete' && jQuery.active == 0)"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zmienPolaPozycji(String nazwaTowaruDoZmiany, String iloscDoZmiany) {
        FluentWait wait = new FluentWait(driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOf(poleIloscZmien.pobierzPoWebElement()));
        poleIloscZmien.wyczysc();
        poleNazwaTowaruZmien.wyczysc();
        poleIloscZmien.wpiszIlosc(iloscDoZmiany);
        poleIloscZmien.zmienFocus();
        waitForJavaScriptToFinish(2);
        poleNazwaTowaruZmien.wpiszNazweTowaru(nazwaTowaruDoZmiany);
        poleNazwaTowaruZmien.zmienFocus();
        waitForJavaScriptToFinish(2);
        zapiszZmianyDoBazyPrzycisk.kliknijPrzycisk();
        modalZmienDialog.zamknij();
        waitForJavaScriptToFinish(4);
    }

}

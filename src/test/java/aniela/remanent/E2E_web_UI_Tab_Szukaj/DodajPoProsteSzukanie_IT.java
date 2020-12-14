package aniela.remanent.E2E_web_UI_Tab_Szukaj;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.*;
import aniela.remanent.SzukajTowarProste_UI_TabComponents.*;
import aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents.ModalSzukajDialog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class DodajPoProsteSzukanie_IT {


    @Autowired
    private  WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private SzukajTab szukajTab;
    @Autowired
    private PoleSzukajPoNazwie poleSzukajPoNazwie;
    @Autowired
    private PoleSzukajPozycji poleSzukajPozycji;
    @Autowired
    private PrzyciskSzukajPozycji przyciskSzukajPozycji;
    @Autowired
    private TabelkaWynikiSzukaj tabelkaWynikiSzukaj;
    @Autowired
    private PrzyciskDodajTabelka przyciskDodajTabelka;
    @Autowired
    private PoleDodajTabelka poleDodajTabelka;
    @Autowired
    private ModalSzukajDialog modalSzukajDialog;



    @Autowired
    private ZapisDoBazyPrzycisk przyciskZapisDoBazy;
    @Autowired
    private ModalZapiszDialog dialogModal;
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
    private DodajWHistoriPrzycisk dodajWHistoriPrzycisk;
    @Autowired
    private PoleDodajWHistori poleDodajWHistori;
    @Autowired
    private TabelkaHistoriaWpisow tabelkaHistoriaWpisow;
    @Autowired
    private DodajTab dodajTab;
    @Autowired
    private PrzyciskZmien przyciskZmien;


    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
    }


    @Test
    public void dodajIloscPoSzukaj(){
        String nazwaDoSzukania = "szukaj dodaj";
        String dodajIlosc = "10";
        szukajTab.kliknijTab();

        poleSzukajPozycji.wpiszTekst(nazwaDoSzukania);
        przyciskSzukajPozycji.kliknijSzukaj();
        czekajNaWynikiWTabelce();

        poleDodajTabelka.wpiszSztuki(dodajIlosc);
        przyciskDodajTabelka.kliknijDodaj();
        zamknijModalDialogPoDodaniu();

        String ilosc = poleDodajTabelka.pobierzIloscDlaWiersza(1); //pierwszy wiersz

        assertEquals(ilosc, "21");
    }


    @Test
    public void dodajTowarZmienIloscPoSzukajISprawdzHistorieNaDodaj(){
        String nazwaDoSzukania = "nowy towar po zmianie w szukaj";
        String dodajIlosc = "10";

        dodajTab.kliknijTab();
        uzupelnijPola();
        przyciskZapisDoBazy.kliknijPrzycisk();
        waitForJavaScriptToFinish(3);
        List<WebElement> komorkiPrzed = tabelkaHistoriaWpisow.zbierzKomorkiPozycjiZWierszaTabelki(1); //pierwszy wiersz
        String iloscPobranaPrzed = komorkiPrzed.get(5).getText(); // kolumna ilosc

        szukajTab.kliknijTab();
        poleSzukajPozycji.wpiszTekst(nazwaDoSzukania);
        przyciskSzukajPozycji.kliknijSzukaj();
        czekajNaWynikiWTabelce();

        poleDodajTabelka.wpiszSztuki(dodajIlosc);
        przyciskDodajTabelka.kliknijDodaj();
        zamknijModalDialogPoDodaniu();
        String ilosc = poleDodajTabelka.pobierzIloscDlaWiersza(1); //pierwszy wiersz

        assertEquals(ilosc, "43");

        dodajTab.kliknijTab();

        List<WebElement> komorkiPo = tabelkaHistoriaWpisow.zbierzKomorkiPozycjiZWierszaTabelki(1); //pierwszy wiersz
        String iloscPobranaPo = komorkiPo.get(5).getText(); // kolumna ilosc

        Integer wynik = Integer.valueOf(iloscPobranaPrzed)+Integer.valueOf(dodajIlosc);
        assertEquals(iloscPobranaPo, wynik.toString());

        assertTrue(przyciskZmien.jestWidoczny());
        assertTrue(dodajWHistoriPrzycisk.jestWidoczny());
    }


    private void zamknijModalDialogPoDodaniu() {
        FluentWait wait =  new FluentWait(driver).withTimeout(5, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(modalSzukajDialog.getPrzyciskZamknijDialogSzukaj()));
        modalSzukajDialog.zamknijDialog();

    }

    private void czekajNaWynikiWTabelce() {
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekaj = new WebDriverWait(driver, 20, 2000);
        czekaj.until(ExpectedConditions.stalenessOf(blockUI));
        czekaj.until(ExpectedConditions.elementToBeClickable(przyciskSzukajPozycji.pobierzWebElement()));
    }


    private void uzupelnijPola() {

        poleNazwaTowaru.wpiszNazweTowaru("nowy towar po zmianie w szukaj");
        poleUzytkownik.wpiszUzytkownika("aa");
        poleCenaBrutto.wpiszCeneBrutto("3,51");
        poleCenaNetto.wpiszCeneNetto("2.45");
        poleIlosc.wpiszIlosc("33");
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

}

package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class DodajTowarTabTabelkaHistoria_IT {



    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

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
    private DodajWHistoriPrzycisk_2 dodajWHistoriPrzycisk2;
    @Autowired
    private PoleDodajWHistori poleDodajWHistori;
    @Autowired
    private TabelkaHistoriaWpisow tabelkaHistoriaWpisow;


    @Before
    public void setUp() throws InterruptedException {
        driver.get("http://localhost:8080/index.html");

        uzupelnijPola1();
        przyciskZapisDoBazy.kliknijPrzycisk();
        waitForJavaScriptToFinish(3);
        uzupelnijPola2();
        przyciskZapisDoBazy.kliknijPrzycisk();
        waitForJavaScriptToFinish(3);
        uzupelnijPola3();
        przyciskZapisDoBazy.kliknijPrzycisk();
        waitForJavaScriptToFinish(3);
    }

    @Test
    public void dodajIloscDoDrugiejPozycjiHistorii() throws InterruptedException {
        String iloscDodaj = "30";
        List<WebElement> komorkiPrzed = tabelkaHistoriaWpisow.zbierzKomorkiPozycjiZWierszaTabelki(2); //drugi wiersz
        String iloscPobranaPrzed = komorkiPrzed.get(5).getText(); // kolumna ilosc

        poleDodajWHistori.wpiszIlosc(iloscDodaj);
        dodajWHistoriPrzycisk2.kliknijDodaj();
        waitForJavaScriptToFinish(3);
        List<WebElement> komorkiPo = tabelkaHistoriaWpisow.zbierzKomorkiPozycjiZWierszaTabelki(2); //drugi wiersz
        String iloscPobranaPo = komorkiPo.get(5).getText(); // kolumna ilosc

        Integer wynik = Integer.valueOf(iloscPobranaPrzed)+Integer.valueOf(iloscDodaj);
        assertEquals(iloscPobranaPo, wynik.toString());
    }


    private void uzupelnijPola1() {
        poleNazwaTowaru.wpiszNazweTowaru("towar1");
        poleUzytkownik.wpiszUzytkownika("aa");
        poleCenaBrutto.wpiszCeneBrutto("3,51");
        poleCenaNetto.wpiszCeneNetto("2.45");
        poleIlosc.wpiszIlosc("3");
        poleJednostka.wybierz("op.");
    }

    private void uzupelnijPola2() {
        poleNazwaTowaru.wpiszNazweTowaru("towar2");
        poleUzytkownik.wpiszUzytkownika("aa");
        poleCenaBrutto.wpiszCeneBrutto("23,51");
        poleCenaNetto.wpiszCeneNetto("22.45");
        poleIlosc.wpiszIlosc("30");
        poleJednostka.wybierz("op.");
    }

    private void uzupelnijPola3() {
        poleNazwaTowaru.wpiszNazweTowaru("towar3");
        poleUzytkownik.wpiszUzytkownika("aa");
        poleCenaBrutto.wpiszCeneBrutto("33,51");
        poleCenaNetto.wpiszCeneNetto("32.45");
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

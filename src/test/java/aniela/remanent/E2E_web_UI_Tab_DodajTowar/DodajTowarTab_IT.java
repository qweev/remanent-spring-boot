package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleIlosc;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class DodajTowarTab_IT {



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

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");

    }

    @Test
    public void dodajTowarPoprawnyWpis(){
        uzupelnijPola();

        przyciskZapisDoBazy.kliknijPrzycisk();

        assertEquals(true, jestWpisWTabelceHistoriiWpisow());
    }


    @Test
    public void dodajTowarNiepoprawnyWpis(){
        przyciskZapisDoBazy.kliknijPrzycisk();

        assertEquals(true, dialogModal.pobierzText().contains("Niepoprawny"));
    }



    private void uzupelnijPola() {
//        PoleNazwaTowaru poleNazwaTowaru = new PoleNazwaTowaru(driver);
//        PoleUzytkownik poleUzytkownik = new PoleUzytkownik(driver);
//        PoleCenaBrutto poleCenaBrutto = new PoleCenaBrutto(driver);
//        PoleCenaNetto poleCenaNetto = new PoleCenaNetto(driver);
//        PoleIlosc poleIlosc = new PoleIlosc(driver);
//        PoleJednostka poleJednostka = new PoleJednostka(driver);

        poleNazwaTowaru.wpiszNazweTowaru("towar");
        poleUzytkownik.wpiszUzytkownika("aa");
        poleCenaBrutto.wpiszCeneBrutto("3,51");
        poleCenaNetto.wpiszCeneNetto("2.45");
        poleIlosc.wpiszIlosc("3");
        poleJednostka.wybierz("op.");
    }


    private boolean jestWpisWTabelceHistoriiWpisow() {
        WebDriverWait czekajNaTabelke = new WebDriverWait(driver, 120, 1000);
        return czekajNaTabelke.until(widocznaPozycjaWTabelce());
    }

    private static ExpectedCondition<Boolean> widocznaPozycjaWTabelce() {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {

                List<WebElement> polaPozycjiWTabelce = zbierzKomorkiPozycjiZTabelki(driver);
                return polaPozycjiWTabelce.stream()
                        .filter(komorka -> (komorka.getText().length() == 0) )
                        .collect(Collectors.toList())
                        .isEmpty();
            }
        };
    }


    private static List<WebElement> zbierzKomorkiPozycjiZTabelki(WebDriver driver){
        List<WebElement> komorki = new ArrayList<WebElement>();
        int pierwszaKomorkaPozycjiWTabelce = 2;
        int ostatniaKomorkaPozycjiWTabelce = 8;

        for(int komorka=pierwszaKomorkaPozycjiWTabelce; komorka < ostatniaKomorkaPozycjiWTabelce; komorka++){
            komorki.add(driver.findElement(By.xpath("//tbody[@id='tabelka']/tr/td["+komorka+"]")));
        }

        return komorki;
    }


}

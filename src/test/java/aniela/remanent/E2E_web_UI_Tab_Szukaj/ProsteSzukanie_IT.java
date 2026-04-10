package aniela.remanent.E2E_web_UI_Tab_Szukaj;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.SzukajTowarProste_UI_TabComponents.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class ProsteSzukanie_IT {


    @Autowired
    private  WebDriver driver;

    @Autowired
    private SzukajTab szukajTab;
    @Autowired
    private PoleSzukajPoUzytkowniku poleSzukajPoUzytkowniku;
    @Autowired
    private PoleSzukajPozycji poleSzukajPozycji;
    @Autowired
    private PrzyciskSzukajPozycji przyciskSzukajPozycji;
    @Autowired
    private TabelkaWynikiSzukaj tabelkaWynikiSzukaj;
    @Autowired
    private PoleFiltrSzukaj poleFiltrSzukaj;

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
        szukajTab.kliknijTab();
        czekajAzZakladkaSzukajBedzieAktywna();
    }

    @Test
    public void szukaniePoNazwie(){
        String nazwaDoSzukania = "szukaj_e2e";
        int liczbaWierszy = 4;

        poleSzukajPozycji.wpiszTekst(nazwaDoSzukania);
        przyciskSzukajPozycji.kliknijSzukaj();
        czekajNaWynikiWTabelce();

        assertEquals(liczbaWierszy, tabelkaWynikiSzukaj.liczbaWidocznychWierszy());
    }

    @Test
    public void szukaniePoUzytkowniku(){
        String nazwaDoSzukania = "szukaj";
        int liczbaWierszy = 5;

        poleSzukajPoUzytkowniku.kliknij();
        czekajAzZakladkaSzukajBedzieAktywna();
        poleSzukajPozycji.wpiszTekst(nazwaDoSzukania);
        przyciskSzukajPozycji.kliknijSzukaj();
        czekajNaWynikiWTabelce();

        assertEquals(liczbaWierszy, tabelkaWynikiSzukaj.liczbaWidocznychWierszy());
    }

    @Test
    public void filtrujWynikiSzukania(){
        int liczbaWierszyPoFiltrowaniu = 2;

        tabelkaWynikiSzukaj.stworzSztuczneWynikiSzukania();
        poleFiltrSzukaj.wpiszDoFiltra("filtr");

        assertEquals(liczbaWierszyPoFiltrowaniu, tabelkaWynikiSzukaj.liczbaWidocznychWierszy() );
    }


    private void czekajNaWynikiWTabelce() {
        WebDriverWait czekaj = new WebDriverWait(driver, Duration.ofSeconds(20));
        czekaj.pollingEvery(Duration.ofSeconds(2));
        czekaj.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI")));
        czekaj.until(ExpectedConditions.elementToBeClickable(przyciskSzukajPozycji.pobierzWebElement()));
    }

    private void czekajAzZakladkaSzukajBedzieAktywna() {
        WebDriverWait czekaj = new WebDriverWait(driver, Duration.ofSeconds(20));
        czekaj.pollingEvery(Duration.ofSeconds(2));
        czekaj.until(ExpectedConditions.attributeContains(By.id("szukaj"), "class", "active"));
        czekaj.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI")));
        czekaj.until(ExpectedConditions.visibilityOfElementLocated(By.id("szukajPozycji")));
    }
}

package aniela.remanent.E2E_web_UI_Tab_Szukaj;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

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

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
        szukajTab.kliknijTab();
    }


    @Test
    public void dodajIloscPoSzukaj(){
        String nazwaDoSzukania = "raport towar";
        String dodajIlosc = "10";

        poleSzukajPozycji.wpiszTekst(nazwaDoSzukania);
        przyciskSzukajPozycji.kliknijSzukaj();
        czekajNaWynikiWTabelce();

        poleDodajTabelka.wpiszSztuki(dodajIlosc);
        przyciskDodajTabelka.kliknijDodaj();
        zamknijModalDialogPoDodaniu();

        String ilosc = poleDodajTabelka.pobierzIloscDlaWiersza(1);

        assertEquals(ilosc, "21");
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


}

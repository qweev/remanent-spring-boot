package aniela.remanent.E2E_web_UI_Tab_Zmien;

import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.Zmien_UI_TabComponents.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class ZmienPozycje_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private TabZmien tabZmien;
    @Autowired
    private PoleWpiszNumerPozycjiZmien poleWpiszNumerPozycji;
    @Autowired
    private ZapiszZmianyDoBazyPrzyciskZmien zapiszZmianyDoBazyPrzycisk;
    @Autowired
    private PoleIloscZmien poleIlosc;
    @Autowired
    private PoleNazwaTowaruZmien poleNazwaTowaru;
    @Autowired
    private ModalZmienDialog modalZmienDialog;

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
        tabZmien.kliknijTab();
    }

    @Test
    public void zmianaNazwyTowaruOrazIlosciWPozycji(){
        String nazwaTowaruDoZmiany  = "nowa nazwa";
        String iloscDoZmiany = "13";
        String numerPozycji = "7";

        pobierzPozycje(numerPozycji);
        zmienPolaPozycji(nazwaTowaruDoZmiany, iloscDoZmiany);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(true, czyPozycjaZmieniona(numerPozycji));
    }


    @Test
    public void pobranieNieistniejacejPozycji(){
        String numerNieistniejacejPozycji = "99999";

        pobierzNieistniejacaPozycje(numerNieistniejacejPozycji);

        assertEquals(true, modalZmienDialog.pobierzStyl().contains(stylCzerwony));
        assertEquals(true, modalZmienDialog.pobierzText().contains("Nie ma takiej pozycji. [410]"));
    }

    private boolean czyPozycjaZmieniona(String numerPozycji) {
//        ModalZmienDialog modalDialog = new ModalZmienDialog(driver);
        return ModalZmienDialog.pobierzText().contains("Pozycja numer: "+ numerPozycji +" zmieniona w bazie");
    }

    private void zmienPolaPozycji(String nazwaTowaruDoZmiany, String iloscDoZmiany) {
        poleIlosc.wyczysc();
        poleNazwaTowaru.wyczysc();
        poleIlosc.wpiszIlosc(iloscDoZmiany);
        poleIlosc.zmienFocus();
        poleNazwaTowaru.wpiszNazweTowaru(nazwaTowaruDoZmiany);
        poleNazwaTowaru.zmienFocus();
        zapiszZmianyDoBazyPrzycisk.kliknijPrzycisk();
    }

    private void pobierzPozycje(String numerPozycji) {
        poleWpiszNumerPozycji.wpiszNumerPozycji(numerPozycji);
        poleWpiszNumerPozycji.kliknijPrzycisk();
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekajNaPobranie = new WebDriverWait(driver, 20, 2000);
        czekajNaPobranie.until(ExpectedConditions.stalenessOf(blockUI));
        czekajNaPobranie.until(ExpectedConditions.elementToBeClickable(poleIlosc.pobierzPoWebElement()));
    }

    private void pobierzNieistniejacaPozycje(String numerPozycji){
        poleWpiszNumerPozycji.wpiszNumerPozycji(numerPozycji);
        poleWpiszNumerPozycji.kliknijPrzycisk();
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekajNaPobranie = new WebDriverWait(driver, 20, 2000);
        czekajNaPobranie.until(ExpectedConditions.stalenessOf(blockUI));
        czekajNaPobranie.until(ExpectedConditions.visibilityOf(modalZmienDialog.pobierzWebElement()));
    }


}

package aniela.remanent.E2E_web_UI_Tab_Usun;

import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.DodajTab;
import aniela.remanent.DodajTowar_UI_TabComponents.DodajWHistoriPrzycisk_2;
import aniela.remanent.DodajTowar_UI_TabComponents.PrzyciskZmien;
import aniela.remanent.UsunTowar_UI_TabComponents.*;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class UsunTowar_IT {

    @Autowired
    private  WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private TabUsun tabUsun;
    @Autowired
    private PolePobierzPozycjeDoUsun polePobierzPozycjeDoUsun;
    @Autowired
    private PrzyciskPobierzDoUsun przyciskPobierzDoUsun;
    @Autowired
    private ModalUsunDialog modalUsunDialog;
    @Autowired
    private TabelkaUsun tabelkaUsun;
    @Autowired
    private PrzyciskUsunZBazy przyciskUsunZBazy;
    @Autowired
    private PrzyciskZmien przyciskZmien;
    @Autowired
    private DodajWHistoriPrzycisk_2 dodajWHistoriPrzycisk2;
    @Autowired
    private DodajTab dodajTab;

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
        tabUsun.kliknijTab();
    }


    @Test
    public void wpisanieNieistniejacegoNumeruPozycji(){
        String numerPozycji = "8678";

        polePobierzPozycjeDoUsun.wpiszNumerPozycjiDoUsuniecia(numerPozycji);
        polePobierzPozycjeDoUsun.zmienFocus();
        przyciskPobierzDoUsun.kliknijPrzycisk();
        czekajNaModalDialog();

        assertEquals(true, modalUsunDialog.pobierzStyl().contains(stylCzerwony));
        assertEquals(true, modalUsunDialog.pobierzText().contains("Nie ma takiej pozycji. [410]"));
    }


    @Test
    public void wpisanieZlegoNumeruPozycji(){
        String zlyNumerPozycji = "8_zly_numer";

        polePobierzPozycjeDoUsun.wpiszNumerPozycjiDoUsuniecia(zlyNumerPozycji);
        polePobierzPozycjeDoUsun.zmienFocus();
        przyciskPobierzDoUsun.kliknijPrzycisk();
        czekajNaModalDialog();

        assertEquals(true, modalUsunDialog.pobierzStyl().contains(stylCzerwony));
        assertEquals(true, modalUsunDialog.pobierzText().contains("wpisz jeszcze raz"));

    }

    @Test
    public void usunieciePozycji(){
        String numerPozycji = "9";

        polePobierzPozycjeDoUsun.wpiszNumerPozycjiDoUsuniecia(numerPozycji);
        przyciskPobierzDoUsun.kliknijPrzycisk();
        czekajNaPozycjeIPrzyciskUsunZBazy();

        przyciskUsunZBazy.kliknijPrzycisk();
        czekajNaModalDialog();

        assertEquals(false, modalUsunDialog.pobierzStyl().contains(stylCzerwony));
        assertEquals(true, modalUsunDialog.pobierzText().contains("Pozycja o numerze: ".concat(numerPozycji)));
    }

    private void czekajNaPozycjeIPrzyciskUsunZBazy() {
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekaj = new WebDriverWait(driver, 20, 500);
        czekaj.until(ExpectedConditions.stalenessOf(blockUI));
        czekaj.until(ExpectedConditions.visibilityOf(tabelkaUsun.pobierzWebElement()));
        czekaj.until(ExpectedConditions.elementToBeClickable(przyciskUsunZBazy.pobierzWebElement()));
    }

    private void czekajNaModalDialog() {
        WebDriverWait czekajModal = new WebDriverWait(driver, 20, 2000);
        czekajModal.until(ExpectedConditions.visibilityOf(modalUsunDialog.pobierzWebElement()));
    }

}

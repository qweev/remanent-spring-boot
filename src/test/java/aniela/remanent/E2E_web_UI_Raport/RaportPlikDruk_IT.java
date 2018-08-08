package aniela.remanent.E2E_web_UI_Raport;

import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.Raport_Generuj_Plik_UI_Cmpoments.ModalExcel;
import aniela.remanent.Raport_Generuj_Plik_UI_Cmpoments.PoleRemanentDruk;
import aniela.remanent.Raport_Generuj_Plik_UI_Cmpoments.PrzyciskGenerujPlikRaportDoDruku;
import aniela.remanent.Raport_Logowanie_UI_Components.PoleHasloLogowanie;
import aniela.remanent.Raport_Logowanie_UI_Components.PoleUzytkownikLogowanie;
import aniela.remanent.Raport_Logowanie_UI_Components.PrzyciskZaloguj;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class RaportPlikDruk_IT {

    @Autowired
    private WebDriver driver;


    @Autowired
    private PoleUzytkownikLogowanie poleUzytkownik;
    @Autowired
    private PoleHasloLogowanie poleHaslo;
    @Autowired
    private PrzyciskZaloguj przyciskZaloguj;
    @Autowired
    private PoleRemanentDruk poleRemanentDruk;
    @Autowired
    private PrzyciskGenerujPlikRaportDoDruku przyciskGenerujPlikRaportDoDruku;
    @Autowired
    private ModalExcel modalExcel;



    @Before
    public void setUp(){
        driver.get("http://localhost:8080/raporty.html");
        zaloguj();
    }

    @Test
    public void generujRaportBrutto(){
        String plikZapisany = "plik zapisany";

        czekajNaZnikniecieBlockUI();
        przyciskGenerujPlikRaportDoDruku.kliknij();
        czekajNaZnikniecieBlockUI();
        czekajNaModalDialog();

        assertEquals(true,  modalExcel.pobierzText().contains(plikZapisany));
    }



    private void zaloguj() {
        String uzytkownik = "admin";
        String haslo = "admin";

        poleUzytkownik.wpiszUsera(uzytkownik);
        poleHaslo.wpiszPass(haslo);
        przyciskZaloguj.kliknij();
    }


    private void czekajNaZnikniecieBlockUI(){
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekaj = new WebDriverWait(driver, 20, 2000);
        czekaj.until(ExpectedConditions.stalenessOf(blockUI));
    }

    private void czekajNaModalDialog(){
        WebDriverWait czekaj = new WebDriverWait(driver, 20, 2000);
        czekaj.until(ExpectedConditions.visibilityOf(modalExcel.pobierzWebElement()));
    }


}

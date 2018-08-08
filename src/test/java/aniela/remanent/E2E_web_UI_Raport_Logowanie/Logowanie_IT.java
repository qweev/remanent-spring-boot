package aniela.remanent.E2E_web_UI_Raport_Logowanie;


import aniela.FireFoxConfiguration;
import aniela.remanent.Raport_Logowanie_UI_Components.ModalLogowanie;
import aniela.remanent.Raport_Logowanie_UI_Components.PoleHasloLogowanie;
import aniela.remanent.Raport_Logowanie_UI_Components.PoleUzytkownikLogowanie;
import aniela.remanent.Application;
import aniela.remanent.Raport_Logowanie_UI_Components.PrzyciskZaloguj;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class Logowanie_IT {


    @Autowired
    private  WebDriver driver;

    @Autowired
    private PoleUzytkownikLogowanie poleUzytkownikLogowanie;
    @Autowired
    private PrzyciskZaloguj przyciskZaloguj;
    @Autowired
    private PoleHasloLogowanie poleHasloLogowanie;
    @Autowired
    private ModalLogowanie modalLogowanie;


    @After
    public void cleanUpTest(){
        driver.navigate().refresh();
    }


    @Before
    public void setUp(){
        driver.get("http://localhost:8080/raporty.html");
    }

    @Test
    public void zalogujPoprawnie(){
        String uzytkownik = "admin";
        String haslo = "admin";

        poleUzytkownikLogowanie.wpiszUsera(uzytkownik);
        poleHasloLogowanie.wpiszPass(haslo);
        przyciskZaloguj.kliknij();

        assertEquals(true, czyUdaneLogowanie());
    }


    @Test
    public void zalogujNiepoprawniePoUzytkowniku(){
        String uzytkownik = "zlyadmin";
        String haslo = "admin";
        String nieautoryzowanyDostep = ""+HttpStatus.UNAUTHORIZED.value();

        poleUzytkownikLogowanie.wpiszUsera(uzytkownik);
        poleHasloLogowanie.wpiszPass(haslo);
        przyciskZaloguj.kliknij();

        assertEquals(true, czyNieudaneLogowanie());
        assertEquals(true, modalLogowanie.pobierzText().contains(nieautoryzowanyDostep));
    }


    @Test
    public void zalogujNiepoprawniePoHasle(){
        String uzytkownik = "admin";
        String haslo = "zlehaslo";
        String nieautoryzowanyDostep = ""+HttpStatus.UNAUTHORIZED.value();

        poleUzytkownikLogowanie.wpiszUsera(uzytkownik);
        poleHasloLogowanie.wpiszPass(haslo);
        przyciskZaloguj.kliknij();

        assertEquals(true, czyNieudaneLogowanie());
        System.out.print("!!!!!!!!!!!!!! kod" +ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()));
        assertEquals(true, modalLogowanie.pobierzText().contains(nieautoryzowanyDostep));

    }



    @Test
    public void zalogujBezDanych(){
        String uzytkownik = "";
        String haslo = "";

        poleUzytkownikLogowanie.wpiszUsera(uzytkownik);
        poleHasloLogowanie.wpiszPass(haslo);
        przyciskZaloguj.kliknij();

        assertEquals(true, czyNieudaneLogowanieBezDanych());
        assertEquals(true, modalLogowanie.pobierzText().contains("Wpisz jeszcze raz"));
    }


    private boolean czyUdaneLogowanie() {
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekaj = new WebDriverWait(driver, 20, 2000);
        czekaj.until(ExpectedConditions.stalenessOf(blockUI));
        czekaj.until(ExpectedConditions.invisibilityOfElementLocated(przyciskZaloguj.pobierzPoBy()));
        return true;
    }

    private boolean czyNieudaneLogowanie() {
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekaj = new WebDriverWait(driver, 20, 2000);
        czekaj.until(ExpectedConditions.stalenessOf(blockUI));
        czekaj.until(ExpectedConditions.visibilityOf(modalLogowanie.pobierzPoWebelement()));
        return true;
    }
    private boolean czyNieudaneLogowanieBezDanych() {
        WebDriverWait czekaj = new WebDriverWait(driver, 20, 2000);
        czekaj.until(ExpectedConditions.visibilityOf(modalLogowanie.pobierzPoWebelement()));
        return true;
    }
}

package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleNazwaTowaru;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleUzytkownik;
import org.junit.After;
import org.junit.Before;
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
public class PoleUzytkownik_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private PoleUzytkownik poleUzytkownik;

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
    }

    @Test
    public void wpisanieUzytkownika(){
        String uzytkownik  = "aaa";

        poleUzytkownik.wpiszUzytkownika(uzytkownik);
        poleUzytkownik.zmienFocus();

        assertEquals(uzytkownik, poleUzytkownik.pobierzUzytkownika());
    }

}




package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleJednostka;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleNazwaTowaru;
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
public class PoleNazwaTowaru_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private PoleNazwaTowaru poleNazwaTowaru;

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
    }

    @Test
    public void wpisanieNazwyTowaru(){
        String nazwa  = "aaaaajijij dfdf  dfj";

        poleNazwaTowaru.wpiszNazweTowaru(nazwa);
        poleNazwaTowaru.zmienFocus();

        assertEquals(nazwa, poleNazwaTowaru.pobierzNazweTowaru());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void wpisanieZaDlugiejNazwyTowaru(){
        String nazwaZaDluga  = "aaaaajijij dfdf  dfj sdsdsdsdsdsdsdsd"
                + "sdsdsdsdsdsdsdsdsdsdsd sdsdsdsdsdsddsssdsdsd"
                + "sdsdsdssdssdsds sdsdsdsdsdsdsdsds sdsdsdsdsdsds"
                + "sdsdsdsdsssd sdsdsdsdsd sdsdsdsdssdsd"
                + "sdsdsdsdsdsdsd sdsdsdsdsdsdssdsdsd"
                + "sdsdsdsdsdsdssd sdsdsdsdsdsdsdssdsdd";

        poleNazwaTowaru.wpiszNazweTowaru(nazwaZaDluga);
        poleNazwaTowaru.zmienFocus();

        assertEquals(nazwaZaDluga, poleNazwaTowaru.pobierzNazweTowaru());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void wpisaniePustejNazwyTowaru(){
        String nazwaPusta  = "";

        poleNazwaTowaru.wpiszNazweTowaru(nazwaPusta);
        poleNazwaTowaru.zmienFocus();

        assertEquals(nazwaPusta, poleNazwaTowaru.pobierzNazweTowaru());
        assertEquals(true, zlyWpis());
    }


    @Test
    public void wpisanieNazwyTowaruPrzyWlaczonymCAPSLOCK(){
        String nazwaCAPSLOCK  = "AA BB CC";

        poleNazwaTowaru.wpiszNazweTowaru(nazwaCAPSLOCK);
        poleNazwaTowaru.zmienFocus();

        assertEquals(nazwaCAPSLOCK, poleNazwaTowaru.pobierzNazweTowaru());
        assertEquals(true, zlyWpis());
    }


    private boolean zlyWpis() {
        return poleNazwaTowaru.pobierzStyl().contains(stylCzerwony);
    }


}


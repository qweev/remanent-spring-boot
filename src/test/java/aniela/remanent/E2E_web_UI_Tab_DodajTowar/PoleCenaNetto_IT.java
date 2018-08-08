package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleCenaNetto;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class PoleCenaNetto_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private PoleCenaNetto poleCenaNetto;

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
    }

    @Test
    public void wpisanieCenyNettoZKropka(){
        String cena = "3.14";

        poleCenaNetto.wpiszCeneNetto(cena);
        poleCenaNetto.zmienFocus();

        assertEquals(cena, poleCenaNetto.pobierzCeneNetto());
        assertEquals(false, zlyWpis());
    }



    @Test
    public void wpisanieCenyNettoZPrzecinkiem(){
        String cena = "3,14";

        poleCenaNetto.wpiszCeneNetto(cena);
        poleCenaNetto.zmienFocus();

        assertEquals(cena, poleCenaNetto.pobierzCeneNetto());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void wpisanieCenyNettoZPodwojnymPrzecinkiem(){
        String cena = "3,14,4";

        poleCenaNetto.wpiszCeneNetto(cena);
        poleCenaNetto.zmienFocus();

        assertEquals(cena, poleCenaNetto.pobierzCeneNetto());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void wpisanieCenyNettoZPodwojnaKropka(){
        String cena = "3.14.4";

        poleCenaNetto.wpiszCeneNetto(cena);
        poleCenaNetto.zmienFocus();

        assertEquals(cena, poleCenaNetto.pobierzCeneNetto());
        assertEquals(true, zlyWpis());
    }


    @Test
    public void pustePoleCenaNetto(){
        String cena = "";

        poleCenaNetto.wpiszCeneNetto(cena);
        poleCenaNetto.zmienFocus();

        assertEquals(cena, poleCenaNetto.pobierzCeneNetto());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void nieLiczba(){
        String cena = "3sfff4";

        poleCenaNetto.wpiszCeneNetto(cena);
        poleCenaNetto.zmienFocus();

        assertEquals(cena, poleCenaNetto.pobierzCeneNetto());
        assertEquals(true, zlyWpis());
    }

    private boolean zlyWpis() {
        return poleCenaNetto.pobierzStyl().contains(stylCzerwony);
    }

}

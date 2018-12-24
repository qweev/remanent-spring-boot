package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleCenaBrutto;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.*;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class PoleCenaBrutto_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private PoleCenaBrutto poleCenaBrutto;

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
    }

    @Test
    public void wpisanieCenyBruttoZKropka(){
        String cena = "3.14";

        poleCenaBrutto.wpiszCeneBrutto(cena);
        poleCenaBrutto.zmienFocus();

        assertEquals(cena, poleCenaBrutto.pobierzCeneBrutto());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void wpisanieCenyBruttoZPrzecinkiem(){
        String cena = "3,14";

        poleCenaBrutto.wpiszCeneBrutto(cena);
        poleCenaBrutto.zmienFocus();

        assertEquals(cena, poleCenaBrutto.pobierzCeneBrutto());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void wpisanieCenyBruttoZPodwojnymPrzecinkiem(){
        String cena = "3,14,4";

        poleCenaBrutto.wpiszCeneBrutto(cena);
        poleCenaBrutto.zmienFocus();

        assertEquals(cena, poleCenaBrutto.pobierzCeneBrutto());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void wpisanieCenyBruttoZPodwojnaKropka(){
        String cena = "3.14.4";

        poleCenaBrutto.wpiszCeneBrutto(cena);
        poleCenaBrutto.zmienFocus();

        assertEquals(cena, poleCenaBrutto.pobierzCeneBrutto());
        assertEquals(true, zlyWpis());
    }


    @Test
    public void pustePoleCenaBrutto(){
        String cena = "";

        poleCenaBrutto.wpiszCeneBrutto(cena);
        poleCenaBrutto.zmienFocus();

        assertEquals(cena, poleCenaBrutto.pobierzCeneBrutto());
        assertEquals(true, zlyWpis());
    }



    private boolean zlyWpis() {
        return poleCenaBrutto.pobierzStyl().contains(stylCzerwony);
    }

}

package aniela.remanent.E2E_web_UI_Tab_Zmien;

import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.Zmien_UI_TabComponents.PoleCenaBruttoZmien;
import aniela.remanent.Zmien_UI_TabComponents.PoleCenaNettoZmien;
import aniela.remanent.Zmien_UI_TabComponents.PoleWpiszNumerPozycjiZmien;
import aniela.remanent.Zmien_UI_TabComponents.TabZmien;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
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
public class PoleCenaNetto_IT {



    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private TabZmien tabZmien;
    @Autowired
    private PoleWpiszNumerPozycjiZmien poleWpiszNumerPozycji;
    @Autowired
    private PoleCenaNettoZmien poleCenaNettoZmien;

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
        tabZmien.kliknijTab();
    }

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }


    @Test
    public void wpisanieCenyBruttoZKropka(){
        String cena = "3.45";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);
        poleCenaNettoZmien.wyczysc();

        poleCenaNettoZmien.wpiszCeneBrutto(cena);
        poleCenaNettoZmien.zmienFocus();

        assertEquals(cena, poleCenaNettoZmien.pobierzCeneBrutto());
        assertEquals(false, zlyWpis());
    }


    @Test
    public void wpisanieCenyBruttoZPrzecinkiem(){
        String cena = "3,14";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);
        poleCenaNettoZmien.wyczysc();

        poleCenaNettoZmien.wpiszCeneBrutto(cena);
        poleCenaNettoZmien.zmienFocus();

        assertEquals(cena, poleCenaNettoZmien.pobierzCeneBrutto());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void wpisanieCenyBruttoZPodwojnymPrzecinkiem(){
        String cena = "3,14,4";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);
        poleCenaNettoZmien.wyczysc();

        poleCenaNettoZmien.wpiszCeneBrutto(cena);
        poleCenaNettoZmien.zmienFocus();

        assertEquals(cena, poleCenaNettoZmien.pobierzCeneBrutto());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void wpisanieCenyBruttoZPodwojnaKropka(){
        String cena = "3.14.4";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);
        poleCenaNettoZmien.wyczysc();

        poleCenaNettoZmien.wpiszCeneBrutto(cena);
        poleCenaNettoZmien.zmienFocus();

        assertEquals(cena, poleCenaNettoZmien.pobierzCeneBrutto());
        assertEquals(true, zlyWpis());
    }


    @Test
    public void pustePoleCenaBrutto(){
        String cena = "";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);
        poleCenaNettoZmien.wyczysc();

        poleCenaNettoZmien.wpiszCeneBrutto(cena);
        poleCenaNettoZmien.zmienFocus();

        assertEquals(cena, poleCenaNettoZmien.pobierzCeneBrutto());
        assertEquals(true, zlyWpis());
    }

    private void pobierzPozycje(String numerPozycji) {
        poleWpiszNumerPozycji.wpiszNumerPozycji(numerPozycji);
        poleWpiszNumerPozycji.kliknijPrzycisk();
        WebDriverWait czekajNaPobranie = new WebDriverWait(driver, 20, 2000);
        czekajNaPobranie.until(ExpectedConditions.visibilityOf(poleCenaNettoZmien.pobierzPoWebElement()));
    }


    private boolean zlyWpis() {
        return poleCenaNettoZmien.pobierzStyl().contains(stylCzerwony);
    }

}

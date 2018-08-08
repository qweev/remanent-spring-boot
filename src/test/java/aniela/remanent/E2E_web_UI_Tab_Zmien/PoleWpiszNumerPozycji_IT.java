package aniela.remanent.E2E_web_UI_Tab_Zmien;

import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.Zmien_UI_TabComponents.PoleUzytkownikZmien;
import aniela.remanent.Zmien_UI_TabComponents.PoleWpiszNumerPozycjiZmien;
import aniela.remanent.Zmien_UI_TabComponents.TabZmien;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class PoleWpiszNumerPozycji_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private TabZmien tabZmien;

    @Autowired
    private PoleWpiszNumerPozycjiZmien poleWpiszNumerPozycjiZmien;

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
    public void wpisaniePoprawnegoNumeruPozycji(){
        String numerPozycji  = "1";
        tabZmien.kliknijTab();

        poleWpiszNumerPozycjiZmien.wpiszNumerPozycji(numerPozycji);
        poleWpiszNumerPozycjiZmien.zmienFocus();

        assertEquals(numerPozycji, poleWpiszNumerPozycjiZmien.pobierzNumerWpisanejPozycji());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void wpisaniePustegoNumeruPozycji(){
        String pustyNumerPozycji  = "";
        tabZmien.kliknijTab();

        poleWpiszNumerPozycjiZmien.wpiszNumerPozycji(pustyNumerPozycji);
        poleWpiszNumerPozycjiZmien.zmienFocus();

        assertEquals(pustyNumerPozycji, poleWpiszNumerPozycjiZmien.pobierzNumerWpisanejPozycji());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void wpisanieZlegoNumeruPozycji(){
        String zlyNumerPozycji  = "zly";
        tabZmien.kliknijTab();

        poleWpiszNumerPozycjiZmien.wpiszNumerPozycji(zlyNumerPozycji);
        poleWpiszNumerPozycjiZmien.zmienFocus();

        assertEquals(zlyNumerPozycji, poleWpiszNumerPozycjiZmien.pobierzNumerWpisanejPozycji());
        assertEquals(true, zlyWpis());
    }

    private boolean zlyWpis() {
        return poleWpiszNumerPozycjiZmien.pobierzStyl().contains(stylCzerwony);
    }
}

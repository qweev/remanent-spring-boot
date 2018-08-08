package aniela.remanent.E2E_web_UI_Tab_Zmien;

import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.Zmien_UI_TabComponents.PoleJednostkaZmien;
import aniela.remanent.Zmien_UI_TabComponents.PoleNazwaTowaruZmien;
import aniela.remanent.Zmien_UI_TabComponents.PoleWpiszNumerPozycjiZmien;
import aniela.remanent.Zmien_UI_TabComponents.TabZmien;
import org.junit.After;
import org.junit.Before;
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
public class PoleNazwaTowaru_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private TabZmien tabZmien;
    @Autowired
    private PoleNazwaTowaruZmien poleNazwaTowaruZmien;
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
    public void zmianaNazwyTowaru(){
        String nazwaTowaruDoDopisania = "zmien nazwe towaru";
        String nazwaTowaruPoZmianie = "zmien Pozycje e2ezmien nazwe towaru";
        String numerPozycji  = "6";

        pobierzPozycje(numerPozycji);

        poleNazwaTowaruZmien.wpiszNazweTowaru(nazwaTowaruDoDopisania);
        poleNazwaTowaruZmien.zmienFocus();

        assertEquals(nazwaTowaruPoZmianie ,poleNazwaTowaruZmien.pobierzNazweTowaru());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void zmianaNazwyTowaruNaZadluga(){
        String doDopisaniaWiecejNiz50znakow = "zmien nazwe towaru zmien nazwe towaru zmien nazwe towaru zmien nazwe towaru"
                + "zmien nazwe towaru zmien nazwe towaru zmien nazwe towaru zmien nazwe towaru zmien nazwe towaru zmien nazwe towaru";
        String nazwaTowaruPoZmianie = "zmien Pozycje e2e" + doDopisaniaWiecejNiz50znakow;
        String numerPozycji  = "6";
        pobierzPozycje(numerPozycji);

        poleNazwaTowaruZmien.wpiszNazweTowaru(doDopisaniaWiecejNiz50znakow);
        poleNazwaTowaruZmien.zmienFocus();

        assertEquals(nazwaTowaruPoZmianie ,poleNazwaTowaruZmien.pobierzNazweTowaru());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void zmianaNazwyTowaruNaPusta(){
        String nazwaTowaruPoZmianie = "";
        String numerPozycji  = "6";

        pobierzPozycje(numerPozycji);

        poleNazwaTowaruZmien.wyczysc();
        poleNazwaTowaruZmien.zmienFocus();

        assertEquals(nazwaTowaruPoZmianie ,poleNazwaTowaruZmien.pobierzNazweTowaru());
        assertEquals(true, zlyWpis());
    }

    private void pobierzPozycje(String numerPozycji) {
        poleWpiszNumerPozycjiZmien.wpiszNumerPozycji(numerPozycji);
        poleWpiszNumerPozycjiZmien.kliknijPrzycisk();
        WebDriverWait czekajNaPobranie = new WebDriverWait(driver, 20, 2000);
        czekajNaPobranie.until(ExpectedConditions.visibilityOf(poleNazwaTowaruZmien.pobierzPoWebElement()));

    }

    private boolean zlyWpis() {
        return poleNazwaTowaruZmien.pobierzStyl().contains(stylCzerwony);
    }

}

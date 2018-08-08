package aniela.remanent.E2E_web_UI_Tab_Usun;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.UsunTowar_UI_TabComponents.PolePobierzPozycjeDoUsun;
import aniela.remanent.UsunTowar_UI_TabComponents.TabUsun;
import aniela.remanent.Zmien_UI_TabComponents.PoleCenaBruttoZmien;
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
public class PobierzDoUsun_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private TabUsun tabUsun;
    @Autowired
    private PolePobierzPozycjeDoUsun polePobierzPozycjeDoUsun;

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
        tabUsun.kliknijTab();
    }

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }


    @Test
    public void wpisaniePoprawnegoNumeruPozycji(){
        String numerPozycji = "8";

        polePobierzPozycjeDoUsun.wpiszNumerPozycjiDoUsuniecia(numerPozycji);
        polePobierzPozycjeDoUsun.zmienFocus();

        assertEquals(numerPozycji, polePobierzPozycjeDoUsun.pobierzNumerPozycjiDoUsuniecia());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void wpisanieZlegoNumeruPozycji(){
        String zlyNumerPozycji = "1a";

        polePobierzPozycjeDoUsun.wpiszNumerPozycjiDoUsuniecia(zlyNumerPozycji);
        polePobierzPozycjeDoUsun.zmienFocus();

        assertEquals(zlyNumerPozycji, polePobierzPozycjeDoUsun.pobierzNumerPozycjiDoUsuniecia());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void wpisanieNumeruPozycjiZKropka(){
        String zlyNumerPozycjiZKropka = "1.8";

        polePobierzPozycjeDoUsun.wpiszNumerPozycjiDoUsuniecia(zlyNumerPozycjiZKropka);
        polePobierzPozycjeDoUsun.zmienFocus();

        assertEquals(zlyNumerPozycjiZKropka, polePobierzPozycjeDoUsun.pobierzNumerPozycjiDoUsuniecia());
        assertEquals(true, zlyWpis());
    }

    @Test
    public void wpisanieNumeruPozycjiZPrzecinkiem(){
        String zlyNumerPozycjiZPrzecinkiem = "1,8";

        polePobierzPozycjeDoUsun.wpiszNumerPozycjiDoUsuniecia(zlyNumerPozycjiZPrzecinkiem);
        polePobierzPozycjeDoUsun.zmienFocus();

        assertEquals(zlyNumerPozycjiZPrzecinkiem, polePobierzPozycjeDoUsun.pobierzNumerPozycjiDoUsuniecia());
        assertEquals(true, zlyWpis());
    }

    private boolean zlyWpis() {
        return polePobierzPozycjeDoUsun.pobierzStyl().contains(stylCzerwony);
    }

}

package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleIlosc;
import org.junit.Test;
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
public class PoleIlosc_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private PoleIlosc poleIlosc;

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
    }

    @Test
    public void wpisanieIlosc(){
        String ilosc  = "5";

        poleIlosc.wpiszIlosc(ilosc);

        assertEquals(ilosc, poleIlosc.pobierzIlosc());
        assertEquals(false, zlyWpis());
    }

    @Test
    public void wpisanieIloscZKropka(){
        String ilosc = "3.14";

        poleIlosc.wpiszIlosc(ilosc);
        poleIlosc.zmienFocus();

        assertEquals(ilosc, poleIlosc.pobierzIlosc());
        assertEquals(false, zlyWpis());
    }


    @Test
    public void wpisanieIlosciZPrzecinkiem(){
        String ilosc = "3,14";

        poleIlosc.wpiszIlosc(ilosc);
        poleIlosc.zmienFocus();

        assertEquals(ilosc, poleIlosc.pobierzIlosc());
        assertEquals(false, zlyWpis());
    }


    @Test
    public void wpisanieIlosciZDwomaPrzecinkami(){
        String ilosc = "3,14,3";

        poleIlosc.wpiszIlosc(ilosc);
        poleIlosc.zmienFocus();

        assertEquals(ilosc, poleIlosc.pobierzIlosc());
        assertEquals(true, zlyWpis());
    }


    @Test
    public void wpisanieIlosciZDwomaKropkami(){
        String ilosc = "3.14.3";

        poleIlosc.wpiszIlosc(ilosc);
        poleIlosc.zmienFocus();

        assertEquals(ilosc, poleIlosc.pobierzIlosc());
        assertEquals(true, zlyWpis());
    }


    @Test
    public void wpisanieNieLiczby(){
        String ilosc = "3aaa4";

        poleIlosc.wpiszIlosc(ilosc);
        poleIlosc.zmienFocus();

        assertEquals(ilosc, poleIlosc.pobierzIlosc());
        assertEquals(true, zlyWpis());
    }


    @Test
    public void pustePoleIlosc(){
        String ilosc = "";

        poleIlosc.wpiszIlosc(ilosc);
        poleIlosc.zmienFocus();

        assertEquals(ilosc, poleIlosc.pobierzIlosc());
        assertEquals(true, zlyWpis());
    }


    private boolean zlyWpis() {
        return poleIlosc.pobierzStyl().contains(stylCzerwony);
    }


}

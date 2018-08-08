package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleIlosc;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleJednostka;
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
public class PoleJednostka_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private PoleJednostka poleJednostka;

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
    }

    @Test
    public void wybierzSztuki(){
        String wybor  = "szt.";

        poleJednostka.wybierz(wybor);

        assertEquals(wybor, poleJednostka.zwrocWybranaJednostke());
    }

    @Test
    public void wybierzJednostkeOpakowania(){
        String wybor  = "op.";

        poleJednostka.wybierz(wybor);

        assertEquals(wybor, poleJednostka.zwrocWybranaJednostke());
    }

    @Test
    public void wybierzJednostkeKomplet(){
        String wybor  = "kpl.";

        poleJednostka.wybierz(wybor);

        assertEquals(wybor, poleJednostka.zwrocWybranaJednostke());
    }

    @Test
    public void wybierzJednostkeMB(){
        String wybor  = "mb.";

        poleJednostka.wybierz(wybor);

        assertEquals(wybor, poleJednostka.zwrocWybranaJednostke());
    }

    @Test
    public void wybierzJednostkePar(){
        String wybor  = "par";

        poleJednostka.wybierz(wybor);

        assertEquals(wybor, poleJednostka.zwrocWybranaJednostke());
    }


}

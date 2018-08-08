package aniela.remanent.E2E_web_UI_Tab_DodajTowar;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.DodajTowar_UI_TabComponents.PoleFiltrujTabelke;
import aniela.remanent.DodajTowar_UI_TabComponents.TabelkaHistoriaWpisow;
import org.junit.After;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.*;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class PoleFiltrujTabelke_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private PoleFiltrujTabelke poleFiltrujTabelke;
    @Autowired
    private TabelkaHistoriaWpisow tabelkaHistoriaWpisow;

//    @After
//    public void cleanUpTest(){
//        driver.navigate().refresh();
//    }

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
    }

    @Test
    public void wpisanieFrazyDoFiltra(){
        String fraza = "filtr test";

        poleFiltrujTabelke.wpiszDoFiltra(fraza);

        assertEquals(fraza, poleFiltrujTabelke.pobierzZFiltra(fraza));
    }

    @Test
    public void filtrujTabelkeHistriaWpisow(){
        String filtr = "filtr test";
        int iloscWidocznychWierszy = 2 ;
        tabelkaHistoriaWpisow.stworzSztucznaHistorie5Wpisow();

        poleFiltrujTabelke.wpiszDoFiltra(filtr);

        assertEquals(iloscWidocznychWierszy, tabelkaHistoriaWpisow.liczbaWidocznychWierszy());
    }


}

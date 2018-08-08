package aniela.remanent.E2E_web_UI_Tab_Zmien;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.Zmien_UI_TabComponents.PoleJednostkaZmien;
import aniela.remanent.Zmien_UI_TabComponents.PoleWpiszNumerPozycjiZmien;
import aniela.remanent.Zmien_UI_TabComponents.TabZmien;
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
public class PoleJednostka_IT {


    @Autowired
    private WebDriver driver;

    private String stylCzerwony = "czerwony";

    @Autowired
    private TabZmien tabZmien;
    @Autowired
    private PoleJednostkaZmien poleJednostkaZmien;
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
    public void wybierzSztuki(){
        String wybor  = "szt.";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);

        poleJednostkaZmien.wybierz(wybor);

        assertEquals(wybor, poleJednostkaZmien.zwrocWybranaJednostke());
    }

    @Test
    public void wybierzJednostkeOpakowania(){
        String wybor  = "op.";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);

        poleJednostkaZmien.wybierz(wybor);

        assertEquals(wybor, poleJednostkaZmien.zwrocWybranaJednostke());
    }

    @Test
    public void wybierzJednostkeKomplet(){
        String wybor  = "kpl.";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);

        poleJednostkaZmien.wybierz(wybor);

        assertEquals(wybor, poleJednostkaZmien.zwrocWybranaJednostke());
    }

    @Test
    public void wybierzJednostkeMB(){
        String wybor  = "mb.";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);

        poleJednostkaZmien.wybierz(wybor);

        assertEquals(wybor, poleJednostkaZmien.zwrocWybranaJednostke());
    }

    @Test
    public void wybierzJednostkePar(){
        String wybor  = "par";
        String numerPozycji = "6";
        pobierzPozycje(numerPozycji);

        poleJednostkaZmien.wybierz(wybor);

        assertEquals(wybor, poleJednostkaZmien.zwrocWybranaJednostke());
    }

    private void pobierzPozycje(String numerPozycji) {
        poleWpiszNumerPozycjiZmien.wpiszNumerPozycji(numerPozycji);
        poleWpiszNumerPozycjiZmien.kliknijPrzycisk();
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekajNaPobranie = new WebDriverWait(driver, 20, 2000);
        czekajNaPobranie.until(ExpectedConditions.stalenessOf(blockUI));
        czekajNaPobranie.until(ExpectedConditions.elementToBeClickable(poleJednostkaZmien.pobierzPoWebElement()));
    }



}

package aniela.remanent.E2E_web_UI_Tab_Szukaj;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.SzukajTowarProste_UI_TabComponents.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class SortowanieTabelki_IT {


    @Autowired
    private  WebDriver driver;

    @Autowired
    private SzukajTab szukajTab;
    @Autowired
    private PoleSzukajPoUzytkowniku poleSzukajPoUzytkowniku;
    @Autowired
    private PoleSzukajPoNazwie poleSzukajPoNazwie;
    @Autowired
    private PoleSzukajPozycji poleSzukajPozycji;
    @Autowired
    private PrzyciskSzukajPozycji przyciskSzukajPozycji;
    @Autowired
    private TabelkaWynikiSzukaj tabelkaWynikiSzukaj;
    @Autowired
    private PoleFiltrSzukaj poleFiltrSzukaj;

    @Before
    public void setUp(){
        driver.get("http://localhost:8080/index.html");
        szukajTab.kliknijTab();
        String nazwaDoSzukania = "sortuj";
        poleSzukajPoUzytkowniku.kliknij();
        poleSzukajPozycji.wpiszTekst(nazwaDoSzukania);
        przyciskSzukajPozycji.kliknijSzukaj();
        czekajNaWynikiWTabelce();
    }


    @Test
    public void sortujPoIdMalejaco(){
        tabelkaWynikiSzukaj.kliknijKolumneNumer();
        waitForJavaScriptToFinish(3);
        tabelkaWynikiSzukaj.kliknijKolumneNumer();
        waitForJavaScriptToFinish(3);

       List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
       List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
       String idPierwszePrzedSort = pierwszyWierszPrzedSort.get(0).getText(); // id
       String idOstatniePrzedSort = ostatniWierszPrzedSort.get(0).getText(); // id

       tabelkaWynikiSzukaj.kliknijKolumneNumer();
       waitForJavaScriptToFinish(3);
       List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
       List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
       String idPierwszePoSort = pierwszyWierszPoSort.get(0).getText(); // id
       String idOstatniePoSort = ostatniWierszPoSort.get(0).getText(); // id

       assertEquals(idPierwszePrzedSort, idOstatniePoSort);
       assertEquals(idOstatniePrzedSort, idPierwszePoSort);
       assertTrue(Integer.valueOf(idPierwszePoSort).intValue() < Integer.valueOf(idOstatniePoSort).intValue() );
    }

    @Test
    public void sortujPoIdRosnaco(){
        tabelkaWynikiSzukaj.kliknijKolumneNumer();
        waitForJavaScriptToFinish(3);
        tabelkaWynikiSzukaj.kliknijKolumneNumer();
        waitForJavaScriptToFinish(3);
        tabelkaWynikiSzukaj.kliknijKolumneNumer();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String idPierwszePrzedSort = pierwszyWierszPrzedSort.get(0).getText(); // id
        String idOstatniePrzedSort = ostatniWierszPrzedSort.get(0).getText(); // id

        tabelkaWynikiSzukaj.kliknijKolumneNumer();
        waitForJavaScriptToFinish(3);
        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String idPierwszePoSort = pierwszyWierszPoSort.get(0).getText(); // id
        String idOstatniePoSort = ostatniWierszPoSort.get(0).getText(); // id

        assertEquals(idPierwszePrzedSort, idOstatniePoSort);
        assertEquals(idOstatniePrzedSort, idPierwszePoSort);
        assertTrue(Integer.valueOf(idPierwszePoSort).intValue() > Integer.valueOf(idOstatniePoSort).intValue() );
    }


    @Test
    public void sortujPoNazwaMalejaco(){
        tabelkaWynikiSzukaj.kliknijKolumneNazwa();
        waitForJavaScriptToFinish(3);
        tabelkaWynikiSzukaj.kliknijKolumneNazwa();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String nazwaPierwszePrzedSort = pierwszyWierszPrzedSort.get(1).getText(); // nazwa
        String nazwaOstatniePrzedSort = ostatniWierszPrzedSort.get(1).getText(); // nazwa

        tabelkaWynikiSzukaj.kliknijKolumneNazwa();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String nazwaPierwszePoSort = pierwszyWierszPoSort.get(1).getText(); // nazwa
        String nazwaOstatniePoSort = ostatniWierszPoSort.get(1).getText(); // nazwa

        assertEquals(nazwaPierwszePrzedSort, nazwaOstatniePoSort);
        assertEquals(nazwaOstatniePrzedSort, nazwaPierwszePoSort);
    }

    @Test
    public void sortujPoNazwaRosnaco(){
        tabelkaWynikiSzukaj.kliknijKolumneNazwa();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String nazwaPierwszePrzedSort = pierwszyWierszPrzedSort.get(1).getText(); // nazwa
        String nazwaOstatniePrzedSort = ostatniWierszPrzedSort.get(1).getText(); // nazwa

        tabelkaWynikiSzukaj.kliknijKolumneNazwa();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String nazwaPierwszePoSort = pierwszyWierszPoSort.get(1).getText(); // nazwa
        String nazwaOstatniePoSort = ostatniWierszPoSort.get(1).getText(); // nazwa

        assertEquals(nazwaPierwszePrzedSort, nazwaOstatniePoSort);
        assertEquals(nazwaOstatniePrzedSort, nazwaPierwszePoSort);
    }

    @Test
    public void sortujPoBruttoMalejaco(){
        tabelkaWynikiSzukaj.kliknijKolumneBrutto();
        waitForJavaScriptToFinish(3);
        tabelkaWynikiSzukaj.kliknijKolumneBrutto();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String bruttoPierwszePrzedSort = pierwszyWierszPrzedSort.get(2).getText();
        String bruttoOstatniePrzedSort = ostatniWierszPrzedSort.get(2).getText();

        tabelkaWynikiSzukaj.kliknijKolumneBrutto();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String bruttoPierwszePoSort = pierwszyWierszPoSort.get(2).getText();
        String bruttoOstatniePoSort = ostatniWierszPoSort.get(2).getText();

        assertEquals(bruttoPierwszePrzedSort, bruttoOstatniePoSort);
        assertEquals(bruttoOstatniePrzedSort, bruttoPierwszePoSort);
    }

    @Test
    public void sortujPoBruttoRosnaco(){
        tabelkaWynikiSzukaj.kliknijKolumneBrutto();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String bruttoPierwszePrzedSort = pierwszyWierszPrzedSort.get(2).getText();
        String bruttoOstatniePrzedSort = ostatniWierszPrzedSort.get(2).getText();

        tabelkaWynikiSzukaj.kliknijKolumneBrutto();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String bruttoPierwszePoSort = pierwszyWierszPoSort.get(2).getText(); // nazwa
        String bruttoOstatniePoSort = ostatniWierszPoSort.get(2).getText(); // nazwa

        assertEquals(bruttoPierwszePrzedSort, bruttoOstatniePoSort);
        assertEquals(bruttoOstatniePrzedSort, bruttoPierwszePoSort);
    }

    @Test
    public void sortujPoNettoMalejaco(){
        tabelkaWynikiSzukaj.kliknijKolumneNetto();
        waitForJavaScriptToFinish(3);
        tabelkaWynikiSzukaj.kliknijKolumneNetto();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String nettoPierwszePrzedSort = pierwszyWierszPrzedSort.get(3).getText();
        String nettoOstatniePrzedSort = ostatniWierszPrzedSort.get(3).getText();

        tabelkaWynikiSzukaj.kliknijKolumneNetto();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String nettoPierwszePoSort = pierwszyWierszPoSort.get(3).getText();
        String nettoOstatniePoSort = ostatniWierszPoSort.get(3).getText();

        assertEquals(nettoPierwszePrzedSort, nettoOstatniePoSort);
        assertEquals(nettoOstatniePrzedSort, nettoPierwszePoSort);
    }

    @Test
    public void sortujPoNettoRosnaco(){
        tabelkaWynikiSzukaj.kliknijKolumneNetto();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String nettoPierwszePrzedSort = pierwszyWierszPrzedSort.get(3).getText();
        String nettoOstatniePrzedSort = ostatniWierszPrzedSort.get(3).getText();

        tabelkaWynikiSzukaj.kliknijKolumneNetto();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String nettoPierwszePoSort = pierwszyWierszPoSort.get(3).getText();
        String nettoOstatniePoSort = ostatniWierszPoSort.get(3).getText();

        assertEquals(nettoPierwszePrzedSort, nettoOstatniePoSort);
        assertEquals(nettoOstatniePrzedSort, nettoPierwszePoSort);
    }



    @Test
    public void sortujPoIloscMalejaco(){
        tabelkaWynikiSzukaj.kliknijKolumneIlosc();
        waitForJavaScriptToFinish(3);
        tabelkaWynikiSzukaj.kliknijKolumneIlosc();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String iloscPierwszePrzedSort = pierwszyWierszPrzedSort.get(5).getText();
        String iloscOstatniePrzedSort = ostatniWierszPrzedSort.get(5).getText();

        tabelkaWynikiSzukaj.kliknijKolumneIlosc();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String iloscPierwszePoSort = pierwszyWierszPoSort.get(5).getText();
        String iloscOstatniePoSort = ostatniWierszPoSort.get(5).getText();

        assertEquals(iloscPierwszePrzedSort, iloscOstatniePoSort);
        assertEquals(iloscOstatniePrzedSort, iloscPierwszePoSort);
    }

    @Test
    public void sortujPoIloscRosnaco(){
        tabelkaWynikiSzukaj.kliknijKolumneIlosc();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPrzedSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String iloscPierwszePrzedSort = pierwszyWierszPrzedSort.get(5).getText();
        String iloscOstatniePrzedSort = ostatniWierszPrzedSort.get(5).getText();

        tabelkaWynikiSzukaj.kliknijKolumneIlosc();
        waitForJavaScriptToFinish(3);

        List <WebElement> pierwszyWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(1);
        List <WebElement> ostatniWierszPoSort = tabelkaWynikiSzukaj.pobierzWierszTabeli(4);
        String iloscPierwszePoSort = pierwszyWierszPoSort.get(5).getText();
        String iloscOstatniePoSort = ostatniWierszPoSort.get(5).getText();

        assertEquals(iloscPierwszePrzedSort, iloscOstatniePoSort);
        assertEquals(iloscOstatniePrzedSort, iloscPierwszePoSort);
    }


    private void czekajNaWynikiWTabelce() {
        WebElement blockUI = driver.findElement(By.className("blockUI"));
        WebDriverWait czekaj = new WebDriverWait(driver, 20, 2000);
        czekaj.until(ExpectedConditions.stalenessOf(blockUI));
        czekaj.until(ExpectedConditions.elementToBeClickable(przyciskSzukajPozycji.pobierzWebElement()));
    }


    private void waitForJavaScriptToFinish(int timeoutInSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(timeoutInSeconds, TimeUnit.SECONDS)
                    .pollingEvery(500, TimeUnit.MILLISECONDS)
                    .withMessage("###### Wait for page to be ready failed, trying to finish test execution anyway");

            wait.until(ExpectedConditions.jsReturnsValue("return (document.readyState == 'complete' && jQuery.active == 0)"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

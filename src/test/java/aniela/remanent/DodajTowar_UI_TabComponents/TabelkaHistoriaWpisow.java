package aniela.remanent.DodajTowar_UI_TabComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class TabelkaHistoriaWpisow {

    private WebDriver driver;
    @FindBy(xpath="//tbody[@id='tabelka']/tr")
    private List<WebElement> wiersze;

    @Autowired
    public TabelkaHistoriaWpisow(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    public long liczbaWidocznychWierszy(){

        return wiersze.stream()
                .filter(x -> x.getAttribute("style").equalsIgnoreCase("") || x.getAttribute("style").isEmpty() )
                .count();

    }


    public void stworzSztucznaHistorie5Wpisow() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String sztucznaHistoria = "document.getElementById('tabelka').innerHTML = '<tr><td>filtr test</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td></tr><tr><td>b</td><td>b</td><td>b</td><td>b</td><td>b</td><td>b</td><td>b</td></tr><tr><td>c</td><td>c</td><td>c</td><td>c</td><td>c</td><td>c</td><td>c</td></tr><tr><td>filtr test</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td></tr><tr><td>d</td><td>d</td><td>d</td><td>d</td><td>d</td><td>d</td><td>d</td></tr>'";
        js.executeScript(sztucznaHistoria);

    }

    public List<WebElement> zbierzKomorkiPozycjiZWierszaTabelki(int numerWiersza){
        List<WebElement> komorki = new ArrayList<WebElement>();
        int pierwszaKomorkaPozycjiWTabelce = 2;
        int ostatniaKomorkaPozycjiWTabelce = 8;

        new WebDriverWait(driver, Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='tabelka']/tr["+numerWiersza+"]")));

        for(int komorka=pierwszaKomorkaPozycjiWTabelce; komorka < ostatniaKomorkaPozycjiWTabelce; komorka++){
            komorki.add(driver.findElement(By.xpath("//tbody[@id='tabelka']/tr["+numerWiersza+"]/td["+komorka+"]")));
        }

        return komorki;
    }

}

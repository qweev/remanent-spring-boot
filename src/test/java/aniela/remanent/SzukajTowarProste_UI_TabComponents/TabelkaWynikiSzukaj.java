package aniela.remanent.SzukajTowarProste_UI_TabComponents;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelkaWynikiSzukaj {

    private WebDriver driver;
    @FindBy(xpath="//tbody[@id='tabelkaSzukaj']/tr")
    private List<WebElement> wiersze;

    @Autowired
    public TabelkaWynikiSzukaj(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    // jeden wiersz pusty by default
    public long liczbaWidocznychWierszy(){
//        int liczba = 0;
//        for (WebElement wiersz: wiersze){
//            String attr= wiersz.getAttribute("style");
//            if ( attr.equals("") | attr == null){
//                liczba++;
//            }
//        }
//
//        return liczba;

        return wiersze.stream()
                .filter(x -> x.getAttribute("style").equalsIgnoreCase("") || x.getAttribute("style").isEmpty() )
                .count();

    }

    public int liczbaWierszy(){
        return wiersze.size();
    }


    public void stworzSztuczneWynikiSzukania() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String sztuczneWyniki = "document.getElementById('tabelkaSzukaj').innerHTML = '<tr><td>filtr test</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td></tr><tr><td>b</td><td>b</td><td>b</td><td>b</td><td>b</td><td>b</td><td>b</td></tr><tr><td>c</td><td>c</td><td>c</td><td>c</td><td>c</td><td>c</td><td>c</td></tr><tr><td>filtr test</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td></tr><tr><td>d</td><td>d</td><td>d</td><td>d</td><td>d</td><td>d</td><td>d</td></tr>'";
        js.executeScript(sztuczneWyniki);

    }
}
package aniela.remanent.SzukajTowarProste_UI_TabComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TabelkaWynikiSzukaj {

    private WebDriver driver;
    @FindBy(xpath="//tbody[@id='tabelkaSzukaj']/tr")
    private List<WebElement> wiersze;

    @FindBy(id="numerKolumnaWSzukaj")
    private WebElement numerKolumnaWSzukaj;

    @FindBy(id="nazwaTowaruKolumnaWSzukaj")
    private WebElement nazwaTowaruKolumnaWSzukaj;

    @FindBy(id="cenaBruttoKolumnaWSzukaj")
    private WebElement cenaBruttoKolumnaWSzukaj;

    @FindBy(id="cenaNettoKolumnaWSzukaj")
    private WebElement cenaNettoKolumnaWSzukaj;

    @FindBy(id="jednostkaKolumnaWSzukaj")
    private WebElement jednostkaKolumnaWSzukaj;

    @FindBy(id="iloscKolumnaWSzukaj")
    private WebElement iloscKolumnaWSzukaj;

    @FindBy(id="uzytkownikKolumnaWSzukaj")
    private WebElement uzytkownikKolumnaWSzukaj;

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


    public void kliknijKolumneNumer(){
        numerKolumnaWSzukaj.click();
    }

    public void kliknijKolumneNazwa(){
        nazwaTowaruKolumnaWSzukaj.click();
    }

    public void kliknijKolumneBrutto(){
        cenaBruttoKolumnaWSzukaj.click();
    }

    public void kliknijKolumneNetto(){
        cenaNettoKolumnaWSzukaj.click();
    }

    public void kliknijKolumneIlosc(){
        iloscKolumnaWSzukaj.click();
    }

    public void kliknijKolumneUzytkownik(){
        uzytkownikKolumnaWSzukaj.click();
    }


    public List<WebElement> pobierzWierszTabeli(int numerWiersza){
        List<WebElement> komorki = new ArrayList<WebElement>();
        int pierwszaKomorkaPozycjiWTabelce = 1;
        int ostatniaKomorkaPozycjiWTabelce = 8;

        for(int komorka=pierwszaKomorkaPozycjiWTabelce; komorka < ostatniaKomorkaPozycjiWTabelce; komorka++){
            komorki.add(driver.findElement(By.xpath("//tbody[@id='tabelkaSzukaj']/tr["+numerWiersza+"]/td["+komorka+"]")));
        }

        return komorki;

    }

}

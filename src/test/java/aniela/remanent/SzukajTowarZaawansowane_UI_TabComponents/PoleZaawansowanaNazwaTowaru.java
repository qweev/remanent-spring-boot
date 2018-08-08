package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoleZaawansowanaNazwaTowaru {
    private WebDriver driver;
    @FindBy(id = "zaaNazwaTowaruPole")
    private WebElement poleZaawansowanaNazwaTowaru;

    @Autowired
    public PoleZaawansowanaNazwaTowaru( WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszTekst(String tekst){
        poleZaawansowanaNazwaTowaru.sendKeys(tekst);
    }

    public String pobierzTekst(){
        return poleZaawansowanaNazwaTowaru.getAttribute("value");
    }

    public void zmienFocus(){
        poleZaawansowanaNazwaTowaru.sendKeys(Keys.TAB);
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zaaNazwaTowaruPole').val('')");
    }

    public WebElement pobierzPoWebElement() {
        return poleZaawansowanaNazwaTowaru;
    }
}


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
public class PoleZaawansowaIlosc {
    private WebDriver driver;
    @FindBy(id = "zaaIloscPole")
    private WebElement poleZaawansowaIlosc;

    @Autowired
    public PoleZaawansowaIlosc( WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszTekst(String tekst){
        poleZaawansowaIlosc.sendKeys(tekst);
    }

    public String pobierzTekst(){
        return poleZaawansowaIlosc.getAttribute("value");
    }

    public void zmienFocus(){
        poleZaawansowaIlosc.sendKeys(Keys.TAB);
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zaaIloscPole').val('')");
    }

    public WebElement pobierzPoWebElement() {
        return poleZaawansowaIlosc;
    }
}

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
public class PoleZaawansowanyUzytkownik {


    private WebDriver driver;
    @FindBy(id = "zaaUzytkownikPole")
    private WebElement poleZaawansowanyUzytkownik;

    @Autowired
    public PoleZaawansowanyUzytkownik( WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszTekst(String tekst){
        poleZaawansowanyUzytkownik.sendKeys(tekst);
    }

    public String pobierzTekst(){
        return poleZaawansowanyUzytkownik.getAttribute("value");
    }

    public void zmienFocus(){
        poleZaawansowanyUzytkownik.sendKeys(Keys.TAB);
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zaaUzytkownikPole').val('')");
    }

    public WebElement pobierzPoWebElement() {
        return poleZaawansowanyUzytkownik;
    }
}

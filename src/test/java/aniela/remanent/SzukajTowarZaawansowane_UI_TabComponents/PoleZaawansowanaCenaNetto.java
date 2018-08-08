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
public class PoleZaawansowanaCenaNetto {
    private WebDriver driver;
    @FindBy(id = "zaaCenaNettoPole")
    private WebElement poleZaawansowanaCenaNetto;

    @Autowired
    public PoleZaawansowanaCenaNetto( WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszTekst(String tekst){
        poleZaawansowanaCenaNetto.sendKeys(tekst);
    }

    public String pobierzTekst(){
        return poleZaawansowanaCenaNetto.getAttribute("value");
    }

    public void zmienFocus(){
        poleZaawansowanaCenaNetto.sendKeys(Keys.TAB);
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zaaCenaNettoPole').val('')");
    }

    public WebElement pobierzPoWebElement() {
        return poleZaawansowanaCenaNetto;
    }
}

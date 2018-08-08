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
public class PoleZaawansowanaCenaBrutto {

    private WebDriver driver;
    @FindBy(id = "zaaCenaBruttoPole")
    private WebElement poleZaawansowanaCenaBrutto;

    @Autowired
    public PoleZaawansowanaCenaBrutto( WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszTekst(String tekst){
        poleZaawansowanaCenaBrutto.sendKeys(tekst);
    }

    public String pobierzTekst(){
        return poleZaawansowanaCenaBrutto.getAttribute("value");
    }

    public void zmienFocus(){
        poleZaawansowanaCenaBrutto.sendKeys(Keys.TAB);
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zaaCenaBruttoPole').val('')");
    }

    public WebElement pobierzPoWebElement() {
        return poleZaawansowanaCenaBrutto;
    }
}

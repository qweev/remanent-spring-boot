package aniela.remanent.SzukajTowarProste_UI_TabComponents;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PoleSzukajPozycji {

    private WebDriver driver;
    @FindBy(id = "szukajPozycji")
    private WebElement szukajPozycji;

    @Autowired
    public PoleSzukajPozycji(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszTekst(String tekst){
        szukajPozycji.sendKeys(tekst);
    }

    public String pobierzTekst(){
        return szukajPozycji.getAttribute("value");
    }

    public void zmienFocus(){
        szukajPozycji.sendKeys(Keys.TAB);
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#szukajPozycji').val('')");
    }

    public WebElement pobierzPoWebElement() {
        return szukajPozycji;
    }
}

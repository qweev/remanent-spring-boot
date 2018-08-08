package aniela.remanent.Zmien_UI_TabComponents;


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
public class PoleCenaNettoZmien {

    private WebDriver driver;
    @FindBy(id="zmienCenaN")
    private WebElement cenaNetto;

    @Autowired
    public PoleCenaNettoZmien(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszCeneNetto(String cena){
        cenaNetto.click();
        cenaNetto.sendKeys(cena);
    }

    public String pobierzCeneNetto(){
        return cenaNetto.getAttribute("value");
    }

    public String pobierzStyl(){
        return cenaNetto.getAttribute("class");
    }

    public void zmienFocus(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#zmienCenaN').focusout()");
//        cenaNetto.sendKeys(Keys.TAB);
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zmienCenaN').val('')");
    }

    public void wpiszCeneBrutto(String cena) {
        cenaNetto.sendKeys(cena);
    }

    public String pobierzCeneBrutto() {
        return cenaNetto.getAttribute("value");
    }

    public WebElement pobierzPoWebElement() {
        return cenaNetto;
    }
}

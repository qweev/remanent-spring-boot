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
public class PoleCenaBruttoZmien {

    private WebDriver driver;
    @FindBy(id="zmienCenaB")
    private WebElement cenaBrutto;

    @Autowired
    public PoleCenaBruttoZmien(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszCeneBrutto(String cena){
//		cenaBrutto.click();
        cenaBrutto.sendKeys(cena);
    }

    public String pobierzCeneBrutto(){
        return cenaBrutto.getAttribute("value");
    }

    public String pobierzStyl(){
        return cenaBrutto.getAttribute("class");
    }

    public void zmienFocus(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#zmienCenaB').focusout()");
//        cenaBrutto.sendKeys(Keys.TAB);
    }

    public WebElement pobierzPoWebElement() {
        return cenaBrutto;
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zmienCenaB').val('')");
    }
}

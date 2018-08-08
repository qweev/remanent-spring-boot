package aniela.remanent.DodajTowar_UI_TabComponents;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PoleCenaNetto {
    private WebDriver driver;
    @FindBy(id="cenaN")
    private WebElement cenaNetto;

    @Autowired
    public PoleCenaNetto(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszCeneNetto(String cena){
//        cenaNetto.click();
        cenaNetto.sendKeys(cena);
        zmienFocus();
    }

    public String pobierzCeneNetto(){
        return cenaNetto.getAttribute("value");
    }

    public String pobierzStyl(){
        return cenaNetto.getAttribute("class");
    }

    public void zmienFocus(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#cenaN').focusout()");
    }
}

package aniela.remanent.Raport_Logowanie_UI_Components;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PoleHasloLogowanie {

    private WebDriver driver;
    @FindBy(id="pass")
    private WebElement pass;

    @Autowired
    public PoleHasloLogowanie(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszPass(String cena){
        pass.sendKeys(cena);
    }

    public String pobierzPass(){
        return pass.getAttribute("value");
    }


    public WebElement pobierzPoWebElement() {
        return pass;
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#pass').val('')");
    }
}

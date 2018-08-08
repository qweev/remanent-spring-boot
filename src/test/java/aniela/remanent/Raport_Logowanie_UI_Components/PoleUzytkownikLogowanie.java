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
public class PoleUzytkownikLogowanie {

    private WebDriver driver;
    @FindBy(id="user")
    private WebElement user;

    @Autowired
    public PoleUzytkownikLogowanie(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszUsera(String login){
        user.sendKeys(login);
    }


    public WebElement pobierzPoWebElement() {
        return user;
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#user').val('')");
    }

}

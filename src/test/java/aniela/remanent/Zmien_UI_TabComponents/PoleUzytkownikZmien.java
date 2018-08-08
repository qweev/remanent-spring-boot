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
public class PoleUzytkownikZmien {



    private WebDriver driver;
    @FindBy(id="zmienUzytkownik")
    private WebElement uzytkownik;

    @Autowired
    public PoleUzytkownikZmien(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszUzytkownika(String uzytkownik){
        this.uzytkownik.click();
        this.uzytkownik.sendKeys(uzytkownik);
    }

    public String pobierzUzytkownika(){
        return uzytkownik.getAttribute("value");
    }

    public void zmienFocus(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zmienUzytkownik').focusout()");
//        uzytkownik.sendKeys(Keys.TAB);
    }

    public void wyczysc(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zmienUzytkownik').val('')");
    }

    public WebElement pobierzPoWebElement() {
        return uzytkownik;
    }
}

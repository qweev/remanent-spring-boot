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
public class PoleNazwaTowaru {
    private WebDriver driver;
    @FindBy(id="towar")
    private WebElement nazwaTowaru;

    @Autowired
    public PoleNazwaTowaru(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszNazweTowaru(String nazwa){
//        nazwaTowaru.click();
        nazwaTowaru.sendKeys(nazwa);
        zmienFocus();

    }

    public String pobierzNazweTowaru(){
        return nazwaTowaru.getAttribute("value");
    }

    public String pobierzKlasyCSS(){
        return nazwaTowaru.getCssValue("class");
    }


    public String pobierzStyl(){
        return nazwaTowaru.getAttribute("class");
    }

    public void zmienFocus(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#towar').focusout()");
    }
}

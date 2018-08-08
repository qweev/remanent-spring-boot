package aniela.remanent.UsunTowar_UI_TabComponents;

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
public class PolePobierzPozycjeDoUsun {

    private WebDriver driver;
    @FindBy(id="wpiszNumerPozycjiDoUsuniecia")
    private WebElement wpiszNumerPozycjiDoUsuniecia;

    @Autowired
    public PolePobierzPozycjeDoUsun(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszNumerPozycjiDoUsuniecia(String numerPozycji) {
        wpiszNumerPozycjiDoUsuniecia.sendKeys(numerPozycji);
    }

    public String pobierzNumerPozycjiDoUsuniecia (){
        return wpiszNumerPozycjiDoUsuniecia.getAttribute("value");
    }

    public void zmienFocus(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#wpiszNumerPozycjiDoUsuniecia').focusout()");
//        wpiszNumerPozycjiDoUsuniecia.sendKeys(Keys.TAB);
    }

    public WebElement pobierzPoWebElement() {
        return wpiszNumerPozycjiDoUsuniecia;
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#wpiszNumerPozycjiDoUsuniecia').val('')");
    }

    public String pobierzStyl(){
        return wpiszNumerPozycjiDoUsuniecia.getAttribute("class");
    }
}

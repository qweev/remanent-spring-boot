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
public class PoleIloscZmien {


    private WebDriver driver;
    @FindBy(id="zmienIlosc")
    private WebElement ilosc;

    @Autowired
    public PoleIloscZmien(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszIlosc(String ilosc){
//		this.ilosc.click();
        this.ilosc.sendKeys(ilosc);
    }

    public String pobierzIlosc(){
        return ilosc.getAttribute("value");
    }


    public String pobierzStyl(){
        return ilosc.getAttribute("class");
    }

    public void zmienFocus(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#zmienIlosc').focusout()");
//        ilosc.sendKeys(Keys.TAB);
    }

    public void wyczysc() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#zmienIlosc').val('')");
    }

    public WebElement pobierzPoWebElement() {
        return ilosc;
    }
}

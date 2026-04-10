package aniela.remanent.SzukajTowarProste_UI_TabComponents;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

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
        WebDriverWait czekaj = new WebDriverWait(driver, Duration.ofSeconds(20));
        czekaj.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI")));

        WebElement pole = czekaj.until(ExpectedConditions.visibilityOfElementLocated(By.id("szukajPozycji")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", pole);
        js.executeScript("arguments[0].focus(); arguments[0].value=''; arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', {bubbles:true})); arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", pole, tekst);
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

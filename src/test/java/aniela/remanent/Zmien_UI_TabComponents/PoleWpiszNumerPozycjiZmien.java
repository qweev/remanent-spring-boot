package aniela.remanent.Zmien_UI_TabComponents;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class PoleWpiszNumerPozycjiZmien {


    private WebDriver driver;
    @FindBy(id="wpiszNumerPozycji")
    private WebElement poleWpiszNumerPozycji;
    @FindBy(id="pobierzPozycjePoNumerzePrzycisk")
    private WebElement przyciskPobierz;

    private String stylCzerwony = "czerwony";

    @Autowired
    public PoleWpiszNumerPozycjiZmien(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszNumerPozycji(String numer){
        new WebDriverWait(driver, Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                        .until(ExpectedConditions.visibilityOf(poleWpiszNumerPozycji));
        poleWpiszNumerPozycji.click();
        poleWpiszNumerPozycji.sendKeys(numer);
    }

    public String pobierzNumerWpisanejPozycji(){
        poleWpiszNumerPozycji.click();
        return poleWpiszNumerPozycji.getAttribute("value");
    }

    public void zmienFocus(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#wpiszNumerPozycji').focusout()");
    }

    public String pobierzStyl(){
        return poleWpiszNumerPozycji.getAttribute("class");
    }

    public void kliknijPrzycisk() {
        przyciskPobierz.click();
    }

    public boolean jestWidoczne(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(poleWpiszNumerPozycji));
        return poleWpiszNumerPozycji.isDisplayed();
    }

}

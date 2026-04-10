package aniela.remanent.SzukajTowarProste_UI_TabComponents;

import org.openqa.selenium.By;
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
public class SzukajTab {

    private WebDriver driver;
    @FindBy(xpath="//a[@href='#szukaj'] ")
    private WebElement szukajTab;

    @Autowired
    public SzukajTab(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijTab(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(szukajTab));
        this.szukajTab.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("szukajPozycji")));

    }
}

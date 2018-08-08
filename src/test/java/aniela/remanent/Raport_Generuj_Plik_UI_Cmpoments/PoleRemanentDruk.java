package aniela.remanent.Raport_Generuj_Plik_UI_Cmpoments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoleRemanentDruk {
    private WebDriver driver;
    @FindBy(id="sciezka")
    WebElement poleRemanentDruk;

    @Autowired
    public PoleRemanentDruk(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszSciezke(String sciezka){
        poleRemanentDruk.sendKeys(sciezka);
    }
}

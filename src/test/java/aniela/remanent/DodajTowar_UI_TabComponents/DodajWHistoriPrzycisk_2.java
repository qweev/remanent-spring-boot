package aniela.remanent.DodajTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DodajWHistoriPrzycisk_2 {

    private WebDriver driver;

    @FindBy(xpath="(//button[@id='zmienIloscBaza'])[2]")
    private WebElement dodajPrzycisk;

    @Autowired
    public DodajWHistoriPrzycisk_2(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijDodaj(){
        dodajPrzycisk.click();
    }

    public boolean jestWidoczny(){
        return dodajPrzycisk.isDisplayed();
    }
}

package aniela.remanent.DodajTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrzyciskZmien {

    private WebDriver driver;

    @FindBy(id="zmienDodajPrzycisk")
    private WebElement przyciskZmien;


    @Autowired
    public PrzyciskZmien(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijZmien(){
        przyciskZmien.click();
    }

    public boolean jestWidoczny(){
        return przyciskZmien.isDisplayed();
    }


}

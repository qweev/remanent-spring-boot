package aniela.remanent.SzukajTowarProste_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PrzyciskSzukajPozycji {

    private WebDriver driver;
    @FindBy(id="szukajPozycjiPrzycisk")
    private WebElement szukajPozycjiPrzycisk;

    @Autowired
    public PrzyciskSzukajPozycji(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijSzukaj(){
        szukajPozycjiPrzycisk.click();
    }

    public WebElement pobierzWebElement() {
        return szukajPozycjiPrzycisk;
    }
}

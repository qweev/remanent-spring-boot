package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrzyciskSzukajZaawansowane {
    private WebDriver driver;
    @FindBy(id="zaaSzukajPrzycisk")
    private WebElement szukajPozycjiPrzyciskZaaw;

    @Autowired
    public PrzyciskSzukajZaawansowane(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijSzukaj(){
        szukajPozycjiPrzyciskZaaw.click();
    }

    public WebElement pobierzWebElement() {
        return szukajPozycjiPrzyciskZaaw;
    }
}

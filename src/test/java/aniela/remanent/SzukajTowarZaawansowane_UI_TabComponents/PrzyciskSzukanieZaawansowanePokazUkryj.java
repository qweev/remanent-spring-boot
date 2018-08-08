package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrzyciskSzukanieZaawansowanePokazUkryj {

    private WebDriver driver;
    @FindBy(id="przyciskSzukanieZaawansowane")
    private WebElement pokazUkryjPrzycisk;

    @Autowired
    public PrzyciskSzukanieZaawansowanePokazUkryj(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijSzukaj(){
        pokazUkryjPrzycisk.click();
    }

    public WebElement pobierzWebElement() {
        return pokazUkryjPrzycisk;
    }
}

package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckboxUzytkownik {

    private WebDriver driver;
    @FindBy(id="zaaUzytkownik")
    private WebElement checkboxUzytkownik;

    @Autowired
    public CheckboxUzytkownik(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknij(){
        checkboxUzytkownik.click();
    }

    public boolean czyZaznaczony() {
        return checkboxUzytkownik.isSelected();
    }
}

package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckboxCenaNetto {
    private WebDriver driver;
    @FindBy(id="zaaCenaNetto")
    private WebElement checkboxCenaNetto;

    @Autowired
    public CheckboxCenaNetto(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknij(){
        checkboxCenaNetto.click();
    }

    public boolean czyZaznaczony() {
        return checkboxCenaNetto.isSelected();
    }
}

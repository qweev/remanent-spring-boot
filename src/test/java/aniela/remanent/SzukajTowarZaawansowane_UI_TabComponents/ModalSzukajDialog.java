package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModalSzukajDialog {

    private WebDriver driver;
    @FindBy(id = "modalSzukajDialog")
    private WebElement modalSzukajDialog;

    @Autowired
    public ModalSzukajDialog(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String pobierzText(){
        return this.modalSzukajDialog.getText();
    }

}

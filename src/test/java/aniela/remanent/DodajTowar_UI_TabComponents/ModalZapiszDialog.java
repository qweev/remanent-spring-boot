package aniela.remanent.DodajTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ModalZapiszDialog {

    private WebDriver driver;

    @FindBy(id="modalZapisDialog")
    private WebElement dialog;

    @Autowired
    public ModalZapiszDialog(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String pobierzText(){
        return dialog.getText();
    }

}

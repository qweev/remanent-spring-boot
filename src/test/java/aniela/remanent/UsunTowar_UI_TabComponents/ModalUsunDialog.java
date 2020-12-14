package aniela.remanent.UsunTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ModalUsunDialog {

    private WebDriver driver;
    @FindBy(id="modalUsunDialog")
    private WebElement dialog;

    @FindBy(id="zamknijDialogUsun")
    private WebElement przyciskZamknij;


    @Autowired
    public ModalUsunDialog(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String pobierzText(){
        return dialog.getText();
    }

    public WebElement pobierzWebElement(){
        return dialog;
    }

    public String pobierzStyl(){
        return dialog.getAttribute("class");
    }

    public void zamknij() {
        przyciskZamknij.click();
    }
}

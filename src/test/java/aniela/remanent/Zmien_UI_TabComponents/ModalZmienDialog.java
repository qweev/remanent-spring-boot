package aniela.remanent.Zmien_UI_TabComponents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ModalZmienDialog {

    private WebDriver driver;
    @FindBy(id="modalZmienDialog")
    private static WebElement dialog;

    @FindBy(id="zamknijDialogZmien")
    private static WebElement zamknijDialogZmien;


    @Autowired
    public ModalZmienDialog(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public static String pobierzText(){
        return dialog.getText();
    }

    public String pobierzStyl(){
        return dialog.getAttribute("class");
    }

    public WebElement pobierzWebElement(){
        return dialog;
    }

    public void zamknij() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(zamknijDialogZmien));
        zamknijDialogZmien.click();
    }
}

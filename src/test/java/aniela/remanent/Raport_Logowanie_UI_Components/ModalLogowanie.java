package aniela.remanent.Raport_Logowanie_UI_Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ModalLogowanie {

    private WebDriver driver;
    @FindBy(id ="modalLogowanie")
    WebElement modalLogowanie;

    @Autowired
    public ModalLogowanie(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String pobierzText(){
        return modalLogowanie.getText();
    }

    public WebElement pobierzPoWebelement() {
        return modalLogowanie;
    }
}

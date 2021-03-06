package aniela.remanent.Raport_Generuj_Plik_UI_Cmpoments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModalExcel {

    private WebDriver driver;
    @FindBy(id="modalExcel")
    WebElement modalExcel;

    @Autowired
    public ModalExcel(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String pobierzText(){
        return modalExcel.getText();
    }


    public WebElement pobierzWebElement() {
        return modalExcel;
    }
}

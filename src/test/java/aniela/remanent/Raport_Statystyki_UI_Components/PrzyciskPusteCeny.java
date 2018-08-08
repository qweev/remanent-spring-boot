package aniela.remanent.Raport_Statystyki_UI_Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrzyciskPusteCeny {
    private WebDriver driver;
    @FindBy(id="generujZeroweCeny")
    private WebElement generujZeroweCeny;

    @Autowired
    public PrzyciskPusteCeny(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknij(){
        generujZeroweCeny.click();
    }
}

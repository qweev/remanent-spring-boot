package aniela.remanent.Raport_Statystyki_UI_Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiltrTabelkaPusteCeny {

    private WebDriver driver;
    @FindBy(id="szukajTabelkaZeroweCeny")
    private WebElement szukajTabelkaZeroweCeny;

    @Autowired
    public FiltrTabelkaPusteCeny(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszDoFiltra(String nazwa){
        szukajTabelkaZeroweCeny.sendKeys(nazwa);
    }

    public String pobierzZFiltra(String nazwa){
        return szukajTabelkaZeroweCeny.getAttribute("value");
    }
}

package aniela.remanent.DodajTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PoleFiltrujTabelke {
    private WebDriver driver;
    @FindBy(id="szukajDodajTabelka")
    private WebElement szukajDodajTabelka;

    @Autowired
    public PoleFiltrujTabelke(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszDoFiltra(String cena){
        szukajDodajTabelka.click();
        szukajDodajTabelka.sendKeys(cena);
    }

    public String pobierzZFiltra(String cena){
        szukajDodajTabelka.click();
        return szukajDodajTabelka.getAttribute("value");
    }
}

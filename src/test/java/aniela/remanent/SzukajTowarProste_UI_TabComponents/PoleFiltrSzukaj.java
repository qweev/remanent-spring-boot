package aniela.remanent.SzukajTowarProste_UI_TabComponents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PoleFiltrSzukaj {

    private WebDriver driver;
    @FindBy(id="szukajSzukajTabelka")
    private WebElement szukajWynikiTabelka;

    @Autowired
    public PoleFiltrSzukaj( WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszDoFiltra(String cena){
        szukajWynikiTabelka.click();
        szukajWynikiTabelka.sendKeys(cena);
    }

    public String pobierzZFiltra(String cena){
        szukajWynikiTabelka.click();
        return szukajWynikiTabelka.getAttribute("value");
    }
}

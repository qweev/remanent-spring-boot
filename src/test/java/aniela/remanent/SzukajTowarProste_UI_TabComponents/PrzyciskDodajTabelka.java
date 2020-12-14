package aniela.remanent.SzukajTowarProste_UI_TabComponents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrzyciskDodajTabelka {

    private WebDriver driver;
    @FindBy(id="zmienIloscBazaPoSzukaj")
    private WebElement zmienIloscBazaPoSzukaj;

    @Autowired
    public PrzyciskDodajTabelka(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijDodaj(){
        zmienIloscBazaPoSzukaj.click();
    }

}

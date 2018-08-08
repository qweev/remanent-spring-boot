package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadioCenaNetto {
    private WebDriver driver;
    @FindBy(name="szukajCenaNettoRadio")
    private List<WebElement> radioCenaNetto;

    @Autowired
    public RadioCenaNetto(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijRowne(){
        radioCenaNetto.get(0).click();
    }

    public void kliknijWieksze(){
        radioCenaNetto.get(1).click();
    }

    public void kliknijMniejsze(){
        radioCenaNetto.get(2).click();
    }


    public String pobierzAktywnaOpcje(){
        return radioCenaNetto.stream()
                .filter(x -> x.getAttribute("checked").equalsIgnoreCase("checked"))
                .findAny().get().getAttribute("value");


//        for(WebElement radio : radioCenaNetto){
//            if (radio.getAttribute("checked").equalsIgnoreCase("checked")){
//                return radio.getAttribute("value");
//            }
//        }
//        return "";
    }

}

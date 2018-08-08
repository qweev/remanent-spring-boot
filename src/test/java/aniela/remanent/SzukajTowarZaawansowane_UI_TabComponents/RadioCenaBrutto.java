package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadioCenaBrutto {
    private WebDriver driver;
    @FindBy(name="szukajCenaBruttoRadio")
    private List<WebElement> radioCenaBrutto;

    @Autowired
    public RadioCenaBrutto(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijRowne(){
        radioCenaBrutto.get(0).click();
    }

    public void kliknijWieksze(){
        radioCenaBrutto.get(1).click();
    }

    public void kliknijMniejsze(){
        radioCenaBrutto.get(2).click();
    }


    public String pobierzAktywnaOpcje(){
        return radioCenaBrutto.stream()
                .filter(x -> x.getAttribute("checked").equalsIgnoreCase("checked"))
                .findAny().get().getAttribute("value");

//        for(WebElement radio : radioCenaBrutto){
//            if (radio.getAttribute("checked").equalsIgnoreCase("checked")){
//                return radio.getAttribute("value");
//            }
//        }
//        return "";
    }
}

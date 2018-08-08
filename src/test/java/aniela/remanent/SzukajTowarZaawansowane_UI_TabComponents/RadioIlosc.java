package aniela.remanent.SzukajTowarZaawansowane_UI_TabComponents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadioIlosc {
    private WebDriver driver;
    @FindBy(name="szukajIloscRadio")
    private List<WebElement> radioIlosc;

    @Autowired
    public RadioIlosc(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijRowne(){
        radioIlosc.get(0).click();
    }

    public void kliknijWieksze(){
        radioIlosc.get(1).click();
    }

    public void kliknijMniejsze(){
        radioIlosc.get(2).click();
    }


    public String pobierzAktywnaOpcje(){
        return radioIlosc.stream()
                .filter(x -> x.getAttribute("checked").equalsIgnoreCase("checked"))
                .findAny().get().getAttribute("value");

//        for(WebElement radio : radioIlosc){
//            if (radio.getAttribute("checked").equalsIgnoreCase("checked")){
//                return radio.getAttribute("value");
//            }
//        }
//        return "";
    }

}

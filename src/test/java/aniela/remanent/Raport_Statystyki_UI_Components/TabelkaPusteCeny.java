package aniela.remanent.Raport_Statystyki_UI_Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelkaPusteCeny {

    private WebDriver driver;
    @FindBy(xpath="//tbody[@id='tabelkaZeroweCeny']/tr")
    private List<WebElement> wiersze;

    @Autowired
    public TabelkaPusteCeny(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public long liczbaWidocznychPozycji(){
        return wiersze.stream()
                .filter(x -> x.getAttribute("style").equalsIgnoreCase("") || x.getAttribute("style").isEmpty() )
                .count();
    }
}

package aniela.remanent.DodajTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoleDodajWHistori {

    private WebDriver driver;

    @FindBy(xpath="(//input[@id='zmienilosc'])[2]")
    private WebElement dodajPole;

    @Autowired
    public PoleDodajWHistori(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszIlosc(String ilosc){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dodajPole.sendKeys(ilosc);
    }


}

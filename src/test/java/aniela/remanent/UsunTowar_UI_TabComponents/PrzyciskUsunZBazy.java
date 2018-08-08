package aniela.remanent.UsunTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PrzyciskUsunZBazy {

    private WebDriver driver;
    @FindBy(id="usunZBazy")
    private WebElement przyciskUsunZBazy;

    @Autowired
    public PrzyciskUsunZBazy(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijPrzycisk(){
        przyciskUsunZBazy.click();
    }

    public WebElement pobierzWebElement() {
        return przyciskUsunZBazy;
    }
}

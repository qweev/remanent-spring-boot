package aniela.remanent.Raport_Logowanie_UI_Components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
public class PrzyciskZaloguj {

    private WebDriver driver;
    @FindBy(id="zaloguj")
    private WebElement zaloguj;
    private By zalogujBy;

    @Autowired
    public PrzyciskZaloguj(WebDriver driver){
        this.driver = driver;
        zalogujBy = By.id("zaloguj");
        PageFactory.initElements(this.driver, this);
    }

    public void kliknij(){
        zaloguj.click();
    }


    public WebElement pobierzPoWebElement() {
        return zaloguj;
    }

    public By pobierzPoBy() {
        return zalogujBy;
    }
}

package aniela.remanent.DodajTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DodajTab {

    private WebDriver driver;
    @FindBy(xpath="//a[@href='#dodaj'] ")
    private WebElement dodajTab;

    @Autowired
    public DodajTab(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijTab(){
        this.dodajTab.click();
    }
}

package aniela.remanent.UsunTowar_UI_TabComponents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TabUsun {

    private WebDriver driver;
    @FindBy(xpath="//a[@href='#usun'] ")
    private WebElement tabZmien;

    @Autowired
    public TabUsun(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void kliknijTab(){
        this.tabZmien.click();
    }
}

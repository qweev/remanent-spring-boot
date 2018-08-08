package aniela.remanent.UsunTowar_UI_TabComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TabelkaUsun {

    private WebDriver driver;
    @FindBy(id="tabelkaUsun")
    private WebElement tabelkaUsun;

    @Autowired
    public TabelkaUsun(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebElement pobierzWebElement() {
        return tabelkaUsun;
    }
}

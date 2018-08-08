package aniela.remanent.Zmien_UI_TabComponents;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PoleJednostkaZmien {

    private WebDriver driver;
    @FindBy(id="zmienJednostka")
    private WebElement jednostkaPrzycisk;

    @Autowired
    public PoleJednostkaZmien(WebDriver driver){
        this.driver = driver;
//		jednostkaPrzycisk = this.driver.findElement(By.id("jednostka"));
        PageFactory.initElements(this.driver, this);
    }

    public void wybierz(String jednostkaZListy){
        String jednostkaListaID = jednostkaZListy.replace(".", "").trim() + "Zmien";
        jednostkaPrzycisk.click();

        WebElement wybranaJednostka = driver.findElement(By.id(jednostkaListaID));

        wybranaJednostka.click();

    }

    public String zwrocWybranaJednostke(){
        String wybranaJednostka = jednostkaPrzycisk.getText();
        return wybranaJednostka;
    }

    public WebElement pobierzPoWebElement() {
        return jednostkaPrzycisk;
    }
}

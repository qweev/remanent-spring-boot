package aniela.remanent.SzukajTowarProste_UI_TabComponents;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoleDodajTabelka {

    private WebDriver driver;
    @FindBy(id="zmieniloscPoSzukaj")
    private WebElement zmieniloscPoSzukaj;

    @Autowired
    public PoleDodajTabelka(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void wpiszSztuki(String ilosc){
        zmieniloscPoSzukaj.click();
        zmieniloscPoSzukaj.sendKeys(ilosc);
    }

    public String pobierzIloscDlaWiersza(int numerWiersza){
        List<WebElement> polaWiersza = zbierzKomorkiPozycjiZWierszaTabelki(numerWiersza);
        return polaWiersza.get(4).getText();
    }

    private List<WebElement> zbierzKomorkiPozycjiZWierszaTabelki(int numerWiersza){
        List<WebElement> komorki = new ArrayList<WebElement>();
        int pierwszaKomorkaPozycjiWTabelce = 2;
        int ostatniaKomorkaPozycjiWTabelce = 8;

        for(int komorka=pierwszaKomorkaPozycjiWTabelce; komorka < ostatniaKomorkaPozycjiWTabelce; komorka++){
            komorki.add(driver.findElement(By.xpath("//tbody[@id='tabelkaSzukaj']/tr["+numerWiersza+"]/td["+komorka+"]")));
        }

        return komorki;
    }



}

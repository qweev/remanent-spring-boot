package aniela;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;


@Configuration
public class FireFoxConfiguration {

    private String geckoDriverPath = "src\\test\\resources\\geckodriver.exe";

    private  FirefoxDriver driverPozycje;

    @Scope("singleton")
    @Bean
    public WebDriver setupFirefoxDriverPozycje() {
        System.setProperty("webdriver.gecko.driver", geckoDriverPath);
        driverPozycje = new FirefoxDriver();
        return driverPozycje;
    }

    @PreDestroy
    public void destroy() {
        driverPozycje.quit();
    }

}

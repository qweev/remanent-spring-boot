package aniela;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;
import java.util.logging.Logger;

@Configuration
public class FireFoxConfiguration {

    private String geckoDriverPathWindows = "src\\test\\resources\\geckodriver_new.exe";
    private String geckoDriverPathLinux = "src/test/resources/geckodriver";
    private FirefoxDriver driverPozycje;

    private Logger LOG = Logger.getLogger(this.getClass().getName());

    @Scope("singleton")
    @Bean
    public WebDriver setupFirefoxDriverPozycje() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Win")) {
            System.setProperty("webdriver.gecko.driver", geckoDriverPathWindows);
            LOG.info(String.format("Gecko driver for Windows in use %s", geckoDriverPathWindows));
        } else {
            System.setProperty("webdriver.gecko.driver", geckoDriverPathLinux);
            LOG.info(String.format("Gecko driver for Linux in use %s", geckoDriverPathLinux));
        }

        driverPozycje = new FirefoxDriver();
        return driverPozycje;
    }

    @PreDestroy
    public void destroy() {
        if (driverPozycje != null) {
            try {
                driverPozycje.quit();
            } catch (NoSuchSessionException ignored) {
                LOG.fine("Firefox session already closed before @PreDestroy.");
            }
        }
    }
}

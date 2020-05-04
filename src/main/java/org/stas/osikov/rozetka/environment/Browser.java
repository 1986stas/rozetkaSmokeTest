package org.stas.osikov.rozetka.environment;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

import javax.naming.NamingException;
import java.util.logging.Logger;

@SuppressWarnings("all")
public final class Browser {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(Browser.class));

    private static BrowserFactory instance;
    private final static SystemProperties systemProperties = ConfigFactory.create(SystemProperties.class);

    private static Browsers currentBrowser = Browsers.valueOf((System.getenv("browser") == null
            ? systemProperties.browser()
            : System.getenv("browser")).toUpperCase());
    static final boolean IS_HEADLESS = systemProperties.isHeadless();
    public static final String BROWSER_URL = systemProperties.url();
    private static final String BROWSER_SIZE = systemProperties.browserSize();

    private Browser(){
        LOGGER.info(String.format("browser %s is ready", currentBrowser.name()));
    }

    @SuppressWarnings("all")
    public static void getInstance() {
        if (instance == null) {
            synchronized (BrowserFactory.class) {
                initNewDriver();
                instance = new BrowserFactory();
            }
        }
    }

    static boolean isGrid() {
        return false;
    }

    public static WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }

    private static void initNewDriver() {
        Configuration.timeout = 10000;
        WebDriverManager.chromedriver().version("80");
        Configuration.pollingInterval = 300;
        Configuration.browserSize = BROWSER_SIZE;
        try {
            BrowserFactory.setUp(currentBrowser);
        } catch (NamingException e) {
            LOGGER.info("Browser: getting New Driver " + e.getExplanation());;
        }
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public enum Browsers {
        FIREFOX("firefox"),
        CHROME("chrome");

        @Getter
        private final String value;
    }

}

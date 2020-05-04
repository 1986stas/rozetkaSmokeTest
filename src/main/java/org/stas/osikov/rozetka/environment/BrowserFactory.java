package org.stas.osikov.rozetka.environment;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

import javax.naming.NamingException;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrowserFactory {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BrowserFactory.class));

    public static void setUp(@NonNull final Browser.Browsers type) throws NamingException {
        for (Browser.Browsers t : Browser.Browsers.values()) {
            if (t == type) {
                setWebDriver(t);
                return;
            }
        }
        throw new NamingException("browser name wrong" + ":\nchrome\nfirefox");
    }

    /**
     * Set web driver.
     *
     * @param type the type
     *
     * @throws NamingException the naming exception
     */
    @SneakyThrows(NamingException.class)
    private static void setWebDriver(@NonNull final Browser.Browsers type) {
        Configuration.headless = Browser.IS_HEADLESS;
        switch (type) {
            case CHROME:
                Configuration.browser = WebDriverRunner.CHROME;
                break;
            case FIREFOX:
                Configuration.browser = WebDriverRunner.FIREFOX;
                break;
            default:
                LOGGER.info(String.format("WebDriver %s not created", type.name()));
                throw new NamingException("browser name wrong" + ":\nchrome\nfirefox");
        }
        if(Browser.isGrid()) {
            /*
            here might be an implementation of remote driver, for example selenoid
             */
        }
    }
}

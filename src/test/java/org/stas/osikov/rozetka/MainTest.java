package org.stas.osikov.rozetka;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.stas.osikov.rozetka.environment.Browser;
import org.stas.osikov.rozetka.listener.AllureAttachmentListener;
import org.stas.osikov.rozetka.page.AbstractPage;
import org.stas.osikov.rozetka.page.ProductsPage;
import org.stas.osikov.rozetka.page.SearchPage;

import static com.codeborne.selenide.Selenide.page;

@ExtendWith({AllureAttachmentListener.class})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AbstractPage.class)
public class MainTest {

    @Autowired
    ProductsPage productsPage;
    @Autowired
    SearchPage searchPage;

    @BeforeAll
    static void openPage() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().
                screenshots(true).savePageSource(false));
        Browser.getInstance();
    }

    protected <T> T at(Class<T> pageClass){
        return page(pageClass);
    }

}
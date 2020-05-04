package org.stas.osikov.rozetka.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.stas.osikov.rozetka.environment.Browser;

import static com.codeborne.selenide.Selenide.open;

@SuppressWarnings("rawtypes")
public abstract class MainPage<Page extends MainPage> {
    private final static int TIME_TO_WAIT = 10000;
    private static String BASE_URL = Browser.BROWSER_URL;


    <T extends ElementsCollection> void selectProduct(String productName, T t) {
        t.stream()
                .filter(selenideElement -> readText(selenideElement).contains(productName))
                .forEach(selenideElement -> selenideElement.waitUntil(Condition.visible, TIME_TO_WAIT)
                        .hover().click());
    }

    protected void openPage(Class<Page> pageClass){
        open(BASE_URL, pageClass);
    }

    /**
     * methods below can be improved by switching between By or WebElement, or any other Remote WebElement
     * @param elementAttr
     * @param <V>
     */

    protected <V> void click(V elementAttr) {
        ((SelenideElement) elementAttr).waitUntil(Condition.appear, TIME_TO_WAIT).click();
    }

    protected <V> void writeText(V elementAttr, String text) {
        ((SelenideElement) elementAttr).waitUntil(Condition.appear, TIME_TO_WAIT).setValue(text);
    }

    protected <V> boolean isElementVisible(V elementAttr) {
        return  ((SelenideElement) elementAttr).is(Condition.visible);
    }

    protected <V> String readText(V elementAttr) {
        return ((SelenideElement) elementAttr).text();
    }

    protected void waitForPageLoad() {
        new WebDriverWait(Browser.getDriver(), TIME_TO_WAIT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

}
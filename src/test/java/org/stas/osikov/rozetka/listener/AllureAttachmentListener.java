package org.stas.osikov.rozetka.listener;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.logging.Logger;


public class AllureAttachmentListener implements AfterTestExecutionCallback, BeforeTestExecutionCallback {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AllureAttachmentListener.class));

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()){
            LOGGER.info("Test Failed " + context.getDisplayName());
            saveAllureScreenshot();
        }
        saveAllureScreenshot();
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        LOGGER.info("Test Started " + context.getDisplayName());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveAllureScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}

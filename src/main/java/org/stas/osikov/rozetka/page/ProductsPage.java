package org.stas.osikov.rozetka.page;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import org.stas.osikov.rozetka.exception.ProductNotFoundException;
import org.stas.osikov.rozetka.pojo.Product;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$$;

@SuppressWarnings("rawtypes")
@Component
public class ProductsPage extends MainPage {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(ProductsPage.class));

    private ElementsCollection productsList =
            $$(By.xpath("//*[@class='catalog-grid']/descendant-or-self::*[@class='goods-tile']/descendant-or-self::*[contains(@class, 'goods-tile__inner')]//span[contains (@class, 'title')]"));

    @SneakyThrows
    @Step
    public ProductsPage waitForProducts(){
        Selenide.sleep(3000);
        try {
            productsList.shouldBe(CollectionCondition.sizeGreaterThan(0));
        }
        catch (Exception productNotFoundException){
            throw new ProductNotFoundException("Failed to load products", productNotFoundException);
        }
        return this;
    }

    @Step
    public int getProductsListSize(){
        return productsList.size();
    }

    @Step
    public boolean isProductDisplayed(Product product){
        return productsList.stream().
                anyMatch(selenideElement -> readText(selenideElement).
                        contains(product.getName()));
    }
}

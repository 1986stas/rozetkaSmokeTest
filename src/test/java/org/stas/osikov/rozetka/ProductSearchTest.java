package org.stas.osikov.rozetka;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.stas.osikov.rozetka.page.ProductsPage;
import org.stas.osikov.rozetka.pojo.Product;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Smoke test")
@Feature("In this feature we check rozetka product search functionality")
public class ProductSearchTest extends MainTest {

    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest
    @Description("In this test we're checking that a user can search for a product and see it on a result page")
    @MethodSource("getProductName")
    void productShouldBeDisplayed(Product product){
        searchPage.openSearchPage();
        assertAll("Content is displayed", () ->
                assertTrue(searchPage.isSearchButtonDisplayed(), "Search button is not displayed"),
                () -> assertTrue(searchPage.isSearchFieldDisplayed(), "Search field is not displayed")
        );
        searchPage.writeTextToSearchField(product).
                    clickOnSearchButton();
        at(ProductsPage.class).waitForProducts();
        assertThat("Product list is empty", productsPage.getProductsListSize(), is(not(0)));
        assertThat("List does not contain product", productsPage.isProductDisplayed(product));
    }

    private static Stream<Arguments> getProductName() {
        return Stream.of(
                Arguments.of(new Product("Samsung Galaxy S10")),
                Arguments.of(new Product("Google Pixel 4 XL")),
                Arguments.of(new Product("Huawei P30"))
        );
    }
}

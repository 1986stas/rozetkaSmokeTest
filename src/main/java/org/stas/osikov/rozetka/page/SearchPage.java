package org.stas.osikov.rozetka.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import org.stas.osikov.rozetka.pojo.Product;

import static com.codeborne.selenide.Selenide.$;

@SuppressWarnings("rawtypes")
@Component
public class SearchPage extends MainPage {

    private SelenideElement searchField = $(By.xpath("//div[@role = 'search']/descendant-or-self::input[@name = 'search']"));
    private SelenideElement searchButton = $(By.xpath("//div[@role = 'search']/descendant-or-self::button[contains (@class, 'submit')]"));

    @Step
    public SearchPage openSearchPage(){
        openPage(SearchPage.class);
        return this;
    }

    @Step
    public boolean isSearchFieldDisplayed(){
        return isElementVisible(searchField);
    }

    @Step
    public boolean isSearchButtonDisplayed(){
        return isElementVisible(searchButton);
    }

    @Step
    public SearchPage writeTextToSearchField(Product product){
        writeText(searchField, product.getName());
        return this;
    }

    @Step
    public SearchPage clickOnSearchButton(){
        click(searchButton);
        return this;
    }



}

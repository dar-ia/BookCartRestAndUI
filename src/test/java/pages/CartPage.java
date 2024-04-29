package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.Attachments;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CartPage {
    SelenideElement userName = $("#userName-value"),
            noDataInCart = $(".rt-noData"),
            deleteAllBooksButton = $$("#submit").findBy(Condition.text("Delete All Books")),
            confirmPopUpButton = $("#closeSmallModal-ok");

    @Step("Open browser on profile")
    public CartPage openBrowserOnTheCart() {
        open("/profile");
        return this;
    }

    @Step("Open browser to add cookies")
    public CartPage openBrowserOnDummyPage() {
        open("/images/Toolsqa.jpg");
        return this;
    }

    @Step("Assert that login is successful")
    public CartPage assertSuccessLogin(String userNameActual) {
        userName.shouldHave(Condition.text(userNameActual));
        Attachments.screenshotAs("Profile is opened");
        return this;
    }

    @Step("Refresh page")
    public CartPage refreshPage() {
        refresh();
        return this;
    }

    @Step("Assert that cart is empty in UI")
    public CartPage assertThatCartIsEmpty() {
        noDataInCart.shouldHave(Condition.text("No rows found"));
        noDataInCart.shouldBe(Condition.visible);
        Attachments.screenshotAs("Cart is empty");
        return this;
    }

    @Step("Assert that cart is not empty in UI")
    public CartPage assertThatCartIsNotEmpty() {
        noDataInCart.shouldNotBe(Condition.visible);
        Attachments.screenshotAs("Cart is not empty");
        return this;
    }

    @Step("Delete all books")
    public CartPage deleteAllBooks() {
        deleteAllBooksButton
                .scrollTo()
                .click();
        return this;
    }

    @Step("Confirm delete operation")
    public CartPage confirmDelete() {
        confirmPopUpButton.click();
        Selenide.switchTo().alert().accept();
        return this;
    }
}

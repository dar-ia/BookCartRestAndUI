package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.Attachments;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CartPage {
    SelenideElement userName = $("#userName-value"),
            noDataInCart = $(".rt-noData");
//    @Step("Open browser")
//    public CartPage openBrowserOnTheCart(String token, String expires, String userName, String userId) {
//        open("/images/Toolsqa.jpg");
//        getWebDriver().manage().addCookie(new Cookie("token", token));
//        getWebDriver().manage().addCookie(new Cookie("expires", expires));
//        getWebDriver().manage().addCookie(new Cookie("userName", userName));
//        getWebDriver().manage().addCookie(new Cookie("userID", userId));
//        open("/profile");
//        return this;
//    }
    @Step("Open browser")
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
    public CartPage assertSuccessLogin(String userNameActual){
        userName.shouldHave(Condition.text(userNameActual));
        Attachments.screenshotAs("Profile is opened");
        return this;
    }
    @Step("Assert that cart is empty")
    public CartPage assertThatCartIsEmpty(){
        noDataInCart.shouldHave(Condition.text("No rows found"));
        noDataInCart.shouldBe(Condition.visible);
        Attachments.screenshotAs("Cart is empty");
        return this;
    }
    @Step("Assert that cart is not empty")
    public CartPage assertThatCartIsNotEmpty(){

        noDataInCart.shouldNotBe(Condition.visible);
        Attachments.screenshotAs("Cart is not empty");
        return this;
    }
}

package tests;

import api.AccountApis;
import api.BookStoreApis;
import helpers.WithLogin;
import models.accountmodels.UserModel;
import models.bookstoremodels.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;

import static api.AuthApi.authorizeRequest;
import static io.qameta.allure.Allure.step;
@DisplayName("Tests on books removing from the cart")
public class DeleteBooksWithAnnotationTests extends TestBase {
    @Test
    @WithLogin
    @DisplayName("Positive test on books removing from the cart")
    void deleteAllBooksFromTheCartTest() {
        CartPage cartPage = new CartPage();
        BookStoreApis bookStoreApis = new BookStoreApis();
        AccountApis accountApis = new AccountApis();
        step("Clear the cart", () -> {bookStoreApis.deleteAllBooks(authorizeRequest().getUserId());
        });
        AddBooksResponse addBooksResponse =
                step("Add books to the cart", () -> bookStoreApis.addBooks());
        UserModel userWithBooks = step("Fetch user info", () -> accountApis.getUserInfo());
        step("Ensure that cart is not empty", () -> {
            Assertions.assertEquals(2, userWithBooks.getBooks().length);
        });
        cartPage.openBrowserOnTheCart()
                .assertThatCartIsNotEmpty();
        step("Delete books from the cart", () -> bookStoreApis.deleteAllBooks(authorizeRequest().getUserId()));
        UserModel userIsCleared = step("Fetch user info", () -> accountApis.getUserInfo());
        step("Ensure that cart is not empty", () -> {
            Assertions.assertEquals(0, userIsCleared.getBooks().length);
        });
        cartPage.openBrowserOnTheCart()
                .assertThatCartIsEmpty();
    }
}

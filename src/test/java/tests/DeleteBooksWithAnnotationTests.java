package tests;

import api.AccountApis;
import api.BookStoreApis;
import helpers.WithLogin;
import models.accountmodels.UserModel;
import models.bookstoremodels.*;
import org.junit.jupiter.api.*;
import pages.CartPage;

import static api.AuthApi.authorizeRequest;
import static io.qameta.allure.Allure.step;

@DisplayName("Tests on books removing from the cart")
public class DeleteBooksWithAnnotationTests extends TestBase {
    CartPage cartPage = new CartPage();
    BookStoreApis bookStoreApis = new BookStoreApis();
    AccountApis accountApis = new AccountApis();

    @Test
    @WithLogin
    @DisplayName("Positive test on books removing from the cart")
    void deleteAllBooksFromTheCartTest() {
        bookStoreApis.deleteAllBooks(authorizeRequest().getUserId());
        bookStoreApis.addBooks();
        UserModel userWithBooks = accountApis.getUserInfo();
        step("Ensure that cart is not empty", () -> {
            Assertions.assertEquals(2, userWithBooks.getBooks().length);
        });
        cartPage.openBrowserOnTheCart()
                .assertThatCartIsNotEmpty();
        bookStoreApis.deleteAllBooks(authorizeRequest().getUserId());
        UserModel userIsCleared = accountApis.getUserInfo();
        step("Ensure that cart is not empty", () -> {
            Assertions.assertEquals(0, userIsCleared.getBooks().length);
        });
        cartPage.openBrowserOnTheCart()
                .assertThatCartIsEmpty();
    }
}

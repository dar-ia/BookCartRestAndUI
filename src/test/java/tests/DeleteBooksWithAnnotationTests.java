package tests;

import api.BookStoreApis;
import helpers.WithLogin;
import org.junit.jupiter.api.*;
import pages.CartPage;

import static api.AuthApi.authorizeRequest;

@DisplayName("Tests on books removing from the cart")
public class DeleteBooksWithAnnotationTests extends TestBase {
    CartPage cartPage = new CartPage();
    BookStoreApis bookStoreApis = new BookStoreApis();

    @Test
    @WithLogin
    @DisplayName("Positive test on books removing from the cart")
    void deleteAllBooksFromTheCartTest() {
        bookStoreApis.deleteAllBooks(authorizeRequest().getUserId());
        bookStoreApis.addBooks();
        cartPage.openBrowserOnTheCart()
                .assertSuccessLogin(authorizeRequest().getUsername())
                .assertThatCartIsNotEmpty();
        cartPage.deleteAllBooks()
                .confirmDelete()
                .assertThatCartIsEmpty();
    }
}

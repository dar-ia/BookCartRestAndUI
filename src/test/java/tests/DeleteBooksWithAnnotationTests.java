package tests;

import helpers.WithLogin;
import models.accountModels.UserModel;
import models.bookStoreModels.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CartPage;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static models.accountModels.LoginRequestModel.authorizeRequest;
import static specs.Specifications.*;
import static specs.Specifications.noCreateResponseSuccess;

public class DeleteBooksWithAnnotationTests extends TestBase{
    @Test
    @WithLogin
    void deleteAllBooksFromTheCartTest() {
        CartPage cartPage = new CartPage();


        step("Clear the cart", () -> {
            given(sendRequestWithoutBodySpec)
                    .when()
                    .delete("/BookStore/v1/Books?UserId=" + authorizeRequest().getUserId())
                    .then()
                    .spec(deleteResponseSuccessSpec);
        });
        UserModel userBefore = step("Fetch user info", () -> {
            return given(sendRequestWithoutBodySpec)
                    .when()
                    .get("/Account/v1/User/" + authorizeRequest().getUserId())
                    .then()
                    .spec(noCreateResponseSuccess)
                    .extract().as(UserModel.class);
        });
        step("Ensure that cart is empty", () -> {
            Assertions.assertEquals(0, userBefore.getBooks().length);
        });
        cartPage.openBrowserOnTheCart()
                .assertSuccessLogin(authorizeRequest().getUsername())
                .assertThatCartIsEmpty();
        ListBooksResponseModel listOfBooks =
                step("Fetch books from the catalog", () -> {
                    return given(sendRequestWithoutBodySpec)
                            .when()
                            .get("/BookStore/v1/Books")
                            .then()
                            .spec(noCreateResponseSuccess)
                            .extract().as(ListBooksResponseModel.class);
                });
        AddBooksResponse addBooksResponse =
                step("Add books to the cart", () -> {
                    AddBooksRequestModel addBooksRequest = new AddBooksRequestModel();
                    addBooksRequest.setUserId(authorizeRequest().getUserId());
                    IsbnModel book1 = new IsbnModel();
                    IsbnModel book2 = new IsbnModel();
                    book1.setIsbn(listOfBooks.getBooks()[0].getIsbn());
                    book2.setIsbn(listOfBooks.getBooks()[2].getIsbn());
                    addBooksRequest.setCollectionOfIsbns(new IsbnModel[]{book1, book2});
                    return given(sendRequestWithBodySpec)
                            .body(addBooksRequest)
                            .when()
                            .post("/BookStore/v1/Books")
                            .then()
                            .spec(createResponseSuccess)
                            .extract().as(AddBooksResponse.class);
                });
        UserModel userWithBooks = step("Fetch user info", () -> {
            return given(sendRequestWithoutBodySpec)
                    .when()
                    .get("/Account/v1/User/" + authorizeRequest().getUserId())
                    .then()
                    .spec(noCreateResponseSuccess)
                    .extract().as(UserModel.class);
        });
        step("Ensure that cart is not empty", () -> {
            Assertions.assertEquals(2, userWithBooks.getBooks().length);
        });
        cartPage.openBrowserOnTheCart()
                .assertThatCartIsNotEmpty();
        step("Delete books from the cart", () -> {
            DeleteBookRequestBody deleteBookRequest = new DeleteBookRequestBody();
            deleteBookRequest.setUserId(authorizeRequest().getUserId());
            for (IsbnModel book : addBooksResponse.getBooks()) {
                deleteBookRequest.setIsbn(book.getIsbn());

                given(sendRequestWithBodySpec)
                        .body(deleteBookRequest)
                        .log().all()
                        .when()
                        .delete("/BookStore/v1/Book")
                        .then()
                        .spec(deleteResponseSuccessSpec);
            }
        });
        UserModel userIsCleared = step("Fetch user info", () -> {
            return given(sendRequestWithoutBodySpec)
                    .when()
                    .get("/Account/v1/User/" + authorizeRequest().getUserId())
                    .then()
                    .spec(noCreateResponseSuccess)
                    .extract().as(UserModel.class);
        });
        step("Ensure that cart is not empty", () -> {
            Assertions.assertEquals(0, userIsCleared.getBooks().length);
        });

        cartPage.openBrowserOnTheCart()
                .assertThatCartIsEmpty();

    }
}

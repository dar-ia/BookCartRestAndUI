package api;

import models.bookstoremodels.AddBooksRequestModel;
import models.bookstoremodels.AddBooksResponse;
import models.bookstoremodels.IsbnModel;
import models.bookstoremodels.ListBooksResponseModel;

import static api.AuthApi.authorizeRequest;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specifications.*;

public class BookStoreApis {
    public void deleteAllBooks(String userId){
        given(sendRequestWithoutBodySpec)
                .queryParams("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(delete204ResponseSuccessSpec);
    }
    public ListBooksResponseModel listOfBooks(){
        return given(sendRequestWithoutBodySpec)
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .spec(noCreate200ResponseSuccess)
                .extract().as(ListBooksResponseModel.class);
    }
    public AddBooksResponse addBooks() {
        AddBooksRequestModel addBooksRequest = new AddBooksRequestModel();
        addBooksRequest.setUserId(authorizeRequest().getUserId());
        IsbnModel book1 = new IsbnModel();
        IsbnModel book2 = new IsbnModel();
        book1.setIsbn(listOfBooks().getBooks()[0].getIsbn());
        book2.setIsbn(listOfBooks().getBooks()[2].getIsbn());
        addBooksRequest.setCollectionOfIsbns(new IsbnModel[]{book1, book2});
        return given(sendRequestWithBodySpec)
                .body(addBooksRequest)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(create201ResponseSuccess)
                .extract().as(AddBooksResponse.class);
    }

}

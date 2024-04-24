package api;

import models.accountmodels.UserModel;

import static api.AuthApi.authorizeRequest;
import static io.restassured.RestAssured.given;
import static specs.Specifications.noCreate200ResponseSuccess;
import static specs.Specifications.sendRequestWithoutBodySpec;

public class AccountApis  {
    public UserModel getUserInfo(){
        return given(sendRequestWithoutBodySpec)
                .when()
                .get("/Account/v1/User/" + authorizeRequest().getUserId())
                .then()
                .spec(noCreate200ResponseSuccess)
                .extract().as(UserModel.class);
    }
}

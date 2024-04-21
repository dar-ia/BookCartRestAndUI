package models.accountModels;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tests.TestBase;

import static io.restassured.RestAssured.given;
import static specs.Specifications.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginRequestModel extends TestBase {
    String userName,password;
    public static LoginResponseModel authorizeRequest(){
        LoginRequestModel loginRequest = new LoginRequestModel();
        loginRequest.setUserName("user12321");
        loginRequest.setPassword("User123!");

        return given(sendSimpleRequest)
                .body(loginRequest)
                .when()
                .post("/Account/v1/Login")
                .then()
                .extract().as(LoginResponseModel.class);
    }

}

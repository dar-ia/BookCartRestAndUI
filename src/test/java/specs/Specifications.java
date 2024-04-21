package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static models.accountModels.LoginRequestModel.authorizeRequest;

public class Specifications {

    public static RequestSpecification sendSimpleRequest = with()
            .contentType(ContentType.JSON);
    public static RequestSpecification sendRequestWithBodySpec = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + authorizeRequest().getToken())
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .log(LogDetail.BODY)
            .build();

    public static RequestSpecification sendRequestWithoutBodySpec = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .addHeader("Authorization", "Bearer " + authorizeRequest().getToken())
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .build();

    public static ResponseSpecification deleteResponseSuccessSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.STATUS)
            .build();
    public static ResponseSpecification createResponseSuccess = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

    public static ResponseSpecification noCreateResponseSuccess = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}

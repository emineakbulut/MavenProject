package com.hrms.APITesting;

import com.hrms.utils.apiConstants;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class generateTokenSteps {

    public static String token;
    @Given("a JWT is generated")
    public void a_JWT_is_generated() {

        RequestSpecification generateTokenRequest=given().header("Content-type","application/json")
                .body("{\n" +
                        "  \"email\": \"kayra@gmail.com\",\n" +
                        "  \"password\": \"321pass\"\n" +
                        "}");

        Response generateTokenResponse = generateTokenRequest.when().post(apiConstants.GENERATE_TOKEN_URI);
        generateTokenResponse.prettyPrint();

        token="Bearer "+generateTokenResponse.jsonPath().getString("token");
    }
}

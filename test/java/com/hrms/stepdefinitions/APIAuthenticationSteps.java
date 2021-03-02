package com.hrms.stepdefinitions;

import com.hrms.utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.parser.Token;

import static io.restassured.RestAssured.*;

public class APIAuthenticationSteps {

    private Response response;
    public static String Token;
    private static RequestSpecification request;
    String generateTokenURI="http://3.237.189.167/syntaxapi/api/generateToken.php";

    @Given("user generates token")
    public void user_generates_token() {
       request=given().header("Content-Type","application/json");
        System.out.println(request.log().all());

        request.body(CommonMethods.readJson("C:/Users/Work/eclipse-workspace/CucumberFrameWork/src/test/resources/generateToken.json"))
                .when().post(generateTokenURI);
        System.out.println(response.prettyPrint());

        Token=response.jsonPath().getString("token");
        System.out.println(Token);
    }
}

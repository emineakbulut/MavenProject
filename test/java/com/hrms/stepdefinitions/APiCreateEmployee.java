package com.hrms.stepdefinitions;

import com.hrms.utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APiCreateEmployee {

    private static RequestSpecification request;
    private Response response;

    @Given("call create employee api")
    public void call_create_employee_api() {
        request = given().header("Content-Type","application/json").header("Authorization",APIAuthenticationSteps.Token);
        request.body(CommonMethods.readJson("C:/Users/Work/eclipse-workspace/CucumberFrameWork/src/test/resources/createEmployee.json"));
    }

    @When("retrieve response")
    public void retrieve_response() {
       response=request.post("http://3.237.189.167/syntaxapi/api/createEmployee.php");
    }

    @Then("status code is {int}")
    public void status_code_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Then("validate employee is created")
    public void validate_employee_is_created() {

    }
}

package com.hrms.APITesting;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
//given()
//when()
//then()
public class hardCodedExamples {
    String baseURI=RestAssured.baseURI="http://3.237.189.167/syntaxapi/api";
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTQ0MzY4NjEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxNDQ4MDA2MSwidXNlcklkIjoiMjUxMCJ9.zoi9_kMW91CO9dqiXtVZmqOxpXZujl-Bc67f8LhNc18";
    @Test
    public void sampleTest(){

        //preparing request to get one employee
        RequestSpecification preparingGetOneEmployeeRequest = given().header("Authorization", token)
                .header("Content-Type", "Application/json")
                .queryParam("employee_id", "15499A");
        //sending the request to the endpoint
        Response getOneEmployeeResponse = preparingGetOneEmployeeRequest.when().get("/getOneEmployee.php");

       // System.out.println(getOneEmployeeResponse.asString());
        //getOneEmployeeResponse.prettyPrint();

        //assert that status code is 200
        getOneEmployeeResponse.then().assertThat().statusCode(200);

    }

    @Test
    public void aPostCreateEmployee(){
        RequestSpecification createEmployeeRequest=given().header("Authorization",token)
                .header("Content-Type","application/json").body("{\n" +
                "  \"emp_firstname\": \"Mariii\",\n" +
                "  \"emp_lastname\": \"Romaniuk\",\n" +
                "  \"emp_middle_name\": null,\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2021-02-11\",\n" +
                "  \"emp_status\": \"employee\",\n" +
                "  \"emp_job_title\": \"IT Analyst\"\n" +
                "}");

        //Making a Post call to Create Employee
        Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");
        //Printing the Employee Id
        createEmployeeResponse.prettyPrint();
        //Assert that status code is 201
        createEmployeeResponse.then().assertThat().statusCode(201);
        //Get Employee_id
        String employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
        // Print EmployeeId
        System.out.println(employeeID);
        //Assert that message contains Entry Created
        createEmployeeResponse.then().assertThat().body("Message",equalTo("Entry Created"));
        //Assert that Employee Id is 15845A
        createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname",equalTo("Mariii"));
    }

    @Test
    public void bgetCreatedEmployee(){
       RequestSpecification getCreatedEmployee=given().header("Authorization",token)
               .header("Content-Type","application/json").queryParam("employee_id","16215A");
       Response getEmployeeResponse=getCreatedEmployee.when().get("/getOneEmployee.php");
      // getEmployeeResponse.prettyPrint();
       String empID=getEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
// checking if the empid is equal to the one mentioned in string
       boolean verifyEmployeeId=empID.equalsIgnoreCase("16215A");
       // System.out.println(verifyEmployeeId);

        Assert.assertTrue(verifyEmployeeId);
//both approach gives the same result
        getEmployeeResponse.then().assertThat().body("employee[0].employee_id",equalTo("16215A"));

    }

    @Test
    public void cUpdateEmployee(){
        RequestSpecification updateEmployeeRequest=given().header("Authorization",token)
                .header("Content-Type","application/json").body(" {\n" +
                        "            \"employee_id\": \"16051A\",\n" +
                        "            \"emp_firstname\": \"Ayran\",\n" +
                        "            \"emp_middle_name\": \"Simit\",\n" +
                        "            \"emp_lastname\": \"Peynir\",\n" +
                        "            \"emp_birthday\": \"1995-01-01\",\n" +
                        "            \"emp_gender\": \"M\",\n" +
                        "            \"emp_job_title\": \"IT Analyst\",\n" +
                        "            \"emp_status\": \"Employee\"\n" +
                        "        }");

        Response UpdateEmployeeReponse = updateEmployeeRequest.when().put("/updateEmployee.php");
        UpdateEmployeeReponse.prettyPrint();

        //assert that updated information is correct
        JsonPath jsonPath=UpdateEmployeeReponse.jsonPath();
        String employee_firstname=jsonPath.getString("employee[0].emp_firstname");
        System.out.println(employee_firstname);

        assertThat(employee_firstname,equalTo("Ayran"));

    }
    @Test
    public void dPartiallyUpdateEmployee(){
        RequestSpecification partiallyUpdateRequest=given().header("Authorization",token)
                .header("Content-Type","application/json").body("{\n" +
                        "    \"employee_id\": \"16051A\",\n" +
                        "    \"emp_lastname\": \"KremPeynir\"\n" +
                        "}");

        Response PartiallyUpdatedEmployeeResponse=partiallyUpdateRequest.when()
                .patch("/updatePartialEmplyeesDetails.php");
        PartiallyUpdatedEmployeeResponse.prettyPrint();
        //assert that body contains the Message entery updated
        JsonPath js= PartiallyUpdatedEmployeeResponse.jsonPath();
        Object Message = js.get("Message");
        System.out.println(Message);
        assertThat(Message,equalTo("Entry updated"));
        //the other method
        PartiallyUpdatedEmployeeResponse.then().body("Message", containsString("Entry updated"));

    }

    @Test
    public void eDeleteEmployeeRequest(){
        RequestSpecification deleteEmpRequest=given().header("Authorization",token)
                .header("Content-Type","application/json")
                .queryParam("employee_id","16051A");

        Response deleteReqResponse=deleteEmpRequest.when().delete("/deleteEmployee.php");
        deleteReqResponse.prettyPrint();

        //deleteEmpRequest.then().assertThat().body("message", containsString("Entry deleted"));
    }
}

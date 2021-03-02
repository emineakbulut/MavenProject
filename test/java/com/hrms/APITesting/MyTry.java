package com.hrms.APITesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class MyTry {

    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
            ".eyJpYXQiOjE2MTM5MzQyNDgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxMzk3NzQ0OCwidXNlcklkIjoiMjUxMCJ9" +
            ".Z7QUgvvZW3vL-9tGh1vXZSoA1dkVTvICx3oR8fMxfgk";
    String baseURL="http://3.237.189.167/syntaxapi/api";
    String contentType="application/json";

    @Test //junit annotations
    public void getAllJobTitle(){

        Response response=RestAssured.given().contentType(contentType)
                .header("Authorization",token)
                .when().get(baseURL+"/jobTitle.php");

        response.prettyPrint();
        int actualResponseCode=response.statusCode();
        System.out.println(actualResponseCode);

    }

    @Test
    public void getOneEmployee(){
        Response response=RestAssured.given().param("employee_id","15499A").contentType(contentType)
                .header("Authorization",token).when().get(baseURL+"/getOneEmployee.php");

        response.prettyPrint();
    }

    @Test
    public void getAllEmployeeStatus() {

        Response response = RestAssured.given().contentType("application/json")
                .header("Authorization", token).when().get(baseURL+"/employeeStatus.php");
        response.prettyPrint();
        int actualStatusCode=response.getStatusCode();
        System.out.println( actualStatusCode);

    }

    @Test
    public void createEmployee(){
        Response response=RestAssured.given().contentType(contentType).header("Authorization",token)
                .param("emp_firstname","Alma")
                .param("emp_lastname","Aldenia")
                .param("emp_middle_name","Tina")
                .param("emp_gender" , "F")
                .param("emp_birthday","2021-02-19")
                .param("emp_status", "Employee")
                .param( "emp_job_title","IT Analyst")
                .when().post(baseURL+"/createEmployee.php");
        response.prettyPrint();
        int actualStatusCode=response.getStatusCode();
        Assert.assertEquals(200,actualStatusCode);
    }
}
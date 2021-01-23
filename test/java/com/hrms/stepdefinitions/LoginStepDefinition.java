package com.hrms.stepdefinitions;

import com.hrms.utils.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static com.hrms.testbase.PageInitializer.dashboardPage;
import static com.hrms.testbase.PageInitializer.loginPage;

public class LoginStepDefinition extends CommonMethods {

    @Given("navigate to HRMS login page")
    public void navigate_to_hrms_login_page() {
        setUp();
    }

    @When("enter valid credentials")
    public void enter_valid_credentials() {
       loginPage.login("Admin","Hum@nhrm123");
    }

    @When("click on log in button")
    public void click_on_log_in_button() {
        loginPage.clickOnLoginBtn();
    }

    @Then("verify dashboard is displayed")
    public void verify_dashboard_is_displayed() {
        Assert.assertTrue(dashboardPage.welcomeMessage.isDisplayed());
    }

    @Then("quit the browser")
    public void quit_the_browser() {
       tearDown();
    }

    @Then("verify error message")
    public void verify_error_message() {
        Assert.assertEquals("Verifying error message", "Invalid credentials", loginPage.getErrorMessageText());
    }

    @When("enter invalid credentials")
    public void enter_invalid_credentials() {
        loginPage.login("Admin1","Hum@nhrm1234");
    }


}

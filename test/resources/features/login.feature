
@featureTag  #feature level tag
Feature: Login Functionality

  @validCreds @smoke #scenario level tag
  Scenario: Login with valid credential
#    Given navigate to HRMS login page
    When enter valid credentials
    And click on log in button
    Then verify dashboard is displayed
#    And quit the browser

    @smoke @syntax @regression @whatever #adding multiple scenario leveltags
  Scenario: Login with invalid credentials
#    Given navigate to HRMS login page
    When enter invalid credentials
    And click on log in button
    Then verify error message
#    And quit the browser


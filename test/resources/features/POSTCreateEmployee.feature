Feature: Validating SyntaxHRMS APIs
  Background:
    Given user generates token
    @ApiTest
    Scenario: Test if any employee is created
      Given call create employee api
      When retrieve response
      Then status code is 200
      Then validate employee is created
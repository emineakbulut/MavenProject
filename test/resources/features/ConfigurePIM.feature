Feature: Configure PIM - Optional Fields

  Scenario: Unchecking unnecessary checkboxes
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
    When click on PIM
    When click on configuration drop down
    And click on optional fields
    Then click on

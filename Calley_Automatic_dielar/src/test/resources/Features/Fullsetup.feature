Feature: User Login

  Scenario: Verify successful login with valid credentials
    Given I am on the login page
    When I enter valid credentials
    And I click on the login button
    Then I should see the dashboard page

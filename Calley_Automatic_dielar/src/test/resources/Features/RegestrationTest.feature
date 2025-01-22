Feature: User Registration
  As a new user
  I want to register on the Calley Teams platform
  So that I can use its features

  Scenario: Register a new user and select the Calley Teams plan
    Given I am on the registration page
    When I enter my first name as "Rahul"
    When I enter my email as "rahulchandeshware66@gmail.com"
    When I enter my password as "Rahul@1234"
    When I enter phone number "7760778207"
    When I check the terms and conditions checkbox
    When I submit the registration form
    When I select the Calley Teams plan
    When I accept the free trial
    Then I click to the dashboard

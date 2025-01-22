
package stepdefinitions;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pompages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class RegistrationSteps {
    WebDriver driver;
    RegistrationPage registrationPage;

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        driver = new ChromeDriver();
        
        driver.get("https://app.getcalley.com/registration.aspx"); 
        
        driver.manage().window().maximize();
        
        registrationPage = new RegistrationPage(driver);
    }

    @When("I enter my first name as {string}")
    public void i_enter_my_first_name_as(String firstName) throws InterruptedException {
    	Thread.sleep(2000);
        registrationPage.enterFirstName(firstName);
    }

    @When("I enter my email as {string}")
    public void i_enter_my_email_as(String email) {
        registrationPage.enterEmail(email);
    }

    @When("I enter my password as {string}")
    public void i_enter_my_password_as(String password) {
        registrationPage.enterPassword(password);
    }

    @When("I enter phone number {string}")
    public void i_enter_phone_number(String phoneNumber) {
        registrationPage.enterPhoneNumber(phoneNumber);
    }

    @When("I check the terms and conditions checkbox")
    public void i_check_the_terms_and_conditions_checkbox() {
        registrationPage.checkTermsAndConditions();
    }

    @When("I submit the registration form")
    public void i_submit_the_registration_form() {
        registrationPage.submitForm();
    }

    @When("I select the Calley Teams plan")
    public void i_select_the_calley_teams_plan() {
        registrationPage.selectCalleyTeamsPlan();
    }

    @When("I accept the free trial")
    public void i_accept_the_free_trial() {
        registrationPage.acceptFreeTrial();
    }

    @Then("I click to the dashboard")
    public void i_should_click_to_the_dashboard() {
        registrationPage.clickToDashboard();
    }
}

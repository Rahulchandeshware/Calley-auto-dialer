package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pompages.LoginPage;

import static org.testng.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Set up the WebDriver
       
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("https://app.getcalley.com/Login.aspx");
        loginPage = new LoginPage(driver);
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        loginPage.enterEmail("rahulchandeshware66@gmail.com");
        loginPage.enterPassword("Rahul@1234");
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("I should see the dashboard page")
    public void i_should_see_the_dashboard_page() {
        // Validate successful login by checking the page title or any unique element on the dashboard
        String expectedTitle = "Dashboard"; // Replace with the actual title of the dashboard page
        assertTrue(loginPage.getPageTitle().contains(expectedTitle));

        // Close the browser
        driver.quit();
    }
}

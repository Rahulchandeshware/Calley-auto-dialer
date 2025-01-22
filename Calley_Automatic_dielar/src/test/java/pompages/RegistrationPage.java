
package pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators for the fields and buttons
    @FindBy(id = "txtName") 
    WebElement firstNameField;

    @FindBy(id = "txtEmail") 
    WebElement emailField;

    @FindBy(id = "txtPassword") 
    WebElement passwordField;

    @FindBy(id = "txt_mobile") 
    WebElement phoneNumberField;

    @FindBy(xpath= "(//label[@for='checkbox-signup'])[1]") 
    WebElement termsCheckbox;

    @FindBy(id = "btnSignUp") 
    WebElement submitButton;

    @FindBy(xpath = "(//a[@id='ancPop2'])[1]") 
    WebElement calleyTeamsPlanOption;

    @FindBy(id = "(//input[@id='btnteamsplanactive'])[1]") 
    WebElement acceptFreeTrialButton;

    // priorty
    @FindBy(css = "btn btn-new2 icon_right") 
    WebElement dashboard;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
       
        PageFactory.initElements(driver, this);
    }

    // Wait for an element to be visible
    private void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Methods to interact with the page
    public void enterFirstName(String firstName) {
        waitForElement(firstNameField);
        firstNameField.sendKeys(firstName);
    }

    public void enterEmail(String email) {
        waitForElement(emailField);
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        waitForElement(passwordField);
        passwordField.sendKeys(password);
    }

    public void enterPhoneNumber(String phoneNumber) {
        waitForElement(phoneNumberField);
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void checkTermsAndConditions() {
        waitForElement(termsCheckbox);
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }

    public void submitForm() {
        waitForElement(submitButton);
        submitButton.click();
    }

    public void selectCalleyTeamsPlan() {
        waitForElement(calleyTeamsPlanOption);
        calleyTeamsPlanOption.click();
    }

    public void acceptFreeTrial() {
        waitForElement(acceptFreeTrialButton);
        acceptFreeTrialButton.click();
    }

    public void clickToDashboard() {
        waitForElement(dashboard);
        dashboard.click();
    }
}

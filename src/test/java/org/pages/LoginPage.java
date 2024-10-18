package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    public WebDriver driver;
    @FindBy(how = How.CSS, using = ".login-website-logo") // Adjust selector for your app logo
    @CacheLookup
     WebElement appLogo;

    @FindBy(how = How.TAG_NAME, using = "label")
    List<WebElement> labelTexts;

    @FindBy(how = How.ID, using = "userNameInput")
    @CacheLookup
     WebElement usernameField;

    @FindBy(how = How.ID, using = "passwordInput")
    @CacheLookup
    WebElement passwordField;

    @FindBy(how = How.CSS, using = ".login-button") // Adjust selector for your login button
     WebElement loginButton;

    @FindBy(how = How.CSS, using =".error-message") // Adjust selector for your error message
    @CacheLookup
    WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Method to find the App logo image (assuming you want to verify it's present)
    public boolean isAppLogoDisplayed() {
        return appLogo.isDisplayed();
    }

    // Method to get the text content of the label at a specific index
    public String getLabelText(int index) {
        return labelTexts.get(index).getText();
    }

    // Method to enter text in the "USERNAME" input field
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    // Method to enter text in the "PASSWORD" input field
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    // Method to click the "Login" button
    public void clickLoginButton() {
        loginButton.click();
    }

    // Method to enter credentials and click login (combined logic)
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // Method to wait for and get the text content of the error message
    public String getErrorMessageText() {
        // Wait for the error message to appear (implement waiting logic here)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}

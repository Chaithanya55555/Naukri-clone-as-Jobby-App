package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public WebDriver driver;

    // Assuming the heading text has a unique ID (adjust if needed)
    @FindBy(how = How.CLASS_NAME, using = "home-heading")
     WebElement headingText;

    // Assuming the description text has a class name (adjust if needed)
    @FindBy(how = How.CLASS_NAME, using = "home-description")
     WebElement descriptionText;

    // Assuming the "Find Jobs" button has a CSS selector (adjust if needed)
    @FindBy(how = How.CSS, using = ".find-jobs-button")
     WebElement findJobsButton;

    @FindBy(how = How.CSS ,using = "button[class='logout-desktop-btn']")
    WebElement logoutButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Method to get the text content of the Heading
    public String getHeadingTextContent() {
        return headingText.getText();
    }

    // Method to get the text content of the Description
    public String getDescriptionTextContent() {
        return descriptionText.getText();
    }

    // Method to click the "Find Jobs" button
    public void clickFindJobsButton() {
        findJobsButton.click();
    }

    public void logout(){
        logoutButton.click();
    }

    public Boolean isLogOutButtonDisplayed(){
        boolean isDisplay = logoutButton.isDisplayed();
        return isDisplay;
    }
}

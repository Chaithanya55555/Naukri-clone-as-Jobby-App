package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JobsPage {
    private WebDriver driver;

    // Assuming the profile image has a unique ID (adjust if needed)
    @FindBy(how = How.CLASS_NAME, using = "profile-img")
    private WebElement profileImage;

    // Assuming the profile name has a class name (adjust if needed)
    @FindBy(how = How.CLASS_NAME, using ="profile-name")
    private WebElement profileName;

    // Assuming the short bio text has a CSS selector (adjust if needed)
    @FindBy(how = How.CSS, using=".short-bio")
    private WebElement shortBioText;

    // Assuming the search input field has a unique ID (adjust if needed)
    @FindBy(how = How.CSS, using = "div.desktop-search-bar input.search-input")
    private WebElement searchInputField;

    // Assuming the search button has a CSS selector (adjust if needed)
    @FindBy(how = How.CSS, using ="div.desktop-search-bar button")
    private WebElement searchButton;

    // Assuming each job listing element has a common class name (adjust if needed)
    @FindBy(how = How.CLASS_NAME, using = "link-item")
    private List<WebElement> allJobsDisplayed;

    // Assuming the "No jobs found" image has a unique ID (adjust if needed)
    @FindBy(how = How.CLASS_NAME, using = "jobs-not-found-img")
    private WebElement noJobsFoundImage;

    // Assuming the "No jobs found" heading has a class name (adjust if needed)
    @FindBy(how = How.CLASS_NAME, using = "jobs-not-found-heading")
    private WebElement noJobsFoundHeading;

    // Assuming the "No jobs found" description has a CSS selector (adjust if needed)
    @FindBy(how = How.CSS, using =".jobs-not-found-description")
    private WebElement noJobsFoundDescription;

    public JobsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Method to find the Profile image (assuming you want to verify it's present)
    public boolean isProfileImageDisplayed() {
        return profileImage.isDisplayed();
    }

    // Method to get the text content of the Profile name
    public String getProfileNameText() {
        return profileName.getText();
    }

    // Method to get the text content of the Short bio text
    public String getShortBioText() {
        return shortBioText.getText();
    }

    // Method to enter a text in the Search input field
    public void enterSearchText(String text) {
        searchInputField.sendKeys(text);
    }

    // Method to click the Search button
    public void clickSearchButton() {
        searchButton.click();
    }

    // Method to search for a job with the specified text (combined logic)
    public void searchForJob(String text) {
        enterSearchText(text);
        clickSearchButton();
    }

    // Method to get the Count of all jobs displayed
    public int getNumberOfJobsDisplayed() {
        return allJobsDisplayed.size();
    }

    // Method to find the No jobs found image (assuming you want to verify it's presence)
    public boolean isNoJobsFoundImageDisplayed() {
        return noJobsFoundImage.isDisplayed();
    }

    // Method to get the text content of the No jobs found heading
    public String getNoJobsFoundHeadingText() {
        return noJobsFoundHeading.getText();
    }

    // Method to get the text content of the No jobs found description
    public String getNoJobsFoundDescriptionText() {
        return noJobsFoundDescription.getText();
    }
}

package org.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HeaderSection {
    public WebDriver driver;

    // Assuming the app logo is an image with a unique ID (adjust if needed)
    @FindBy(how = How.CSS, using = "div.nav-bar-large-container .website-logo")
    @CacheLookup
    private WebElement appLogoImage;

    // Assuming the app logo is linked and has a unique ID (adjust if needed)
    @FindBy(how = How.CSS, using = "div.nav-bar-large-container img")
    private WebElement appLogoLink;

    // Assuming the navbar Home link has a CSS selector (adjust if needed)
    @FindBy(how = How.XPATH, using ="//a[text() = 'Home']")
    private WebElement navbarHomeLink;

    // Assuming the navbar Jobs link has a CSS selector (adjust if needed)
    @FindBy(how = How.XPATH, using = "//a[text() = 'Jobs']")
    private WebElement navbarJobsLink;

    // Assuming the "Logout" button has a CSS selector (adjust if needed)
    @FindBy(how = How.CLASS_NAME, using = "logout-desktop-btn")
    public WebElement logoutButton; // No access modifier

    public HeaderSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isAppLogoDisplayed() {
        return appLogoImage.isDisplayed();
    }

    // Method to click the App logo link
    public void clickAppLogoLink() {
        appLogoLink.click();
    }

    // Method to click the Navbar Home link
    public void clickNavbarHomeLink() {
        navbarHomeLink.click();
    }

    // Method to click the Navbar Jobs link
    public void clickNavbarJobsLink() {
        navbarJobsLink.click();
    }

    // Method to click the "Logout" button and accept logout confirmation (assuming alert)
    public void logout() throws InterruptedException {
        logoutButton.click();
        // Handle logout confirmation (replace with your specific implementation)
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}

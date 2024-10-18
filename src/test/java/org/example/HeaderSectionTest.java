package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.HeaderSection;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HeaderSectionTest {
    public WebDriver driver;
    public HeaderSection headerSection;
    public LoginPage loginPage;
    public WebDriverWait wait;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        headerSection = new HeaderSection(driver);
        driver.get("https://qajobbyapp.ccbp.tech/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void appLogoImage(){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertTrue(headerSection.isAppLogoDisplayed(),"App logo is not displayed");

    }
    @Test(priority = 2)
    public void navBarHome(){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        headerSection.clickNavbarJobsLink();
        String expectedUrl1 = "https://qajobbyapp.ccbp.tech/jobs";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl1));
        String currentUrl1 = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl1,expectedUrl1,"URLs do not match");
    }
    @Test(priority = 3)
    public void appLogoLink(){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        headerSection.clickNavbarJobsLink();
        headerSection.clickAppLogoLink();
        String expectedUrl3 = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl3));
        String currentUrl3 = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl3,expectedUrl3,"URLs do not match");

    }
    @Test(priority = 4)
    public void navBarHOme(){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        headerSection.clickNavbarJobsLink();
        headerSection.clickNavbarHomeLink();
        String url = "https://qajobbyapp.ccbp.tech/";
        wait.until(ExpectedConditions.urlToBe(url));
        String url1  = driver.getCurrentUrl();
        Assert.assertEquals(url,url1,"URLs do not match");
    }
    @Test(priority = 5)
    public void logOut() throws InterruptedException {
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        headerSection.logout();
        wait.until(ExpectedConditions.urlToBe("https://qajobbyapp.ccbp.tech/login"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://qajobbyapp.ccbp.tech/login","URLs do not match");

    }
}

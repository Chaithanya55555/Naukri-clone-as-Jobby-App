package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.HomePage;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest {
    public WebDriver driver;
    public LoginPage loginPage;
    public HomePage homePage;
    public WebDriverWait wait;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.get("https://qajobbyapp.ccbp.tech/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void homepageHeading() {
        loginPage.login("rahul", "rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(homePage.getHeadingTextContent(),"Find The Job That Fits Your Life","Heading text does not match");
        Assert.assertEquals(homePage.getDescriptionTextContent(),"Millions of people are searching for jobs, salary information, company reviews. Find the job that fits your abilities and potential.","Description text does not match");
    }
    @Test(priority = 2)
    public void findJobs(){
        loginPage.login("rahul", "rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        homePage.clickFindJobsButton();
        wait.until(ExpectedConditions.urlToBe("https://qajobbyapp.ccbp.tech/jobs"));
        Assert.assertEquals(driver.getCurrentUrl(),"https://qajobbyapp.ccbp.tech/jobs","URLs do not match");
    }
}

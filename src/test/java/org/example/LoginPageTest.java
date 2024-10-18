package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest {
    public WebDriver driver;
    public LoginPage loginPage;
    public WebDriverWait wait;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://qajobbyapp.ccbp.tech/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void loginPageUI(){
        Assert.assertTrue(loginPage.isAppLogoDisplayed(),"App logo is not displayed");
        Assert.assertEquals(loginPage.getLabelText(0) ,"USERNAME","Username label text does not match");
        Assert.assertEquals(loginPage.getLabelText(1) ,"PASSWORD","Password label text does not match");
    }
    @Test(priority = 2)
    public void emptyInput(){
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),"*Username or password is invalid","Error text with empty input fields does not match");
    }
    @Test(priority = 3)
    public void emptyUsername(){
        loginPage.enterPassword("rahul@2021");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),"*Username or password is invalid","*Username or password is invalid");
    }
    @Test(priority =  4)
    public void emptyPassword(){
        loginPage.enterUsername("rahul");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),"*Username or password is invalid","Error text with empty input field do not match");
    }
    @Test(priority = 5)
    public void invalidPassword(){
        loginPage.login("rahul","rahul");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),"*username and password didn't match","Error text with invalid password do not match");
    }
    @Test(priority = 6)
    public void successfulLogin(){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,expectedUrl,"URLs do not match");

    }
}


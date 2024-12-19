package org.example;

import org.baseClasses.baseClassForTestNG;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest extends baseClassForTestNG {

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
        loginPage.enterPassword(p.getProperty("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageText(),"*Username or password is invalid","*Username or password is invalid");
    }
    @Test(priority =  4)
    public void emptyPassword(){
        loginPage.enterUsername(p.getProperty("username"));
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
        loginPage.login(p.getProperty("username"),p.getProperty("password"));
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,expectedUrl,"URLs do not match");

    }
}


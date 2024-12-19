package org.example;


import org.baseClasses.baseClassForTestNG;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class HomePageTest extends baseClassForTestNG {

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

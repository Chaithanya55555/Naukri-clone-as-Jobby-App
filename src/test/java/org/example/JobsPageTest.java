package org.example;


import org.baseClasses.baseClassForTestNG;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class JobsPageTest extends baseClassForTestNG {

    @DataProvider(name = "data")
    public Object[][] dataDetails(){
        Object[][] data = {
                {"Devops Engineer","9"},
                {"Backend Engineer","11"},
                {"Frontend Engineer","13"},
                {"Fullstack Developer","6"},
                {"Data Scientist","11"},
                {"ML Engineer","10"},
        };
        return data;
    }

    @Test(priority = 1)
    public void profileUI(){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        homePage.clickFindJobsButton();
        Assert.assertTrue(jobsPage.isProfileImageDisplayed(),"Profile image is not displayed");
        Assert.assertEquals(jobsPage.getProfileNameText(),"Rahul Attluri","Profile name does not match");
        Assert.assertEquals(jobsPage.getShortBioText(),"Lead Software Developer and AI-ML expert","Bio does not match");
    }
    @Test(priority = 2 ,dataProvider = "data")
    public void successfulSearch(String job,String noOfJobs){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        homePage.clickFindJobsButton();
        jobsPage.enterSearchText(job);
        jobsPage.clickSearchButton();
        String jobsCount = String.valueOf(jobsPage.getNumberOfJobsDisplayed());
        Assert.assertEquals(jobsCount,noOfJobs,"Jobs count does not match");
    }
    @Test(priority = 3)
    public void unsuccessfulSearch(){
        loginPage.login("rahul","rahul@2021");
        loginPage.clickLoginButton();
        String expectedUrl = "https://qajobbyapp.ccbp.tech/";
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        homePage.clickFindJobsButton();
        jobsPage.enterSearchText("Netflix");
        jobsPage.clickSearchButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("jobs-not-found-heading")));
        Assert.assertTrue(jobsPage.isNoJobsFoundImageDisplayed(),"Jobs not found heading does not match");
        Assert.assertEquals(jobsPage.getNoJobsFoundHeadingText(),"No Jobs Found","Jobs not found heading does not match");
        Assert.assertEquals(jobsPage.getNoJobsFoundDescriptionText(),"We could not find any jobs. Try other filters.","Jobs not found description does not match");
    }

}

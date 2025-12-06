package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestngPerformanceTests extends TestBase{
	
	@Test(groups = {"regression", "performance"})
	@Severity(SeverityLevel.NORMAL)
    @Description("performace options adding KPI for job title")
    @Step("login and navigate to performance page, add KPI")
    @Feature("Performance Menu")
    @Story("Performance Options")
	public void shouldAddNewKpiForJobTitleSuccessfully() throws InterruptedException {
		
		login().logMeIn();
		performance().clickOnPerformanceMenu();
		performance().clickOnPerformanceConfigure();
		performance().clickOnKPIs();
		performance().searchResultKPIAndDelete("Bug Detection Rate");
		performance().clickOnPerformanceConfigure();
		performance().clickOnKPIs();
		performance().clickOnAddKPIButton();
		performance().enterKeyPerformanceIndicator("Bug Detection Rate");
		performance().selecrJobTitleToAddKPI();
		performance().enterMinAndMaxRatings();
		performance().clickOnSaveKPIButton();
		performance().clickOnPerformanceConfigure();
		Assert.assertTrue(performance().searchResultKPI("Bug Detection Rate"));
		performance().clickOnDeleteKPIIcon();
		
	}
	
}

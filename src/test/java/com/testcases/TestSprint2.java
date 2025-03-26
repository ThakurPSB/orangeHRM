package com.testcases;

//import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.main.Keywords;
import com.pages.AdminJobTitles;
import com.pages.AdminMenu;
import com.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestSprint2 extends TestBase {
	
	protected static Keywords kw;
	public LoginPage login;
	public AdminMenu admin;
	public AdminJobTitles jobs;

	@BeforeMethod
	public void pageSetup() {
        login = new LoginPage(kw); 
        admin = new AdminMenu(kw);
        jobs = new AdminJobTitles(kw);
        
    }
	
	
	//private static final Logger LOG = Logger.getLogger(TestSprint2.class);
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking admin page options")
    @Step("login and navigate to admin page, search with different options")
    @Feature("admin Features")
    @Story("Admin page options")
	public void SearchSystemUserWithDifferentSearchOptions() throws InterruptedException {
		
		login.logMeIn();
		
		admin.clickOnAdminMenu();
		
		
		admin.enterUsername("admin");
		admin.clickOnSearchButton();
		Assert.assertTrue(admin.searchResultUsername("admin"));
		
		admin.clickOnResetButton();
		admin.selectUserRole("ESS");
		admin.clickOnSearchButton();
		Assert.assertTrue(admin.searchResultRole("ESS"));
		
		
		admin.clickOnResetButton();
		admin.enterEmployeeName("Piyush");
		admin.clickOnSearchButton();
		Assert.assertTrue(admin.searchResultsEmployeeName("Piyush Thakur"));
		
		
		admin.clickOnResetButton();
		admin.selectLoginStatus("Enabled");
		admin.clickOnSearchButton();
		Assert.assertTrue(admin.searchResultStatus("Enabled"));
		
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking admin page options job submenu")
    @Step("login and navigate to admin page, check job options")
    @Feature("admin Features JObs menu")
    @Story("Admin page options jobs submenu checking")
	public void checkForJobMenuAreShowingCorrectOptions() throws InterruptedException {
		
		login.logMeIn();
		
		
		jobs.clickOnAdminMenu();
		jobs.clickOnJobSubMenu("Job Titles");
		Assert.assertTrue(jobs.checkElementinTable("Assistant Manager"));
		jobs.clickOnJobSubMenu("Pay Grades");
		Assert.assertTrue(jobs.checkElementinTable("Grade A"));
		jobs.clickOnJobSubMenu("Employment Status");
		Assert.assertTrue(jobs.checkElementinTable("Active"));
		jobs.clickOnJobSubMenu("Job Categories");
		Assert.assertTrue(jobs.checkElementinTable("Craft Workers"));
		jobs.clickOnJobSubMenu("Work Shifts");
		Assert.assertTrue(jobs.checkElementinTable("Day Shift"));
		
		
	}
	
	
	@Test(enabled = false)
	public void TryAndError() throws InterruptedException {
		
		login.logMeIn();
		
		//AdminJobTitles jobs = new AdminJobTitles();
		
		
	}
	
}

package com.testcases;

//import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.AdminMenu;
import com.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestSprint2 extends TestBase {
	
	//private static final Logger LOG = Logger.getLogger(TestSprint2.class);
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking admin page options")
    @Step("login and navigate to admin page, search with different options")
    @Feature("admin Features")
    @Story("Admin page options")
	public void SearchSystemUserWithDifferentSearchOptions() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		
		AdminMenu admin = new AdminMenu();
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
	
}

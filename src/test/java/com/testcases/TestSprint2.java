package com.testcases;


//import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.AdminJobTitles;
import com.pages.AdminMenu;
import com.pages.AdminOrganizationMenu;
import com.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestSprint2 extends TestBase {
	
	private LoginPage login;
	private AdminMenu admin;
	private AdminJobTitles jobs;
	private AdminOrganizationMenu orgMenu;

	@BeforeMethod
	public void pageSetup() {
        login = new LoginPage(kw); 
        admin = new AdminMenu(kw);
        jobs = new AdminJobTitles(kw);
        orgMenu = new AdminOrganizationMenu(kw);
        
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
		admin.clickOnAdminMenu();
		
		
		jobs.clickOnAdminMenu();
		jobs.clickOnJobSubMenu("Job Titles");
		Assert.assertTrue(jobs.checkElementinTable("Assistant Manager"));
		jobs.clickOnJobSubMenu("Pay Grades");
		Assert.assertTrue(jobs.checkElementinTable("Grade A"));
		jobs.clickOnJobSubMenu("Employment Status");
		Assert.assertTrue(jobs.checkElementinTable("Active"));
		jobs.clickOnJobSubMenu("Job Categories");
		
	
		
	}
	
	

	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking admin page options organization submenu")
    @Step("login and navigate to admin page, check organization options")
    @Feature("admin Features Organization menu")
    @Story("Admin page options oragnization submenu checking")
	public void checkForOrganizationMenuAreShowingCorrectOptions() throws InterruptedException {
		
		login.logMeIn();
		admin.clickOnAdminMenu();
		
		orgMenu.clickOnOrgMenu();
		orgMenu.clickOnOrgSubMenu("General Information");
		orgMenu.clickOnEditButton();
		orgMenu.updateRegNum("123456");
		orgMenu.updateOrgName("PsychoCorps");
		orgMenu.updateTaxID("PVT1419S8809");
		orgMenu.clickOnSave();
		Assert.assertTrue(orgMenu.SaveToastMessageText());
		Assert.assertTrue(orgMenu.orgNameTextCheck("PsychoCorps"));
		Assert.assertTrue(orgMenu.taxIDtextCheck("PVT1419S8809"));
		Assert.assertTrue(orgMenu.regNumberCheck("123456"));
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to add location with all the details provided")
    @Step("login and navigate to admin page, check organization option location menus")
    @Feature("admin Features Organization menu locations")
    @Story("Admin page options oragnization submenu checking location field")
	public void CheckIfAddLocationOptionIsWorkingOrNot() throws InterruptedException {
		login.logMeIn();
		admin.clickOnAdminMenu();
		orgMenu.clickOnOrgMenu();
		orgMenu.clickOnOrgSubMenu("Locations");
		orgMenu.searchLocation("MumbaiCBO");
		orgMenu.deleteSelected("MumbaiCBO");
		orgMenu.clickOnAddLocationButton();
		orgMenu.enterLocation("MumbaiCBO");
		orgMenu.enterCity("Mumbai");
		orgMenu.enterState("MH");
		orgMenu.enterZip("400521");
		orgMenu.selectCountry("India");
		orgMenu.clickOnSaveLocationButton();
		Assert.assertTrue(orgMenu.SaveToastMessageText());
	}
	
	@Test(enabled = false)
	public void TryAndError() throws InterruptedException {
		
		login.logMeIn();
		admin.clickOnAdminMenu();
		orgMenu.clickOnOrgMenu();
		orgMenu.clickOnOrgSubMenu("Locations");
		
		
	}
	
}

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

public class TestngAdminTests extends TestBase{
	
	@Test(groups = {"regression", "smoke", "admin"})
    @Severity(SeverityLevel.NORMAL)
    @Description("Checking admin page options")
    @Step("login and navigate to admin page, search with different options")
    @Feature("Admin Menu")
    @Story("Admin page options")
	public void shouldSearchSystemUsersWithDifferentFilters() throws InterruptedException {
		
		login().logMeIn();
		
		admin().clickOnAdminMenu();
		admin().enterUsername("admin");
		admin().clickOnSearchButton();
		Assert.assertTrue(admin().searchResultUsername("admin"));
		
		admin().clickOnResetButton();
		admin().selectUserRole("ESS");
		admin().clickOnSearchButton();
		Assert.assertTrue(admin().searchResultRole("ESS"));
		
		
		admin().clickOnResetButton();
		admin().enterEmployeeName("Piyush");
		admin().clickOnSearchButton();
		Assert.assertTrue(admin().searchResultsEmployeeName("Piyush Thakur"));
		
		
		admin().clickOnResetButton();
		admin().selectLoginStatus("Enabled");
		admin().clickOnSearchButton();
		Assert.assertTrue(admin().searchResultStatus("Enabled"));
		
	}
	
	@Test(groups = {"regression", "admin"})
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking admin page options job submenu")
    @Step("login and navigate to admin page, check job options")
    @Feature("Admin Menu")
    @Story("Admin page options jobs submenu checking")
	public void shouldDisplayCorrectOptionsInJobMenu() throws InterruptedException {
		
		login().logMeIn();
		admin().clickOnAdminMenu();
		
		jobs().clickOnAdminMenu();
		jobs().clickOnJobSubMenu("Job Titles");
		Assert.assertTrue(jobs().checkElementinTable("Assistant Manager"));
		jobs().clickOnJobSubMenu("Pay Grades");
		Assert.assertTrue(jobs().checkElementinTable("Grade A"));
		jobs().clickOnJobSubMenu("Employment Status");
		Assert.assertTrue(jobs().checkElementinTable("Active"));
        
	}
	
	

	@Test(groups = {"regression", "admin"})
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking admin page options organization submenu")
    @Step("login and navigate to admin page, check organization options")
    @Feature("Admin Menu")
    @Story("Admin page options oragnization submenu checking")
	public void shouldDisplayCorrectOptionsInOrganizationMenu() throws InterruptedException {
		
		login().logMeIn();
		admin().clickOnAdminMenu();
		
		orgMenu().clickOnOrgMenu();
		orgMenu().clickOnOrgSubMenu("General Information");
		orgMenu().clickOnEditButton();
		orgMenu().updateRegNum("123456");
		orgMenu().updateOrgName("PsychoCorps");
		orgMenu().updateTaxID("PVT1419S8809");
		orgMenu().clickOnSave();
		Assert.assertTrue(orgMenu().SaveToastMessageText());
		Assert.assertTrue(orgMenu().orgNameTextCheck("PsychoCorps"));
		Assert.assertTrue(orgMenu().taxIDtextCheck("PVT1419S8809"));
		Assert.assertTrue(orgMenu().regNumberCheck("123456"));
	}
	
	@Test(groups = {"regression", "admin"})
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to add location with all the details provided")
    @Step("login and navigate to admin page, check organization option location menus")
    @Feature("Admin Menu")
    @Story("Admin page options oragnization submenu checking location field")
	public void shouldAllowAddingLocationSuccessfully() throws InterruptedException {
		login().logMeIn();
		admin().clickOnAdminMenu();
		orgMenu().clickOnOrgMenu();
		orgMenu().clickOnOrgSubMenu("Locations");
		orgMenu().searchLocation("MumbaiCBO");
		orgMenu().deleteSelected("MumbaiCBO");
		orgMenu().clickOnAddLocationButton();
		orgMenu().enterLocation("MumbaiCBO");
		orgMenu().enterCity("Mumbai");
		orgMenu().enterState("MH");
		orgMenu().enterZip("400521");
		orgMenu().selectCountry("India");
		orgMenu().clickOnSaveLocationButton();
		Assert.assertTrue(orgMenu().SaveToastMessageText());
	}
	
	@Test(groups = {"regression", "admin"})
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to Add skills")
    @Step("login and navigate to admin page, check organization option skills menus")
    @Feature("Admin Menu")
    @Story("Admin page options oragnization submenu checking skills")
	public void shouldAllowAddingSkillSuccessfully() throws InterruptedException {
		login().logMeIn();
		admin().clickOnAdminMenu();
		qualification().clickOnQualificationMenu();
		qualification().clickOnSkills();
		qualification().deleteEnteredSkill("java");
		qualification().clickOnAddSkillsButton();
		qualification().enterSkillName("java");
		qualification().clickOnSaveSkillButton();
		Assert.assertTrue(orgMenu().SaveToastMessageText());
	}
	

	
}

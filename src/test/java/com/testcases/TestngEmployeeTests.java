package com.testcases;

import java.awt.AWTException;
import java.util.concurrent.TimeoutException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestngEmployeeTests extends TestBase {
	
	@Test(groups = {"regression", "employee"})
	@Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu > employee list > employee search.")
    @Step("Login, navigate to PIM > employee list > select search criteri > check if employee matching are displayed")
    @Feature("Employee Module")
    @Story("PIM employee list Menus")
	public void shouldSearchEmployeeUsingDifferentPimFilters() throws InterruptedException {
		
		login().logMeIn();
		
		pim().clickOnPIM();
		emplist().clickOnEmployeeListMenu();

		emplist().enterEmployeeID(68166);
		emplist().clickOnSearchButton();
		Assert.assertTrue(emplist().searchResultID("68166"));
		emplist().clickOnResetButton();
		emplist().clickOnEmployeeListMenu();
		
		emplist().enterEmployeeName("Piyush");
		emplist().clickOnSearchButton();
		Assert.assertTrue(emplist().searchResultfirstName("Piyush"));
		emplist().clickOnResetButton();
		emplist().clickOnEmployeeListMenu();
		
		emplist().enterEmployeeName("Thakur");
		emplist().clickOnSearchButton();
		Assert.assertTrue(emplist().searchResultLastName("Thakur"));
		emplist().clickOnResetButton();
		emplist().clickOnEmployeeListMenu();
		
		emplist().selectEmploymentStatus("Active");
		emplist().clickOnSearchButton();
		Assert.assertTrue(emplist().searchResultEmploymentStatus("Active"));
		emplist().clickOnResetButton();
		emplist().clickOnEmployeeListMenu();
		
		emplist().enterSupervisorName("Piyush");
		emplist().clickOnSearchButton();
		Assert.assertTrue(emplist().searchResultSupervisor("Piyush Thakur"));
		emplist().clickOnResetButton();
		emplist().clickOnEmployeeListMenu();
		
		emplist().selectJobTitle("Executive");
		emplist().clickOnSearchButton();
		Assert.assertTrue(emplist().searchResultJobTitle("Executive"));
		emplist().clickOnResetButton();
		emplist().clickOnEmployeeListMenu();
		
		emplist().selectSubUnit("Mumbai");
		emplist().clickOnSearchButton();
		Assert.assertTrue(emplist().searchResultSubUnit("Mumbai"));
		emplist().clickOnResetButton();
		emplist().clickOnEmployeeListMenu();
		Thread.sleep(3000);
		
	}
	
	@Test(groups = {"regression", "smoke", "employee"})
	@Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu >  Add Employee > adding user.")
    @Step("Login, navigate to PIM > Add employee > enter the details and add employee")
    @Feature("Employee Module")
    @Story("Add Employee")
    public void shouldCreateEmployeeWithAllRequiredDetails() throws InterruptedException, AWTException, TimeoutException {
		login().logMeIn();
		
		pim().clickOnPIM();
		emplist().clickOnEmployeeListMenu();
		emplist().enterEmployeeName("Wisley");
		emplist().clickOnSearchButton();
		emplist().DeleteSelectedUsers();
		pim().clickOnPIM();
		addemp().clickOnAddNewEmployeeButton();
		addemp().selectProfilePic();
		addemp().EnterUserDetails("George", "L", "Wisley");
		addemp().enterEmployeeID();
		addemp().clickOnCreateLoginDetails();
		addemp().enterLoginDetails("George", "User@2025", false);
		addemp().clickOnSaveButton();
		Assert.assertTrue(addemp().SaveToastMessageText());
		
	}

}

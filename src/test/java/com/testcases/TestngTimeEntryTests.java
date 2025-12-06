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

public class TestngTimeEntryTests extends TestBase {
	
	@Test(groups = {"regression", "time-entry", "smoke"})
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify timesheet editable by employee")
    @Step("login as user and navigate to time page, edit time sheet")
    @Feature("Time Module")
    @Story("Timesheet Submission")
	public void shouldSubmitTimesheetWithDetailsSuccessfully() throws InterruptedException {
		login().logMeInAsUser();
		timemenu().clickOnMenu("Time");
		timemenu().clickOnCreateTimesheetButtonOrEditTimesheetButton();
		timemenu().clickOnDeleteTimesheetEntryIcon();
		timemenu().enterProjectName("S");
		timemenu().selectActivity("c");
		timemenu().enterHoursForMondayAndTuesday();
		timemenu().clickOnSaveTimesheetButton();
		Assert.assertTrue(timemenu().SaveToastMessageText());
	}
	
	
	
	@Test(groups = {"regression", "time-entry"},dependsOnMethods = "shouldSubmitTimesheetWithDetailsSuccessfully")
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify timesheet submission and edit by employee")
    @Step("login as user and navigate to time page, add time sheet")
    @Feature("Time Module")
    @Story("Timesheet editing before submission")
	public void shouldAllowEmployeeToEditTimesheetBeforeSubmission() throws InterruptedException {
		login().logMeInAsUser();
		timemenu().clickOnMenu("Time");
		String beforeValue = timemenu().getMondayHoursViewMode();
		
		timemenu().clickOnEditTimesheetButton();
		timemenu().enterUpdatedMondayHours(beforeValue);
		timemenu().clickOnSaveTimesheetButton();
		
		String afterValue = timemenu().getMondayHoursViewMode();
		
		Assert.assertNotEquals(afterValue, beforeValue);
	}
	
	@Test(groups = {"regression", "time-entry", "smoke"},dependsOnMethods = "shouldAllowEmployeeToEditTimesheetBeforeSubmission")
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify timesheet approved/rejected by manager")
    @Step("login as user and navigate to time page, add time sheet and submit, login as manager and approve/reject timesheet")
    @Feature("Time Module")
    @Story("Timesheet approval/Rejection")
	public void shouldAllowManagerToApproveOrRejectSubmittedTimesheet() throws InterruptedException {
		login().logMeInAsUser();
		timemenu().clickOnMenu("Time");
		timemenu().clickOnSubmitTimesheetButton();
		login().clickOnLogoutButton();
		login().logMeIn();
		timemenu().clickOnMenu("Time");
		timemenu().clickOnViewTimesheetButton();
		timemenu().clickOnRejectTimesheetButton();
		Assert.assertTrue(timemenu().SaveToastMessageText());
		
	}

}

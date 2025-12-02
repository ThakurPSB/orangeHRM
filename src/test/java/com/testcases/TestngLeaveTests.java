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

public class TestngLeaveTests extends TestBase {
	
	@Test(groups = {"smoke"})
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves")
    @Step("login and navigate to leave page, check apply leave menu ")
    @Feature("Leave Module")
    @Story("Apply leave successfully with valid inputs")
	public void ApplyLeaveWithValidInputs() throws InterruptedException {
		login().logMeIn();
		login().clickOnMenu("Leave");
		leaveApply().cancelLeaveIfAlreadyTaken();
		login().clickOnLogoutButton();
		login().logMeInAsUser();
		login().clickOnMenu("Leave");
		leaveApply().clickOnApplyLeave();
		leaveApply().selectLeaveType("e");
		leaveApply().selectFromDate();
		leaveApply().selectToDate();
		leaveApply().clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply().leaveAppliedSuccessfullyOrOverlapingLeave());
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves with invalid dates")
    @Step("login and navigate to leave page, check apply leave menu > enter dates > apply ")
    @Feature("Leave Module")
    @Story("Apply leave with overlapping dates")
	public void ApplyLeaveWithOverlappingDates() throws InterruptedException {
		
		login().logMeIn();
		leaveApply().clickOnLeaveMenu();
		leaveApply().clickOnApplyLeave();
		leaveApply().selectLeaveType("e");
		leaveApply().selectFromDate();
		leaveApply().selectToDate();
		leaveApply().clickOnApplyLeaveButton();
		leaveApply().clickOnApplyLeave();
		leaveApply().selectLeaveType("e");
		leaveApply().selectFromDate();
		leaveApply().selectToDate();
		leaveApply().clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply().failedToApplyLeaveToastText());
		myLeave().clickOnLeaveMenu();
		myLeave().clickOnMyLeaveMenu();
		myLeave().cancelAllLeaves();
	}
	
	//@Test(dependsOnMethods = {"ApplyLeaveWithValidInputs"})
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to approve leaves as supervisor")
    @Step("login and navigate to leave page, check applied leave and approve ")
    @Feature("Leave Module")
    @Story("Approve leave as supervisor")
	public void ApproveLeaveAsSupervisor() throws InterruptedException {
		
		login().logMeIn();
		login().clickOnMenu("Leave");
		leaveApply().clickOnApproveLeaveButton();
		Assert.assertTrue(leaveApply().leaveAppliedSuccessfullyOrOverlapingLeave());
		
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves without selecting leave type")
    @Step("login and navigate to leave page, check apply leave menu > enter dates > apply ")
    @Feature("Leave Module")
    @Story("Apply leave without leave type")
	public void ApplyLeaveWithoutSelectingLeaveType() {
		
		login().logMeIn();
		leaveApply().clickOnLeaveMenu();
		leaveApply().clickOnApplyLeave();
		leaveApply().selectFromDate();
		leaveApply().selectToDate();
		leaveApply().clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply().checkWarningForLeaveType());
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves with past dates")
    @Step("login and navigate to leave page, check apply leave menu > enter dates > apply ")
    @Feature("Leave Module")
    @Story("Apply leave with past dates")
	public void ApplyLeaveWithWeekendDates() throws InterruptedException {
		
		login().logMeIn();
		leaveApply().clickOnLeaveMenu();
		leaveApply().clickOnApplyLeave();
		leaveApply().selectLeaveType("e");
		leaveApply().selectSaturdayFromDate();
		leaveApply().selectSundayToDate();
		leaveApply().clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply().errorNonWorkingDayLeave());
		myLeave().clickOnLeaveMenu();
		myLeave().clickOnMyLeaveMenu();
		myLeave().cancelAllLeaves();
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves without sufficient balance")
    @Step("login and navigate to leave page, check apply leave with floating leave ")
    @Feature("Leave Module")
    @Story("Apply leave with insufficient balance and check for error")
	public void ApplyLeaveWithInsufficientBalance() throws InterruptedException {
	
		
		login().logMeIn();
		leaveApply().clickOnLeaveMenu();
		leaveApply().clickOnApplyLeave();
		leaveApply().selectLeaveType("f"); // f for floating leave
		leaveApply().selectFromDate();
		leaveApply().selectToDate();
		leaveApply().clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply().errorLeaveBalance());
		
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Add leave entitlement to an employee")
    @Step("login and navigate to leaveentitlement,add leaves ")
    @Feature("Leave Module")
    @Story("Add Leave entitlement to single employee")
	public void addLeaveEntitlement() throws InterruptedException  {
		login().logMeIn();
		login().clickOnMenu("Leave");
		entitlement().clickOnLeaveEntitlementMenu();
		entitlement().clickEmployeeEntitlement();
		entitlement().enterEmployeeNameInSearchBoxEmplpyeeEntitlement("Adriana");
		entitlement().selectLeaveTypeInEntitlement("Earned");
		entitlement().clickOnSearchButton();
		double leaveBalance = entitlement().CheckLeaveBalance();
		leaveApply().clickOnLeaveMenu();
		entitlement().clickOnLeaveEntitlementMenu();
		entitlement().clickOnAddEntitlement();
		entitlement().selectIndividualOrMultipleEmployee("individual");
		entitlement().enterEmployeeName("Adriana");
		entitlement().selectLeaveType("Earned");
		entitlement().enterEntitlement("1");
		entitlement().clickOnSaveButton();
		entitlement().clickOnConfirmButton();
		Assert.assertTrue(entitlement().SaveToastMessageText());
		entitlement().clickOnLeaveEntitlementMenu();
		entitlement().clickEmployeeEntitlement();
		entitlement().enterEmployeeNameInSearchBoxEmplpyeeEntitlement("Adriana");
		entitlement().selectLeaveTypeInEntitlement("Earned");
		entitlement().clickOnSearchButton();
		Assert.assertTrue(entitlement().CheckLeaveBalance()>leaveBalance);
		
	
	}




}

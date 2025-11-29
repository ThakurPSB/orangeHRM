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

public class TestngDashboardTests extends TestBase {
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("To Verify if all the links in the profile menu working or not")
    @Step("Login , click on userprofile menu and check each option one by one")
    @Feature("Dashboard Module")
    @Story("Respected links / dialog box should be visible")
	public void checkIfAllTheLinksInProfileMenuAreWorking() throws InterruptedException {
		
		login().logMeIn();
		
		profile().clickOnUserProfile();
		profile().clickOnAbout();
		Assert.assertTrue(profile().aboutInformation());
		profile().closeAboutInformatio();
		profile().clickOnUserProfile();
		profile().clickOnSupport();
		Assert.assertTrue(profile().supportInformation());
		profile().clickOnUserProfile();
		profile().clickOnChangePassword();
		Assert.assertTrue(profile().checkIfChangePasswordPage());
	}
	
	@Test()
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify dashboard punch in link")
    @Step("login and navigate to dashboard page, click on punch in link")
    @Feature("Dashboard Module")
    @Story("Dashboard Punch In Link")
	public void dashboardPunchInLinkDirectToPunchInPage() throws InterruptedException {
		login().logMeInAsUser();
		dashboard().clickOnMenu("Dashboard");
		dashboard().clickOnPunchInButton();
		Assert.assertTrue(dashboard().checkUrlChangeToAttendance("http://localhost/orangehrm/web/index.php/attendance/punchIn"));
	}
	
	@Test()
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify dashboard apply leave link")
    @Step("login and navigate to dashboard page, click on apply leave link")
    @Feature("Dashboard Module")
    @Story("Dashboard apply leaveLink")
	public void dashboardApplyLeaveLinkDirectToLeavePage() {
		login().logMeInAsUser();
		dashboard().clickOnMenu("Dashboard");
		dashboard().clickOnDashboardApplyLeaveShortcutbutton();
		Assert.assertTrue(dashboard().checkUrlChangeToAttendance("http://localhost/orangehrm/web/index.php/leave/applyLeave"));
	}
	
	@Test()
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify dashboard my timesheet link")
    @Step("login and navigate to dashboard page, click on my timesheet link")
    @Feature("Dashboard Module")
    @Story("Dashboard my timesheet Link")
	
	public void dashboardMyTimesheetLinkDirectToMyTimesheetPage() {
		login().logMeInAsUser();
		dashboard().clickOnMenu("Dashboard");
		dashboard().clickOnDashboardMyTimesheetShortcutbutton();
		Assert.assertTrue(dashboard().checkUrlChangeToAttendance("http://localhost/orangehrm/web/index.php/time/viewMyTimesheet"));
	}
	

}

package com.testcases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.DashboardMenu;
import com.pages.LoginPage;
import com.pages.PerformanceMenu;
import com.pages.RecruitmentMenu;
import com.pages.TimeMenu;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestSprint3 extends TestBase{

	/*
	 * private page object fields to encapsulate and reuse them across tests.
	 */
	private LoginPage login;
	private RecruitmentMenu recruitment;
	private PerformanceMenu performance;
	private TimeMenu timemenu;
	private DashboardMenu dashboard;
	
	/*
	 * Inject dependencies "key" for consistent setup for each test run.
	 * */
	@BeforeMethod
	public void pageSetup() {
        login = new LoginPage(key); 
        recruitment = new RecruitmentMenu(key);
        performance = new PerformanceMenu(key);
        timemenu = new TimeMenu(key);
        dashboard = new DashboardMenu(key);
	}
	
	@AfterMethod
	public void tearDowntest() throws InterruptedException {
		login = null;
		recruitment = null;
		performance = null;
		timemenu = null;
		Thread.sleep(3000);
	
	}
	
	@Test(groups = {"smoke"})
    @Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options vacancies")
    @Step("login and navigate to recruitment page, add vacancy for asst mananger")
    @Feature("recruitment Features")
    @Story("vacancies options")
	public void addVacancywithallDetailsAndCheckIfReflectsInCandidateField() throws InterruptedException {
		
		login.logMeIn();
		recruitment.clickOnRecruitmentMenu();
		recruitment.clickOnVacanciesMenu();
		recruitment.clickOnAddVacancyButton();
		recruitment.enterVacancyName("Marketing Asst Manager");
		recruitment.selectJobTitle("Assistant Manager");
		recruitment.selectHiringManager("Piyush");
		recruitment.enterNumberOfVacancy("1");
		recruitment.clickOnSaveVacancyButton();
		recruitment.clickOnVacanciesMenu();
		recruitment.clickOnVacancySearchButton();
		Assert.assertTrue(recruitment.checkVacancyAddedOrNot("Marketing Asst Manager"));
		recruitment.deleteEnteredVacancy("Marketing Asst Manager");
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options Candidate")
    @Step("login and navigate to recruitment page, add candidate for QA Engineer")
    @Feature("Recruitment > Candidates")
    @Story("Candidate options")
	public void AddsNewCandidateForAnExistingVacancy() throws InterruptedException, AWTException {
		
		login.logMeIn();
		recruitment.clickOnRecruitmentMenu();
		recruitment.clickOnCandidateMenu();
		recruitment.clickOnAddCandidateButton();
		recruitment.enterCandidateFullName("Sam", "Ron", "Miles");
		recruitment.clickOnOpenVacanciesList();
		recruitment.enterCandidateEmailID("samRMiles@gmail.com");
		recruitment.enterCandidateContactNumber("9558754562");
		recruitment.selectCandidateResume();
		recruitment.clickOnConcentClickBox();
		recruitment.clickOnSaveCandidateButton();
		Assert.assertTrue(recruitment.SaveToastMessageText());
		
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options Candidate")
    @Step("login and navigate to recruitment page, add candidate without emailID")
    @Feature("Recruitment > Candidates")
    @Story("Candidate options")
	public void AddsNewCandidateForAnExistingVacancyWithoutEmail() throws InterruptedException, AWTException {
		
		login.logMeIn();
		recruitment.clickOnRecruitmentMenu();
		recruitment.clickOnCandidateMenu();
		recruitment.clickOnAddCandidateButton();
		recruitment.enterCandidateFullName("Sam", "Ron", "Miles");
		recruitment.clickOnOpenVacanciesList();
		recruitment.enterCandidateContactNumber("9558754562");
		recruitment.selectCandidateResume();
		recruitment.clickOnConcentClickBox();
		recruitment.clickOnSaveCandidateButton();
		Assert.assertTrue(recruitment.checkErrorMsg());
	}
	

	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("performace options adding KPI for job title")
    @Step("login and navigate to performance page, add KPI")
    @Feature("Performance > Configure > KPI")
    @Story("Performance Options")
	public void addNewKPIForTheJobTitle() throws InterruptedException {
		
		login.logMeIn();
		performance.clickOnPerformanceMenu();
		performance.clickOnPerformanceConfigure();
		performance.clickOnKPIs();
		performance.searchResultKPIAndDelete("Bug Detection Rate");
		performance.clickOnPerformanceConfigure();
		performance.clickOnKPIs();
		performance.clickOnAddKPIButton();
		performance.enterKeyPerformanceIndicator("Bug Detection Rate");
		performance.selecrJobTitleToAddKPI();
		performance.enterMinAndMaxRatings();
		performance.clickOnSaveKPIButton();
		performance.clickOnPerformanceConfigure();
		Assert.assertTrue(performance.searchResultKPI("Bug Detection Rate"));
		performance.clickOnDeleteKPIIcon();
		
	}
	
	
	@Test(groups = {"smoke"})
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify timesheet editable by employee")
    @Step("login as user and navigate to time page, edit time sheet")
    @Feature("Time Module")
    @Story("Timesheet Submission")
	public void employeeSubmittedTimeSheetWithDetails() throws InterruptedException {
		login.logMeInAsUser();
		timemenu.clickOnMenu("Time");
		timemenu.clickOnCreateTimesheetButtonOrEditTimesheetButton();
		timemenu.clickOnDeleteTimesheetEntryIcon();
		timemenu.enterProjectName("S");
		timemenu.selectActivity("c");
		timemenu.enterHoursForMondayAndTuesday();
		timemenu.clickOnSaveTimesheetButton();
		Assert.assertTrue(timemenu.SaveToastMessageText());
	}
	
	
	
	@Test(dependsOnMethods = "employeeSubmittedTimeSheetWithDetails")
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify timesheet submission and edit by employee")
    @Step("login as user and navigate to time page, add time sheet")
    @Feature("Time Module")
    @Story("Timesheet editing before submission")
	public void employeeEditingTimesheetBeforeSubmission() throws InterruptedException {
		login.logMeInAsUser();
		timemenu.clickOnMenu("Time");
		String beforeValue = timemenu.getMondayHours();
		timemenu.clickOnEditTimesheetButton();
		timemenu.enterUpdatedMondayHours();
		timemenu.clickOnSaveTimesheetButton();
		Assert.assertNotEquals(timemenu.getMondayHours(), beforeValue);
	}
	
	@Test(dependsOnMethods = "employeeEditingTimesheetBeforeSubmission")
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify timesheet approved/rejected by manager")
    @Step("login as user and navigate to time page, add time sheet and submit, login as manager and approve/reject timesheet")
    @Feature("Time Module")
    @Story("Timesheet approval/Rejection")
	public void managerApprovesOrRejectsTimesheet() throws InterruptedException {
		login.logMeInAsUser();
		timemenu.clickOnMenu("Time");
		timemenu.clickOnSubmitTimesheetButton();
		login.clickOnLogoutButton();
		login.logMeIn();
		timemenu.clickOnMenu("Time");
		timemenu.clickOnViewTimesheetButton();
		timemenu.clickOnRejectTimesheetButton();
		Assert.assertTrue(timemenu.SaveToastMessageText());
		
	}
	
	@Test()
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify dashboard punch in link")
    @Step("login and navigate to dashboard page, click on punch in link")
    @Feature("Dashboard Module")
    @Story("Dashboard Punch In Link")
	public void dashboardPunchInLinkDirectToPunchInPage() throws InterruptedException {
		login.logMeInAsUser();
		dashboard.clickOnMenu("Dashboard");
		dashboard.clickOnPunchInButton();
		Assert.assertTrue(dashboard.checkUrlChangeToAttendance("http://localhost/orangehrm/web/index.php/attendance/punchIn"));
	}
	
	@Test()
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify dashboard apply leave link")
    @Step("login and navigate to dashboard page, click on apply leave link")
    @Feature("Dashboard Module")
    @Story("Dashboard apply leaveLink")
	public void dashboardApplyLeaveLinkDirectToLeavePage() {
		login.logMeInAsUser();
		dashboard.clickOnMenu("Dashboard");
		dashboard.clickOnDashboardApplyLeaveShortcutbutton();
		Assert.assertTrue(dashboard.checkUrlChangeToAttendance("http://localhost/orangehrm/web/index.php/leave/applyLeave"));
	}
	
	@Test()
	@Severity(SeverityLevel.NORMAL)
    @Description("to verify dashboard my timesheet link")
    @Step("login and navigate to dashboard page, click on my timesheet link")
    @Feature("Dashboard Module")
    @Story("Dashboard my timesheet Link")
	
	public void dashboardMyTimesheetLinkDirectToMyTimesheetPage() {
		login.logMeInAsUser();
		dashboard.clickOnMenu("Dashboard");
		dashboard.clickOnDashboardMyTimesheetShortcutbutton();
		Assert.assertTrue(
		dashboard.checkUrlChangeToAttendance("http://localhost/orangehrm/web/index.php/time/viewMyTimesheet"));
	}
	
	
	
	
	
	
	
}

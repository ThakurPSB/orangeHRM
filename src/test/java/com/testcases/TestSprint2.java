package com.testcases;


//import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.testng.log4testng.Logger;

import com.base.TestBase;
import com.pages.AdminJobTitles;
import com.pages.AdminMenu;
import com.pages.AdminOrganizationMenu;
import com.pages.AdminQualificationMenu;
import com.pages.LeaveApplyMenu;
import com.pages.LeaveEntitlementMenu;
import com.pages.LeaveMyLeaveMenu;
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
	private AdminQualificationMenu qualification;
	private LeaveApplyMenu leaveApply;
	private LeaveMyLeaveMenu myLeave;
	private LeaveEntitlementMenu entitlement;

	@BeforeMethod
	public void pageSetup() {
        login = new LoginPage(kw); 
        admin = new AdminMenu(kw);
        jobs = new AdminJobTitles(kw);
        orgMenu = new AdminOrganizationMenu(kw);
        qualification = new AdminQualificationMenu(kw);
        leaveApply= new LeaveApplyMenu(kw);
        myLeave = new LeaveMyLeaveMenu(kw);
        entitlement = new LeaveEntitlementMenu(kw);
    }
	
	@AfterMethod
	public void tearDowntest() throws InterruptedException {
		login = null;
		admin = null;
		jobs = null;
		orgMenu = null;
		qualification = null;
		leaveApply= null;
		myLeave = null;
		entitlement = null;
		
		Thread.sleep(3000);
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
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to Add skills")
    @Step("login and navigate to admin page, check organization option skills menus")
    @Feature("admin Features Organization menu skills")
    @Story("Admin page options oragnization submenu checking skills")
	public void CheckIfAddSkillOptionIsWorkingOrNot() throws InterruptedException {
		login.logMeIn();
		admin.clickOnAdminMenu();
		qualification.clickOnQualificationMenu();
		qualification.clickOnSkills();
		qualification.clickOnAddSkillsButton();
		qualification.enterSkillName("java");
		qualification.clickOnSaveSkillButton();
		Assert.assertTrue(orgMenu.SaveToastMessageText());
		qualification.deleteEnteredSkill("java");
	}
	
	@Test(groups = {"smoke"})
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves")
    @Step("login and navigate to leave page, check apply leave menu ")
    @Feature("Leave Module")
    @Story("Apply leave successfully with valid inputs")
	public void ApplyLeaveWithValidInputs() throws InterruptedException {
	
		login.logMeInAsUser();
		login.clickOnMenu("Leave");
		leaveApply.clickOnApplyLeave();
		leaveApply.selectLeaveType("e");
		leaveApply.selectFromDate();
		leaveApply.selectToDate();
		leaveApply.clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply.leaveAppliedSuccessfullyOrOverlapingLeave());
		
	}
	
	@Test(dependsOnMethods = "ApplyLeaveWithValidInputs", groups = {"smoke"})
	@Severity(SeverityLevel.NORMAL)
    @Description("To verify when leave is applied can admin or manager able to approve leave")
    @Step("login and navigate to leave page, check approve leave menu ")
    @Feature("Leave Module")
    @Story("Approve leave successfully")
	public void approveLeaveApplication() throws InterruptedException {
	
		
		login.logMeIn(); //logged in as admin
		leaveApply.clickOnLeaveMenu();
		leaveApply.clickOnApproveLeaveButton();
		Assert.assertTrue(leaveApply.SaveToastMessageText());
		leaveApply.clickOnLeaveMenu();
		leaveApply.selectLeaveStatus("t");
		leaveApply.clickOnSearchLeaveButton();
		leaveApply.clickOnMoreOptionsButton();
		leaveApply.clickOnCancelLeaveOption();
		
	}
	
	@Test(groups = {"smoke"})
	@Severity(SeverityLevel.NORMAL)
    @Description("To verify when leave is applied can admin or manager able to reject leave")
    @Step("login and navigate to leave page, check Reject leave  ")
    @Feature("Leave Module")
    @Story("Reject leave successfully")
	public void userCancelLeaveApplication() throws InterruptedException {
	
		login.logMeInAsUser();
		login.clickOnMenu("Leave");
		leaveApply.clickOnApplyLeave();
		leaveApply.selectLeaveType("e");
		leaveApply.selectFromDate();
		leaveApply.selectToDate();
		leaveApply.clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply.SaveToastMessageText());
		login.clickOnMenu("Leave");
		leaveApply.clickOnUserCancelLeaveButton();
		Assert.assertTrue(leaveApply.SaveToastMessageText());
		
		
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves with invalid dates")
    @Step("login and navigate to leave page, check apply leave menu > enter dates > apply ")
    @Feature("Leave Module")
    @Story("Apply leave with overlapping dates")
	public void ApplyLeaveWithOverlappingDates() throws InterruptedException {
		
		login.logMeIn();
		leaveApply.clickOnLeaveMenu();
		leaveApply.clickOnApplyLeave();
		leaveApply.selectLeaveType("e");
		leaveApply.selectFromDate();
		leaveApply.selectToDate();
		leaveApply.clickOnApplyLeaveButton();
		leaveApply.clickOnApplyLeave();
		leaveApply.selectLeaveType("e");
		leaveApply.selectFromDate();
		leaveApply.selectToDate();
		leaveApply.clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply.failedToApplyLeaveToastText());
		myLeave.clickOnLeaveMenu();
		myLeave.clickOnMyLeaveMenu();
		myLeave.cancelAllLeaves();
	}
	
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves without selecting leave type")
    @Step("login and navigate to leave page, check apply leave menu > enter dates > apply ")
    @Feature("Leave Module")
    @Story("Apply leave without leave type")
	public void ApplyLeaveWithoutSelectingLeaveType() {
		
		login.logMeIn();
		leaveApply.clickOnLeaveMenu();
		leaveApply.clickOnApplyLeave();
		leaveApply.selectFromDate();
		leaveApply.selectToDate();
		leaveApply.clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply.checkWarningForLeaveType());
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves with past dates")
    @Step("login and navigate to leave page, check apply leave menu > enter dates > apply ")
    @Feature("Leave Module")
    @Story("Apply leave with past dates")
	public void ApplyLeaveWithPastDates() throws InterruptedException {
		
		login.logMeIn();
		leaveApply.clickOnLeaveMenu();
		leaveApply.clickOnApplyLeave();
		leaveApply.selectLeaveType("e");
		leaveApply.selectPastFromDate();
		leaveApply.selectPastToDate();
		leaveApply.clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply.SaveToastMessageText());
		myLeave.clickOnLeaveMenu();
		myLeave.clickOnMyLeaveMenu();
		myLeave.cancelAllLeaves();
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Checking if able to apply leaves without sufficient balance")
    @Step("login and navigate to leave page, check apply leave with floating leave ")
    @Feature("Leave Module")
    @Story("Apply leave with insufficient balance and check for error")
	public void ApplyLeaveWithInsufficientBalance() throws InterruptedException {
	
		
		login.logMeIn();
		leaveApply.clickOnLeaveMenu();
		leaveApply.clickOnApplyLeave();
		leaveApply.selectLeaveType("f"); // f for floating leave
		leaveApply.selectFromDate();
		leaveApply.selectToDate();
		leaveApply.clickOnApplyLeaveButton();
		Assert.assertTrue(leaveApply.errorLeaveBalance());
		
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Add leave entitlement to an employee")
    @Step("login and navigate to leaveentitlement,add leaves ")
    @Feature("Leave > Entitlements")
    @Story("Add Leave entitlement to single employee")
	public void addLeaveEntitlement() throws InterruptedException  {
		login.logMeIn();
		leaveApply.clickOnLeaveMenu();
		entitlement.clickOnLeaveEntitlementMenu();
		entitlement.clickEmployeeEntitlement();
		entitlement.enterEmployeeNameInSearchBoxEmplpyeeEntitlement("Adriana Jones");
		entitlement.selectLeaveTypeInEntitlement("Earned");
		entitlement.clickOnSearchButton();
		double leaveBalance = entitlement.CheckLeaveBalance();
		leaveApply.clickOnLeaveMenu();
		entitlement.clickOnLeaveEntitlementMenu();
		entitlement.clickOnAddEntitlement();
		entitlement.selectIndividualOrMultipleEmployee("individual");
		entitlement.enterEmployeeName("Adriana Jones");
		entitlement.selectLeaveType("Earned");
		entitlement.enterEntitlement("1");
		entitlement.clickOnSaveButton();
		entitlement.clickOnConfirmButton();
		Assert.assertTrue(entitlement.SaveToastMessageText());
		entitlement.clickOnLeaveEntitlementMenu();
		entitlement.clickEmployeeEntitlement();
		entitlement.enterEmployeeNameInSearchBoxEmplpyeeEntitlement("Adriana Jones");
		entitlement.selectLeaveTypeInEntitlement("Earned");
		entitlement.clickOnSearchButton();
		Assert.assertTrue(entitlement.CheckLeaveBalance()>leaveBalance);
	}


	
}

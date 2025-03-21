package com.testcases;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.PimMenu;
import com.pages.ReportingMethod;
import com.pages.TerminationReasons;
import com.pages.AddEmployeePage;
import com.pages.EmployeeListMenu;
import com.pages.LoginPage;
import com.pages.PimCustomFields;
import com.pages.UserDropdownMenu;
import com.pages.UserProfile;

import io.qameta.allure.*;

public class TestSprint1 extends TestBase{
	private static final Logger LOG = Logger.getLogger(TestSprint1.class);
	
	@Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify valid login with correct username and password")
    @Step("Login with username: {0} and password: {1}")
    @Feature("Login Feature")
    @Story("Valid login credentials should grant access")
	public void CheckIfLoginSuccessfullWithCorrectCredentials() throws InterruptedException {
		LoginPage login = new LoginPage();
		
		HashMap <String ,String> credentials = new HashMap<>();
		
		credentials.put("admin", "Piyush@2050");
		credentials.put("admin", "admin45");
		credentials.put("user1", "admin123");
		
		for(Map.Entry<String,String> entry: credentials.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("admin") && entry.getValue().equalsIgnoreCase("Piyush@2050")){
				login.enterCredentials(entry.getKey(), entry.getValue());
				login.ClickOnLoginButton();
				Assert.assertEquals(login.dashboardText(), "Dashboard");
				login.clickOnLogoutButton();
				LOG.info("Test Passed - Login succesfull with correct credentials");
			}
			else {
				login.enterCredentials(entry.getKey(), entry.getValue());
				login.ClickOnLoginButton();
				Assert.assertEquals(login.errorText(), "Invalid credentials");
				LOG.info("Test Passed - Login unsuccesfull with incorrect credentials");
			}
		}
		Thread.sleep(3000);
	}
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify if all the links in the profile menu working or not")
    @Step("Login , click on userprofile menu and check each option one by one")
    @Feature("User Dropdown")
    @Story("Respected links / dialog box should be visible")
	public void checkIfAllTheLinksInProfileMenuAreWorking() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		
		UserDropdownMenu profile = new UserDropdownMenu();
		
		profile.clickOnUserProfile();
		profile.clickOnAbout();
		Assert.assertTrue(profile.aboutInformation());
		profile.closeAboutInformatio();
		profile.clickOnUserProfile();
		profile.clickOnSupport();
		Assert.assertTrue(profile.supportInformation());
		profile.clickOnUserProfile();
		profile.clickOnChangePassword();
		Assert.assertTrue(profile.checkIfChangePasswordPage());
		Thread.sleep(3000);
	}
	
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu > Configuration > Optional field page is working properly.")
    @Step("Login, navigate to PIM > configuration > optional fields > Turn on all the filds > click on Save")
    @Feature("Configuration/Optional Fields")
    @Story("Create a page object for PIM menu")
	public void checkIfPIMmenuConfigurationOptionalFieldCanBeTurnedOnOff() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		PimMenu pim = new PimMenu();
		
		login.logMeIn();
		
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		pim.clickOnOptionalFields();
		pim.turnONshowDeprecatedField();
		pim.turnONSSNfield();
		pim.turnONSINfield();
		pim.turnONUStaxExemptionMenufield();
		pim.clickOnSaveButtonOptionalFidls();
		Assert.assertTrue(pim.SaveToastMessageText());
		
		Thread.sleep(3000);
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu > Configuration > Optional field toggle reflecting in user profile.")
    @Step("Login, navigate to PIM > configuration > optional fields > Turn on all the filds > click on Save > go to user profile > check if fields are visible")
    @Feature("OptionalFieldToggle")
    @Story("Create a page object for user profile")
	public void CheckIfOptionalFieldsVisibilityToggleWorking() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		
		UserProfile user = new UserProfile();
		PimMenu pim = new PimMenu();
		
		//When options are turned off
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		pim.clickOnOptionalFields();
		pim.turnOFFshowDeprecatedField();
		pim.turnOFFSINfield();
		pim.turnOFFSSNfield();
		pim.turnOFFUStaxExemptionMenufield();
		pim.clickOnSaveButtonOptionalFidls();
		pim.clickOnPIM();
		user.clickOnEmployeeList();
		user.clickOnUser1();
		Assert.assertTrue(user.checknicknameVisibility());
		Assert.assertTrue(user.checkSSNvisibility());
		Assert.assertTrue(user.checkSINvisibility());
		Assert.assertTrue(user.checkTaxExemptionvisibility());
		
		//when options are turned on
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		pim.clickOnOptionalFields();
		pim.turnONshowDeprecatedField();
		pim.turnONSINfield();
		pim.turnONSSNfield();
		pim.turnONUStaxExemptionMenufield();
		pim.clickOnSaveButtonOptionalFidls();
		pim.clickOnPIM();
		user.clickOnEmployeeList();
		user.clickOnUser1();
		Assert.assertTrue(user.checkNicknameDisplayed());
		Assert.assertTrue(user.checkSINdisplayed());
		Assert.assertTrue(user.checkSSNdisplayed());
		Assert.assertTrue(user.checkTaxExemptionDisplayed());
		
		Thread.sleep(3000);
	}

	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu > Configuration > custom field add option working.")
    @Step("Login, navigate to PIM > configuration > custom fields > add the custom fields and check if visible on user profile page")
    @Feature("Add Custom Field")
    @Story("CustoField in PIM Menu")
	public void AddCustomFieldsWithBothTypeOfInputMethodAndCheckIfReflectingOnRespectedPage() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		
		PimCustomFields customField = new PimCustomFields();
		PimMenu pim = new PimMenu();
		UserProfile user = new UserProfile();
		
		//deleting already created custom fields
		
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		customField.clickOnCustomFields();
		customField.SelectFieldToDelete("InsuranceID");
		customField.SelectFieldToDelete("OfficeTransport");
		
		//Creating custom fields
	
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		customField.clickOnCustomFields();
		
		customField.clickOnAddCustomFieldsButton();
		customField.enterFieldName("InsuranceID");
		customField.clickOnScreenOption();
		customField.selectTypeOfInputText();
		customField.clickOnSaveCustomFieldButton();
		Assert.assertTrue(customField.SaveToastMessageText());
		
		customField.clickOnAddCustomFieldsButton();
		customField.enterFieldName("OfficeTransport");
		customField.clickOnScreenOption();
		customField.SelectTypeOfInputDropdown();
		customField.enterOptions("YES,NO");
		customField.clickOnSaveCustomFieldButton();
		Assert.assertTrue(customField.SaveToastMessageText());
		
		// checking custom fields are displayed
		
		pim.clickOnPIM();
		user.clickOnEmployeeList();
		user.clickOnUser1();
		user.ClickOnMemebership();
		Assert.assertTrue(user.checkInsuranceIDDisplayed());
		Assert.assertTrue(user.checkOfficeTransportDisplayed());
		
		Thread.sleep(3000);
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu > Configuration > Reporting method and termination options working.")
    @Step("Login, navigate to PIM > configuration > custom fields > add the reporting method and termaintion reason and then delete")
    @Feature("Add Termination reason and reporting method")
    @Story("PIM Configuration Menus")
	public void AddReportingMethodAndTerminationReason() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		
		PimMenu pim = new PimMenu();
		ReportingMethod rm = new ReportingMethod();	
		TerminationReasons tr = new TerminationReasons();
		
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		rm.clickOnReportingMethod();
		rm.deleteSelectedReportingMethod("Online");
		rm.AddReportingMethodButton();
		rm.addrReportingMethod("Online");
		Assert.assertTrue(rm.checkAssertReportingMethod());
		
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		tr.clickOnTerminationReasonsMenu();
		tr.deleteSelectedTerminationReason("Other");
		tr.ClickOnAddTerminationReasonButton();
		tr.AddTerminationReason("Other");
		Assert.assertTrue(tr.checkAssertTerminationReason());
		Thread.sleep(3000);
		
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu > employee list > employee search.")
    @Step("Login, navigate to PIM > employee list > select search criteri > check if employee matching are displayed")
    @Feature("employee list search feature")
    @Story("PIM employee list Menus")
	public void checkSearchOptionsInPIMMenuEmployeeListPage() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		EmployeeListMenu emplist = new EmployeeListMenu();
		PimMenu pim = new PimMenu();
		pim.clickOnPIM();
		emplist.clickOnEmployeeListMenu();

		emplist.enterEmployeeID(68166);
		emplist.clickOnSearchButton();
		Assert.assertTrue(emplist.searchResultID("68166"));
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.enterEmployeeName("Piyush");
		emplist.clickOnSearchButton();
		Assert.assertTrue(emplist.searchResultfirstName("Piyush"));
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.enterEmployeeName("Thakur");
		emplist.clickOnSearchButton();
		Assert.assertTrue(emplist.searchResultLastName("Thakur"));
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.selectEmploymentStatus("Active");
		emplist.clickOnSearchButton();
		Assert.assertTrue(emplist.searchResultEmploymentStatus("Active"));
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.enterSupervisorName("Piyush");
		emplist.clickOnSearchButton();
		Assert.assertTrue(emplist.searchResultSupervisor("Piyush Thakur"));
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.selectJobTitle("Executive");
		emplist.clickOnSearchButton();
		Assert.assertTrue(emplist.searchResultJobTitle("Executive"));
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.selectSubUnit("Mumbai");
		emplist.clickOnSearchButton();
		Assert.assertTrue(emplist.searchResultSubUnit("Mumbai"));
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		Thread.sleep(3000);
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu >  Add Employee > adding user.")
    @Step("Login, navigate to PIM > Add employee > enter the details and add employee")
    @Feature("Add Employee")
    @Story("Add Employee")
    public void addUserandCheckIfAllOptionsAreWorkingWhileCreating() throws InterruptedException, AWTException, TimeoutException {
		LoginPage login = new LoginPage();
		login.logMeIn();
		PimMenu pim = new PimMenu();
		EmployeeListMenu emplist = new EmployeeListMenu();
		AddEmployeePage addemp = new AddEmployeePage();
		pim.clickOnPIM();
		emplist.clickOnEmployeeListMenu();
		emplist.enterEmployeeName("Wisley");
		emplist.clickOnSearchButton();
		emplist.DeleteSelectedUsers();
		pim.clickOnPIM();
		addemp.clickOnAddNewEmployeeButton();
		addemp.selectProfilePic();
		addemp.EnterUserDetails("George", "L", "Wisley");
		addemp.enterEmployeeID();
		addemp.clickOnCreateLoginDetails();
		addemp.enterLoginDetails("George", "User@2025", false);
		addemp.clickOnSaveButton();
		Assert.assertTrue(addemp.SaveToastMessageText());
		
	}

	@Test
	public void TryAndError() throws InterruptedException, AWTException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		EmployeeListMenu emplist = new EmployeeListMenu();
		PimMenu pim = new PimMenu();
		//AddEmployeePage addemp = new AddEmployeePage();
		pim.clickOnPIM();
		
		emplist.enterSupervisorName("piy");
		emplist.clickOnSearchButton();
		Assert.assertTrue(emplist.searchResultSupervisor("Piyush Thakur"));
		//emplist.clickOnResetButton();
		//emplist.clickOnEmployeeListMenu();
		
		Thread.sleep(5000);
		
	}

}

package com.testcases;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.PimMenu;
import com.pages.ReportingMethod;
import com.pages.TerminationReasons;
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
	public void CheckIfLoginSuccessfullWithCorrectCredentials() {
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
	}
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify if all the links in the profile menu working or not")
    @Step("Login , click on userprofile menu and check each option one by one")
    @Feature("User Dropdown")
    @Story("Respected links / dialog box should be visible")
	public void checkIfAllTheLinksInProfileMenuAreWorking() {
		
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
	}
	
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu > Configuration > Optional field page is working properly.")
    @Step("Login, navigate to PIM > configuration > optional fields > Turn on all the filds > click on Save")
    @Feature("Configuration/Optional Fields")
    @Story("Create a page object for PIM menu")
	public void checkIfPIMmenuConfigurationOptionalFieldCanBeTurnedOnOff() {
		
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
		Assert.assertTrue(rm.SaveToastMessageText());
		
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		tr.clickOnTerminationReasonsMenu();
		tr.deleteSelectedTerminationReason("Other");
		tr.ClickOnAddTerminationReasonButton();
		tr.AddTerminationReason("Other");
		Assert.assertTrue(tr.SaveToastMessageText());
		
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

		emplist.enterEmployeeID(97016);
		emplist.clickOnSearchButton();
		Assert.assertEquals(emplist.searchResultID(), "97016");
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.enterEmployeeName("Piyush");
		emplist.clickOnSearchButton();
		Assert.assertEquals(emplist.searchResultfirstName(),"Piyush");
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.enterEmployeeName("Piyush");
		emplist.clickOnSearchButton();
		Assert.assertEquals(emplist.searchResultLastName(),"Thakur");
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.selectEmploymentStatus("Active");
		emplist.clickOnSearchButton();
		Assert.assertEquals(emplist.searchResultEmploymentStatus(), "Active");
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.enterSupervisorName("Piyush");
		emplist.clickOnSearchButton();
		Assert.assertEquals(emplist.searchResultSupervisor(), "Piyush Thakur");
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.selectJobTitle(2);
		emplist.clickOnSearchButton();
		Assert.assertEquals(emplist.searchResultJobTitle(), "Executive");
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
		emplist.selectSubUnit(2);
		emplist.clickOnSearchButton();
		Assert.assertEquals(emplist.searchResultSubUnit(), "Mumbai");
		emplist.clickOnResetButton();
		emplist.clickOnEmployeeListMenu();
		
	}
	
	@Test(enabled = false)
	public void tryAndError() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		EmployeeListMenu emplist = new EmployeeListMenu();
		PimMenu pim = new PimMenu();
		pim.clickOnPIM();
		//emplist.readTable();
		System.out.println(emplist.searchResultEmploymentStatus());
		System.out.println(emplist.searchResultID());
		System.out.println(emplist.searchResultJobTitle());
		System.out.println(emplist.searchResultLastName());
		System.out.println(emplist.searchResultSubUnit());
		System.out.println(emplist.searchResultSupervisor());
	
		
		//Thread.sleep(3000);
	}
	


}

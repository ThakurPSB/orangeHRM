package com.testcases;

import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
	
	public LoginPage login;
	public UserDropdownMenu profile;
	public PimMenu pim;
	public UserProfile user;
	public PimCustomFields customField;
	public ReportingMethod rm;
	public TerminationReasons tr;
	public EmployeeListMenu emplist ;
	public AddEmployeePage addemp;
	
	@BeforeMethod
	public void pageSetup() {
        login = new LoginPage(key); 
        profile = new UserDropdownMenu(key);
        pim = new PimMenu(key);
        user = new UserProfile(key);
        customField = new PimCustomFields(key);
        rm = new ReportingMethod(key);	
		tr = new TerminationReasons(key);
		emplist = new EmployeeListMenu(key);
		addemp = new AddEmployeePage(key);
        
    }
	
	@AfterMethod
	public void tearDowntest() throws InterruptedException {
		login = null;
		profile = null;
		pim = null;
		user = null;
		customField = null;
		rm = null;
		tr = null;
		emplist = null;
		addemp = null;
		Thread.sleep(3000);
	}
	
	
	
	private static final Logger LOG = Logger.getLogger(TestSprint1.class);
	
	@Test(groups= {"smoke"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify valid login with correct username and password")
    @Step("Login with username: {0} and password: {1}")
    @Feature("Login Page")
    @Story("Valid login credentials should grant access")
	public void CheckIfLoginSuccessfullWithCorrectCredentials() throws InterruptedException {
					
				login.enterCredentials("admin", "Piyush@2050");
				login.ClickOnLoginButton();
				Assert.assertEquals(login.dashboardText(), "Dashboard");
				login.clickOnLogoutButton();
				LOG.info("Test Passed - Login succesfull with correct credentials");
			
		
	}
	
	@Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify invalid login with incorrect username and password")
    @Step("Login with username: {0} and password: {1}")
    @Feature("Login Page")
    @Story("InValid login credentials should show error message")
	public void CheckIfLoginSuccessfullWithIncorrectCredentials() throws InterruptedException {
		
		HashMap <String ,String> credentials = new HashMap<>();
		
		credentials.put("admin", "admin45");
		credentials.put("user1", "admin123");
		
		for(Map.Entry<String,String> entry: credentials.entrySet()) {
			
			login.enterCredentials(entry.getKey(), entry.getValue());
			login.ClickOnLoginButton();
			Assert.assertEquals(login.errorText(), "Invalid credentials");
			LOG.info("Test Passed - Login unsuccesfull with incorrect credentials");
		
		}
	}
	
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("tO Verify when username and password left blank and login button clicked should display required field error message")
    @Step("Login with username: {0} and password: {1}")
    @Feature("Login Page")
    @Story("blank login credentials should show required error message")
	public void CheckIfLoginSuccessfullWithCredentialsFieldBlank() throws InterruptedException {
		
			login.enterCredentials("","");
			login.ClickOnLoginButton();
			
			Assert.assertEquals(login.usernameRequiredError(), "Required");
			LOG.info("Test Passed - Login unsuccesfull with blank credentials required field error displayed");
		
	}
	
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("To Verify if all the links in the profile menu working or not")
    @Step("Login , click on userprofile menu and check each option one by one")
    @Feature("Profile Menu Links")
    @Story("Respected links / dialog box should be visible")
	public void checkIfAllTheLinksInProfileMenuAreWorking() throws InterruptedException {
		
		login.logMeIn();
		
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
    @Description("To Check if PIM Menu > Configuration > Optional field page is options saving correctly.")
    @Step("Login, navigate to PIM > configuration > optional fields > Turn on all the filds > click on Save")
    @Feature("PIM Menu")
    @Story("Create a page object for PIM menu")
	public void checkIfPIMmenuConfigurationOptionalFieldCanBeTurnedOnOff() throws InterruptedException {
		
		
		
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
    @Description("To verify after login into application and selecting PIM menu optional field toggle is available.")
    @Step("Login, navigate to PIM > configuration > optional fields > Turn on all the filds > click on Save > go to user profile > check if fields are visible")
    @Feature("PIM Menu")
    @Story("Create a page object for user profile")
	public void CheckIfOptionalFieldsVisibilityToggleWorking() throws InterruptedException {
		
		login.logMeIn();
		
		
		
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
    @Feature("PIM Menu")
    @Story("CustoField in PIM Menu")
	public void AddCustomFieldsWithBothTypeOfInputMethodAndCheckIfReflectingOnRespectedPage() throws InterruptedException {
		
		login.logMeIn();
		
		
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
    @Description("Check if PIM Menu > employee list > employee search.")
    @Step("Login, navigate to PIM > employee list > select search criteri > check if employee matching are displayed")
    @Feature("employee list search feature")
    @Story("PIM employee list Menus")
	public void checkSearchOptionsInPIMMenuEmployeeListPage() throws InterruptedException {
		
		login.logMeIn();
		
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
		login.logMeIn();
		
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



}

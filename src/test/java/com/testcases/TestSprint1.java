package com.testcases;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.PimMenu;
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
		
		//custom field with text input
	
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		customField.clickOnCustomFields();
		customField.clickOnAddCustomFieldsButton();
		customField.enterFieldName("InsuranceID");
		customField.clickOnScreenOption();
		customField.selectTypeOfInputText();
		customField.clickOnSaveCustomFieldButton();
		Assert.assertTrue(customField.SaveToastMessageText());
		pim.clickOnPIM();
		user.clickOnEmployeeList();
		user.clickOnUser1();
		user.ClickOnMemebership();
		Assert.assertTrue(user.checkInsuranceIDDisplayed());
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		customField.clickOnCustomFields();
		customField.SelectFieldToDelete("InsuranceID");
		
		//custom field with drop down input
		
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		customField.clickOnCustomFields();
		customField.clickOnAddCustomFieldsButton();
		customField.enterFieldName("OfficeTransport");
		customField.clickOnScreenOption();
		customField.SelectTypeOfInputDropdown();
		customField.enterOptions("YES,NO");
		customField.clickOnSaveCustomFieldButton();
		Assert.assertTrue(customField.SaveToastMessageText());
		pim.clickOnPIM();
		user.clickOnEmployeeList();
		user.clickOnUser1();
		user.ClickOnMemebership();
		Assert.assertTrue(user.checkOfficeTransportDisplayed());
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		customField.clickOnCustomFields();
		customField.SelectFieldToDelete("OfficeTransport");
		
	}
	
	@Test
	public void tryAndError() throws InterruptedException {
		
		LoginPage login = new LoginPage();
		login.logMeIn();
		
		PimCustomFields customField = new PimCustomFields();
		PimMenu pim = new PimMenu();
		//UserProfile user = new UserProfile();
		
		//custom field with text input
		pim.clickOnPIM();
		pim.clickOnConfiguration();
		customField.clickOnCustomFields();
		customField.SelectFieldToDelete("InsuranceID");
	}
	


}

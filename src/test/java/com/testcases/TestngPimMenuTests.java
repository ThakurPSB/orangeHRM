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

public class TestngPimMenuTests extends TestBase {
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("To Check if PIM Menu > Configuration > Optional field page is options saving correctly.")
    @Step("Login, navigate to PIM > configuration > optional fields > Turn on all the filds > click on Save")
    @Feature("PIM Menu")
    @Story("Create a page object for PIM menu")
	public void checkIfPIMmenuConfigurationOptionalFieldCanBeTurnedOnOff() throws InterruptedException {
		
		
		
		login().logMeIn();
		
		pim().clickOnPIM();
		pim().clickOnConfiguration();
		pim().clickOnOptionalFields();
		pim().turnONshowDeprecatedField();
		pim().turnONSSNfield();
		pim().turnONSINfield();
		pim().turnONUStaxExemptionMenufield();
		pim().clickOnSaveButtonOptionalFidls();
		Assert.assertTrue(pim().SaveToastMessageText());
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("To verify after login into application and selecting PIM menu optional field toggle is available.")
    @Step("Login, navigate to PIM > configuration > optional fields > Turn on all the filds > click on Save > go to user profile > check if fields are visible")
    @Feature("PIM Menu")
    @Story("Create a page object for user profile")
	public void CheckIfOptionalFieldsVisibilityToggleWorking() throws InterruptedException {
		
		login().logMeIn();
		
		
		
		//When options are turned off
		pim().clickOnPIM();
		pim().clickOnConfiguration();
		pim().clickOnOptionalFields();
		pim().turnOFFshowDeprecatedField();
		pim().turnOFFSINfield();
		pim().turnOFFSSNfield();
		pim().turnOFFUStaxExemptionMenufield();
		pim().clickOnSaveButtonOptionalFidls();
		pim().clickOnPIM();
		user().clickOnEmployeeList();
		user().clickOnUser1();
		Assert.assertTrue(user().checknicknameVisibility());
		Assert.assertTrue(user().checkSSNvisibility());
		Assert.assertTrue(user().checkSINvisibility());
		Assert.assertTrue(user().checkTaxExemptionvisibility());
		
		//when options are turned on
		pim().clickOnPIM();
		pim().clickOnConfiguration();
		pim().clickOnOptionalFields();
		pim().turnONshowDeprecatedField();
		pim().turnONSINfield();
		pim().turnONSSNfield();
		pim().turnONUStaxExemptionMenufield();
		pim().clickOnSaveButtonOptionalFidls();
		pim().clickOnPIM();
		user().clickOnEmployeeList();
		user().clickOnUser1();
		Assert.assertTrue(user().checkNicknameDisplayed());
		Assert.assertTrue(user().checkSINdisplayed());
		Assert.assertTrue(user().checkSSNdisplayed());
		Assert.assertTrue(user().checkTaxExemptionDisplayed());
		
	}

	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("Check if PIM Menu > Configuration > custom field add option working.")
    @Step("Login, navigate to PIM > configuration > custom fields > add the custom fields and check if visible on user profile page")
    @Feature("PIM Menu")
    @Story("CustoField in PIM Menu")
	public void AddCustomFieldsWithBothTypeOfInputMethodAndCheckIfReflectingOnRespectedPage() throws InterruptedException {
		
		login().logMeIn();
		//deleting already created custom fields
		
		pim().clickOnPIM();
		pim().clickOnConfiguration();
		customField().clickOnCustomFields();
		customField().SelectFieldToDelete("InsuranceID");
		customField().SelectFieldToDelete("OfficeTransport");
		
		//Creating custom fields
		pim().clickOnPIM();
		pim().clickOnConfiguration();
		customField().clickOnCustomFields();
		
		customField().clickOnAddCustomFieldsButton();
		customField().enterFieldName("InsuranceID");
		customField().clickOnScreenOption();
		customField().selectTypeOfInputText();
		customField().clickOnSaveCustomFieldButton();
		Assert.assertTrue(customField().SaveToastMessageText());
		
		customField().clickOnAddCustomFieldsButton();
		customField().enterFieldName("OfficeTransport");
		customField().clickOnScreenOption();
		customField().SelectTypeOfInputDropdown();
		customField().enterOptions("YES,NO");
		customField().clickOnSaveCustomFieldButton();
		Assert.assertTrue(customField().SaveToastMessageText());
		
		// checking custom fields are displayed
		
		pim().clickOnPIM();
		user().clickOnEmployeeList();
		user().clickOnUser1();
		user().ClickOnMemebership();
		Assert.assertTrue(user().checkInsuranceIDDisplayed());
		Assert.assertTrue(user().checkOfficeTransportDisplayed());
		
	}
	

}

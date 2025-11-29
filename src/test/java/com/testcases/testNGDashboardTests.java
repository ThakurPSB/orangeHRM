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

public class testNGDashboardTests extends TestBase {
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("To Verify if all the links in the profile menu working or not")
    @Step("Login , click on userprofile menu and check each option one by one")
    @Feature("Profile Menu Links")
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
	

}

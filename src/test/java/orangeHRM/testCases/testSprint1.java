package orangeHRM.testCases;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.*;
import orangeHRM.base.testBase;
import orangeHRM.pages.loginPage;
import orangeHRM.pages.userDropdownMenu;

public class testSprint1 extends testBase{
	private static final Logger LOG = Logger.getLogger(testSprint1.class);
	
	@Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify valid login with correct username and password")
    @Step("Login with username: {0} and password: {1}")
    @Feature("Login Feature")
    @Story("Valid login credentials should grant access")
	public void CheckIfLoginSuccessfullWithCorrectCredentials() {
		loginPage login = new loginPage();
		
		HashMap <String ,String> credentials = new HashMap<>();
		
		credentials.put("admin", "admin123");
		credentials.put("admin", "admin45");
		credentials.put("user1", "admin123");
		
		for(Map.Entry<String,String> entry: credentials.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("admin") && entry.getValue().equalsIgnoreCase("admin123")){
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
		
		loginPage login = new loginPage();
		login.logMeIn();
		
		userDropdownMenu profile = new userDropdownMenu();
		
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

	

}

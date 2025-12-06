package com.testcases;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestngLoginPageTests extends TestBase {
	
	private static final Logger LOG = Logger.getLogger(TestngLoginPageTests.class);
	
	@Test(groups = {"regression", "smoke", "login"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify valid login with correct username and password")
    @Step("Login with username: {0} and password: {1}")
    @Feature("Login Page")
    @Story("Valid login credentials should grant access")
	public void shouldLoginSuccessfullyWithValidCredentials() throws InterruptedException {
					
				login().enterCredentials("admin", "Piyush@2050");
				login().ClickOnLoginButton();
				Assert.assertEquals(login().dashboardText(), "Dashboard");
				login().clickOnLogoutButton();
				LOG.info("Test Passed - Login succesfull with correct credentials");
			
		
	}
	
	@Test(groups = {"regression", "login"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify invalid login with incorrect username and password")
    @Step("Login with username: {0} and password: {1}")
    @Feature("Login Page")
    @Story("InValid login credentials should show error message")
	public void shouldShowErrorMessageWhenCredentialsAreInvalid() throws InterruptedException {
		
		HashMap <String ,String> credentials = new HashMap<>();
		
		credentials.put("admin", "admin45");
		credentials.put("user1", "admin123");
		
		for(Map.Entry<String,String> entry: credentials.entrySet()) {
			
			login().enterCredentials(entry.getKey(), entry.getValue());
			login().ClickOnLoginButton();
			Assert.assertEquals(login().errorText(), "Invalid credentials");
			LOG.info("Test Passed - Login unsuccesfull with incorrect credentials");
		
		}
	}
	
	
	@Test(groups = {"regression", "login"})
    @Severity(SeverityLevel.NORMAL)
    @Description("tO Verify when username and password left blank and login button clicked should display required field error message")
    @Step("Login with username: {0} and password: {1}")
    @Feature("Login Page")
    @Story("blank login credentials should show required error message")
	public void shouldShowErrorMessageWhenCredentialsAreBlank() throws InterruptedException {
		
			login().enterCredentials("","");
			login().ClickOnLoginButton();
			
			Assert.assertEquals(login().usernameRequiredError(), "Required");
			LOG.info("Test Passed - Login unsuccesfull with blank credentials required field error displayed");
		
	}

}

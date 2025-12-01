package com.stepdefinition;

import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefLoginPage extends StepBase{
	
	@When("user enters username {string} and password {string}")
	public void enterUsernameAndPassword(String user, String pass) {
	    login().enterCredentials(user, pass);
	}

	@When("user clicks on login button")
	public void clickOnLoginButton() {
	    login().ClickOnLoginButton();
	}

	@Then("dashboard should be displayed")
	public void verifyDashboardIsDisplayed() {
	    String text = login().dashboardText();
	    Assert.assertEquals(text, "Dashboard", "Dashboard text mismatch");
	}

	@Then("invalid credential error should be displayed")
	public void verifyInvalidCredentialError() {
	    String error = login().errorText();
	    Assert.assertEquals(error, "Invalid credentials", "Error text mismatch");
	}

	@When("user logs out")
	public void logOutUser() {
	    login().clickOnLogoutButton();
	}

	@When("admin logs in with default credentials")
	public void loginAsAdmin() {
	    login().logMeIn();
	}

	@When("normal user logs in with default credentials")
	public void loginAsNormalUser() {
	    login().logMeInAsUser();
	}

	@Then("username required error should be displayed")
	public void verifyUsernameRequiredError() {
	    String text = login().usernameRequiredError();
	    Assert.assertEquals(text, "Required", "Username required message mismatch");
	}

	@When("user clicks on menu {string}")
	public void clickOnMenu(String menuName) {
	    login().clickOnMenu(menuName);
	}
	
	

    
    
}

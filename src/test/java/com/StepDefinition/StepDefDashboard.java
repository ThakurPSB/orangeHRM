package com.stepdefinition;

import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefDashboard extends StepBase {
	
	@When("user clicks on the user profile")
    public void clickOnUserProfile() {
        profile().clickOnUserProfile();
    }

    @When("user clicks on about option")
    public void clickOnAboutOption() {
        profile().clickOnAbout();
    }

    @When("user clicks on support option")
    public void clickOnSupportOption() {
        profile().clickOnSupport();
    }

    @When("user clicks on change password option")
    public void clickOnChangePasswordOption() {
        profile().clickOnChangePassword();
    }

    @When("user clicks on logout option")
    public void clickOnLogoutOption() {
        profile().logout();
    }

    @Then("about information should be displayed")
    public void verifyAboutInformationDisplayed() {
        Assert.assertTrue(profile().aboutInformation(), "About information not displayed");
    }

    @Then("support information should be displayed")
    public void verifySupportInformationDisplayed() {
        Assert.assertTrue(profile().supportInformation(), "Support information not displayed");
    }

    @Then("change password page should be displayed")
    public void verifyChangePasswordPageDisplayed() {
        Assert.assertTrue(profile().checkIfChangePasswordPage(), "Change password page not displayed");
    }

    @When("user closes about information")
    public void closeAboutInformation() {
        profile().closeAboutInformatio();
    }
    
    

    @When("user opens personal details section")
    public void openPersonalDetails() {
        user().clickOnPersonalDetails();
    }
   

   
    
    @When("user clicks on search in sidebar for menu and click {string}")
    public void clickOnDashboardMenu(String menuName) {
        dashboard().clickOnMenu(menuName);
    }

    @When("user clicks on punch in button")
    public void clickOnPunchInButton() {
        dashboard().clickOnPunchInButton();
    }

    @Then("user should be navigated to url {string}")
    public void verifyAttendanceUrl(String expectedUrl) {
        Assert.assertTrue(
                dashboard().checkUrlChangeToAttendance(expectedUrl),
                "URL did not change to Attendance page"
        );
    }

    @When("user clicks on apply leave shortcut on dashboard")
    public void clickOnApplyLeaveShortcut() {
        dashboard().clickOnDashboardApplyLeaveShortcutbutton();
    }

    @When("user clicks on my timesheet shortcut on dashboard")
    public void clickOnMyTimesheetShortcut() {
        dashboard().clickOnDashboardMyTimesheetShortcutbutton();
    }
}
	


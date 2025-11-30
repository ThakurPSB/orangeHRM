package com.StepDefinition;

import org.testng.Assert;

import com.hooks.Hooks;
import com.pages.DashboardMenu;
import com.pages.UserDropdownMenu;
import com.pages.UserProfile;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefDashboard {
	
	UserDropdownMenu profile = new UserDropdownMenu(Hooks.key);
	UserProfile user = new UserProfile(Hooks.key);
	DashboardMenu dashboard = new DashboardMenu(Hooks.key);
	
	@When("user clicks on the user profile")
    public void clickOnUserProfile() {
        profile.clickOnUserProfile();
    }

    @When("user clicks on about option")
    public void clickOnAboutOption() {
        profile.clickOnAbout();
    }

    @When("user clicks on support option")
    public void clickOnSupportOption() {
        profile.clickOnSupport();
    }

    @When("user clicks on change password option")
    public void clickOnChangePasswordOption() {
        profile.clickOnChangePassword();
    }

    @When("user clicks on logout option")
    public void clickOnLogoutOption() {
        profile.logout();
    }

    @Then("about information should be displayed")
    public void verifyAboutInformationDisplayed() {
        Assert.assertTrue(profile.aboutInformation(), "About information not displayed");
    }

    @Then("support information should be displayed")
    public void verifySupportInformationDisplayed() {
        Assert.assertTrue(profile.supportInformation(), "Support information not displayed");
    }

    @Then("change password page should be displayed")
    public void verifyChangePasswordPageDisplayed() {
        Assert.assertTrue(profile.checkIfChangePasswordPage(), "Change password page not displayed");
    }

    @When("user closes about information")
    public void closeAboutInformation() {
        profile.closeAboutInformatio();
    }
    
    @When("user clicks on employee list")
    public void clickOnEmployeeList() {
        user.clickOnEmployeeList();
    }

    @When("user clicks on first employee record")
    public void clickOnFirstUser() {
        user.clickOnUser1();
    }

    @When("user opens personal details section")
    public void openPersonalDetails() {
        user.clickOnPersonalDetails();
    }

    @When("user opens membership section")
    public void openMembershipSection() {
        user.ClickOnMemebership();
    }

    @Then("SSN field should be hidden")
    public void verifySsnHidden() {
        Assert.assertTrue(user.checkSSNvisibility(), "SSN field is not hidden");
    }

    @Then("SSN field should be visible")
    public void verifySsnVisible() {
        Assert.assertTrue(user.checkSSNdisplayed(), "SSN field is not displayed");
    }

    @Then("SIN field should be hidden")
    public void verifySinHidden() {
        Assert.assertTrue(user.checkSINvisibility(), "SIN field is not hidden");
    }

    @Then("SIN field should be visible")
    public void verifySinVisible() {
        Assert.assertTrue(user.checkSINdisplayed(), "SIN field is not displayed");
    }

    @Then("nickname field should be hidden")
    public void verifyNicknameHidden() {
        Assert.assertTrue(user.checknicknameVisibility(), "Nickname field is not hidden");
    }

    @Then("nickname field should be visible")
    public void verifyNicknameVisible() {
        Assert.assertTrue(user.checkNicknameDisplayed(), "Nickname field is not displayed");
    }

    @Then("tax exemption field should be hidden")
    public void verifyTaxExemptionHidden() {
        Assert.assertTrue(user.checkTaxExemptionvisibility(), "Tax exemption field is not hidden");
    }

    @Then("tax exemption field should be visible")
    public void verifyTaxExemptionVisible() {
        Assert.assertTrue(user.checkTaxExemptionDisplayed(), "Tax exemption field is not displayed");
    }

    @Then("insurance ID field should be hidden")
    public void verifyInsuranceIdHidden() {
        Assert.assertTrue(user.checkInsurandIDvisibility(), "Insurance ID field is not hidden");
    }

    @Then("insurance ID field should be visible")
    public void verifyInsuranceIdVisible() {
        Assert.assertTrue(user.checkInsuranceIDDisplayed(), "Insurance ID field is not displayed");
    }

    @Then("office transport field should be hidden")
    public void verifyOfficeTransportHidden() {
        Assert.assertTrue(user.checkOfficeTransportvisibility(), "Office transport field is not hidden");
    }

    @Then("office transport field should be visible")
    public void verifyOfficeTransportVisible() {
        Assert.assertTrue(user.checkOfficeTransportDisplayed(), "Office transport field is not displayed");
    }
    
    @When("user clicks on search in sidebar for menu and click {string}")
    public void clickOnDashboardMenu(String menuName) {
        dashboard.clickOnMenu(menuName);
    }

    @When("user clicks on punch in button")
    public void clickOnPunchInButton() {
        dashboard.clickOnPunchInButton();
    }

    @Then("user should be navigated to url {string}")
    public void verifyAttendanceUrl(String expectedUrl) {
        Assert.assertTrue(
                dashboard.checkUrlChangeToAttendance(expectedUrl),
                "URL did not change to Attendance page"
        );
    }

    @When("user clicks on apply leave shortcut on dashboard")
    public void clickOnApplyLeaveShortcut() {
        dashboard.clickOnDashboardApplyLeaveShortcutbutton();
    }

    @When("user clicks on my timesheet shortcut on dashboard")
    public void clickOnMyTimesheetShortcut() {
        dashboard.clickOnDashboardMyTimesheetShortcutbutton();
    }
}
	


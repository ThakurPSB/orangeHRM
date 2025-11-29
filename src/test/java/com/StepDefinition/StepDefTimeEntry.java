package com.StepDefinition;

import org.testng.Assert;

import com.hooks.Hooks;
import com.pages.TimeMenu;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefTimeEntry {
	
	TimeMenu timeMenu = new TimeMenu(Hooks.key);
	
	String previousMondayHours;

    // ---------------------------- MENU -------------------------------

    @When("user navigates to time menu {string}")
    public void userNavigatesToTimeMenu(String menuName) {
    	timeMenu.clickOnMenu(menuName);
    }

    // ---------------------------- CREATE / EDIT TIMESHEET -------------------------------

    @When("user clicks create or edit timesheet button")
    public void userClicksCreateOrEditTimesheetButton() {
    	timeMenu.clickOnCreateTimesheetButtonOrEditTimesheetButton();
    }

    @When("user clicks edit timesheet button")
    public void userClicksEditTimesheetButton() {
    	timeMenu.clickOnEditTimesheetButton();
    }

    @When("user clicks submit timesheet button")
    public void userClicksSubmitTimesheetButton() {
    	timeMenu.clickOnSubmitTimesheetButton();
    }

    @When("user clicks save timesheet button")
    public void userClicksSaveTimesheetButton() {
    	timeMenu.clickOnSaveTimesheetButton();
    }

    @When("user clicks view timesheet button")
    public void userClicksViewTimesheetButton() {
    	timeMenu.clickOnViewTimesheetButton();
    }

    @When("user clicks reject timesheet button")
    public void userClicksRejectTimesheetButton() {
    	timeMenu.clickOnRejectTimesheetButton();
    }

    // ---------------------------- PROJECT / ACTIVITY -------------------------------

    @When("user deletes existing timesheet entry")
    public void userDeletesExistingTimesheetEntry() {
    	timeMenu.clickOnDeleteTimesheetEntryIcon();
    }

    @When("user enters project name {string}")
    public void userEntersProjectName(String projectName) throws InterruptedException {
    	timeMenu.enterProjectName(projectName);
    }

    @When("user selects activity {string}")
    public void userSelectsActivity(String activity) throws InterruptedException {
    	timeMenu.selectActivity(activity);
    }

    // ---------------------------- ENTER HOURS -------------------------------

    @When("user enters hours for monday and tuesday")
    public void userEntersHoursForMondayAndTuesday() {
    	timeMenu.enterHoursForMondayAndTuesday();
    }

    @When("user notes current monday hours")
    public void userNotesCurrentMondayHours() {
        previousMondayHours = timeMenu.getMondayHours();
    }

    @When("user updates monday hours")
    public void userUpdatesMondayHours() {
    	timeMenu.enterUpdatedMondayHours(previousMondayHours);
    }

    @Then("monday hours should be updated")
    public void mondayHoursShouldBeUpdated() {
        Assert.assertTrue(timeMenu.checkIfMondayHoursUpdated(),
                "Monday hours were not updated!");
    }

    // ---------------------------- TOAST -------------------------------

    @Then("success toast should be displayed")
    public void successToastShouldBeDisplayed() throws Exception {
        Assert.assertTrue(timeMenu.SaveToastMessageText(),
                "Success toast not displayed");
    }

}

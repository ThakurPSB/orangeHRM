package com.stepdefinition;

import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefTimeEntry extends StepBase{
	
	String previousMondayHours;

    @When("user navigates to menu {string}")
    public void userNavigatesToTimeMenu(String menuName) {
    	timemenu().clickOnMenu(menuName);
    }

    @When("user clicks create or edit timesheet button")
    public void userClicksCreateOrEditTimesheetButton() {
    	timemenu().clickOnCreateTimesheetButtonOrEditTimesheetButton();
    }

    @When("user clicks edit timesheet button")
    public void userClicksEditTimesheetButton() {
    	timemenu().clickOnEditTimesheetButton();
    }

    @When("user clicks submit timesheet button")
    public void userClicksSubmitTimesheetButton() {
    	timemenu().clickOnSubmitTimesheetButton();
    }

    @When("user clicks save timesheet button")
    public void userClicksSaveTimesheetButton() {
    	timemenu().clickOnSaveTimesheetButton();
    }

    @When("user clicks view timesheet button")
    public void userClicksViewTimesheetButton() {
    	timemenu().clickOnViewTimesheetButton();
    }

    @When("user clicks reject timesheet button")
    public void userClicksRejectTimesheetButton() {
    	timemenu().clickOnRejectTimesheetButton();
    }

    @When("user deletes existing timesheet entry")
    public void userDeletesExistingTimesheetEntry() {
    	timemenu().clickOnDeleteTimesheetEntryIcon();
    }

    @When("user enters project name {string}")
    public void userEntersProjectName(String projectName) throws InterruptedException {
    	timemenu().enterProjectName(projectName);
    }

    @When("user selects activity {string}")
    public void userSelectsActivity(String activity) throws InterruptedException {
    	timemenu().selectActivity(activity);
    }

    @When("user enters hours for monday and tuesday")
    public void userEntersHoursForMondayAndTuesday() {
    	timemenu().enterHoursForMondayAndTuesday();
    }

    @When("user notes current monday hours")
    public void userNotesCurrentMondayHours() {
        previousMondayHours = timemenu().getMondayHoursViewMode();
    }
    
    String previosMondayHours;

    @When("user updates monday hours")
    public void userUpdatesMondayHours() {
    	timemenu().enterUpdatedMondayHours(previousMondayHours);
    }

    @Then("monday hours should be updated")
    public void mondayHoursShouldBeUpdated() {
        Assert.assertNotEquals(timemenu().getMondayHoursViewMode(),previousMondayHours,
                "Monday hours were not updated!");
    }
    

    @Then("success toast should be displayed")
    public void successToastShouldBeDisplayed() throws Exception {
        Assert.assertTrue(timemenu().SaveToastMessageText(),
                "Success toast not displayed");
    }

}

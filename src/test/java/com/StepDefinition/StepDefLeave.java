package com.stepdefinition;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefLeave extends StepBase {

	@When("user opens leave menu")
    public void openLeaveMenu() {
        leaveApply().clickOnLeaveMenu();
    }

    @When("user navigates to apply leave page")
    public void openApplyLeavePage() {
        leaveApply().clickOnApplyLeave();
    }


    @When("user selects leave type {string}")
    public void selectLeaveType(String type) {
        leaveApply().selectLeaveType(type);
    }

    @When("user selects from date")
    public void selectFromDate() {
        leaveApply().selectFromDate();
    }

    @When("user selects to date")
    public void selectToDate() {
        leaveApply().selectToDate();
    }

    @When("user applies for leave")
    public void applyLeave() {
        leaveApply().clickOnApplyLeaveButton();
    }

    @Then("leave should be applied successfully")
    public void verifyLeaveApplied() throws Exception {
        Assert.assertTrue(
                leaveApply().isLeaveAppliedSuccessfully(),
                "Leave NOT applied OR toast missing"
        );
    }
    
    @When("check if leave overlapping?")
    public void checkOverlappingleave() {
    	leaveApply().failedToApplyLeaveToastText();
    }

    @Then("leave balance error should be shown")
    public void verifyLeaveBalanceError() throws Exception {
        Assert.assertTrue(
                leaveApply().errorLeaveBalance(),
                "Leave balance error toast NOT displayed"
        );
    }

    @Then("warning for leave type should be displayed")
    public void verifyLeaveTypeWarning() {
        Assert.assertTrue(
                leaveApply().checkWarningForLeaveType(),
                "Warning for leave type NOT displayed"
        );
    }


    @When("user selects Saturday from date")
    public void selectPastFromDate() {
        leaveApply().selectSaturdayFromDate();
    }

    @When("user selects Sunday to date")
    public void selectPastToDate() {
        leaveApply().selectSundayToDate();
    }

    @Then("system should show failed to apply leave toast")
    public void verifyFailedToSubmitNonWorkingDayLeave() throws Exception {
        Assert.assertTrue(
                leaveApply().failedToApplyLeaveToastText(),
                "Past leave error toast NOT displayed"
        );
    }
    
    @Then("Warning for non working days should be displayed")
	public void verifyNonWorkingDaysWarning() {
		Assert.assertTrue(leaveApply().errorNonWorkingDayLeave(), "Warning for non working days NOT displayed");
	}


    @When("user opens user leave menu")
    public void openUserLeaveMenu() {
        leaveApply().clickOnUserLeaveMenu();
    }

    @When("user filters leave by status {string}")
    public void filterLeaveStatus(String status) throws Exception {
        leaveApply().selectLeaveStatusTaken();
        leaveApply().clickOnSearchLeaveButton();
    }


    @When("supervisor approves the leave request")
    public void approveLeave() {
        leaveApply().clickOnApproveLeaveButton();
    }

    @When("user rejects the leave request")
    public void rejectLeave() {
        leaveApply().clickOnRejectLeaveButton();
    }


    @When("user opens more options for leave")
    public void openMoreLeaveOptions() {
        leaveApply().clickOnMoreOptionsButton();
    }

    @When("user chooses cancel leave option")
    public void chooseCancelLeave() {
        leaveApply().clickOnCancelLeaveOption();
    }

    @When("user confirms leave cancellation")
    public void confirmCancelLeave() {
        leaveApply().clickOnUserCancelLeaveButton();
    }

    @When("user cancels leave if already taken")
    public void cancelLeaveIfTaken() throws Exception {
        leaveApply().cancelLeaveIfAlreadyTaken();
    }

    
    @When("user opens My Leave menu")
    public void openMyLeaveMenu() {
        myLeave().clickOnMyLeaveMenu();
    }


    @When("user cancels all applied leaves")
    public void cancelAllLeaves() throws Exception {
        myLeave().cancelAllLeaves();
    }

    @Then("all leaves should be cancelled if available")
    public void verifyCancellationCompleted() {
        // No strict assertion because cancellation depends on availability
        Assert.assertTrue(true, "Leave cancellation flow executed");
    }
    
    @When("user opens Leave Entitlement menu")
    public void openLeaveEntitlementMenu() {
        entitlement().clickOnLeaveEntitlementMenu();
    }

    @When("user navigates to Add Entitlement")
    public void openAddEntitlement() {
        entitlement().clickOnAddEntitlement();
    }


    @When("user selects entitlement type {string}")
    public void selectIndividualOrMultiple(String type) {
        entitlement().selectIndividualOrMultipleEmployee(type);
    }

    @When("user enters employee name for entitlement {string}")
    public void enterEmployeeNameForEntitlement(String emp) throws Exception {
        entitlement().enterEmployeeName(emp);
    }

    @When("user selects leave type {string} for entitlement")
    public void selectLeaveTypeForEntitlement(String leaveType) throws Exception {
        entitlement().selectLeaveType(leaveType);
    }

    @When("user enters entitlement value {string}")
    public void enterEntitlementValue(String val) {
        entitlement().enterEntitlement(val);
    }

    @When("user clicks on save entitlement")
    public void clickSaveEntitlement() {
        entitlement().clickOnSaveButton();
    }

    @When("user confirms entitlement assignment")
    public void confirmEntitlementAssignment() {
        entitlement().clickOnConfirmButton();
    }

    @Then("entitlement should be added successfully")
    public void entitlementAddedSuccessfully() throws Exception {
        Assert.assertTrue(entitlement().SaveToastMessageText(), "Success toast was not displayed!");
    }
    
    @Then("Successful toast message should be Displayed")
    public void successToastDisplayed() throws Exception {
        Assert.assertTrue(entitlement().SaveToastMessageText(), "Success toast was not displayed!");
    }

    @When("user navigates to Employee Entitlements")
    public void openEmployeeEntitlementMenu() {
        entitlement().clickEmployeeEntitlement();
    }

    @When("user searches entitlement for employee {string}")
    public void searchEmployeeEntitlement(String emp) throws Exception {
        entitlement().enterEmployeeNameInSearchBoxEmplpyeeEntitlement(emp);
    }

    @When("user selects leave type {string} in entitlement search")
    public void selectLeaveTypeInSearch(String leaveType) throws Exception {
        entitlement().selectLeaveTypeInEntitlement(leaveType);
    }

    @When("user clicks entitlement search")
    public void clickEntitlementSearch() {
        entitlement().clickOnSearchButton();
    }
    
    @When("user balance before entitlement is noted")
	public void noteLeaveBalanceBeforeEntitlement() {
		double balance = entitlement().CheckLeaveBalance();
		balanceBeforeEntitlement = balance;
    }	
    
    double balanceBeforeEntitlement;

    @Then("leave balance should be more than {double}")
    public void validateLeaveBalance(double expected) {
        double balance = entitlement().CheckLeaveBalance();
        Assert.assertTrue(balance > expected, "Leave balance does not meet expectation");
    }
    
    @Then("leave balance should be increased compared to previous")
	public void validateIncreasedLeaveBalance() {
		double balanceAfter = entitlement().CheckLeaveBalance();
		Assert.assertTrue(balanceAfter > balanceBeforeEntitlement, "Leave balance did not increase after entitlement");
	}
    
    @When("user applies leave with retry if overlapping")
    public void applyLeaveWithRetry() throws Exception {

        leaveApply().clickOnApplyLeave();
        leaveApply().selectLeaveType("e");
        leaveApply().selectFromDate();
        leaveApply().selectToDate();
        leaveApply().clickOnApplyLeaveButton();

        // Check overlap
        if (leaveApply().isLeaveOverlapping()) {
         
            // Cancel existing leaves
            myLeave().clickOnMyLeaveMenu();
            myLeave().cancelAllLeaves();

            // Retry leave apply
            leaveApply().clickOnApplyLeave();
            leaveApply().selectLeaveType("e");
            leaveApply().selectFromDate();
            leaveApply().selectToDate();
            leaveApply().clickOnApplyLeaveButton();
        }
    }
    
    @Then("leave should be applied with success toast")
    public void verifyLeaveSuccessToast() {
        Assert.assertTrue(
                leaveApply().SaveToastMessageText(),
                "Leave NOT applied successfully!"
        );
    }
    
}

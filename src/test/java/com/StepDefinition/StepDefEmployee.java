package com.StepDefinition;

import org.testng.Assert;

import com.hooks.Hooks;
import com.pages.AddEmployeePage;
import com.pages.EmployeeListMenu;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefEmployee extends StepBase{
	
	EmployeeListMenu emplist = new EmployeeListMenu(Hooks.key);
	AddEmployeePage addemp = new AddEmployeePage(Hooks.key);

	@When("user opens employee list menu")
    public void openEmployeeListMenu() throws Exception {
        emplist.clickOnEmployeeListMenu();
    }


    @When("user enters employee name {string}")
    public void enterEmployeeName(String name) throws Exception {
        emplist.enterEmployeeName(name);
    }

    @When("user enters employee id {string}")
    public void enterEmployeeId(String id) {
        emplist.enterEmployeeID(Integer.parseInt(id));
    }

    @When("user selects employment status {string}")
    public void selectEmploymentStatus(String status) throws Exception {
        emplist.selectEmploymentStatus(status);
    }

    @When("user selects include employee type {string}")
    public void selectIncludeType(String type) throws Exception {
        emplist.includePastCurrentEmp(type);
    }

    @When("user enters supervisor name {string}")
    public void enterSupervisorName(String supervisor) throws Exception {
        emplist.enterSupervisorName(supervisor);
    }

    @When("user selects job title {string}")
    public void selectJobTitle(String job) {
        emplist.selectJobTitle(job);
    }

    @When("user selects sub unit {string}")
    public void selectSubUnit(String unit) {
        emplist.selectSubUnit(unit);
    }


    @When("user clicks search button")
    public void clickSearchButton() throws Exception {
        emplist.clickOnSearchButton();
    }

    @When("user clicks reset search button")
    public void clickResetButton() throws Exception {
        emplist.clickOnResetButton();
    }


    @Then("search result should match employee id {string}")
    public void verifySearchById(String id) {
        Assert.assertTrue(
                emplist.searchResultID(id),
                "Employee ID does not match search result"
        );
    }

    @Then("search result should match first name {string}")
    public void verifySearchByFirstName(String name) {
        Assert.assertTrue(
                emplist.searchResultfirstName(name),
                "Employee first name does not match search result"
        );
    }

    @Then("search result should match last name {string}")
    public void verifySearchByLastName(String name) {
        Assert.assertTrue(
                emplist.searchResultLastName(name),
                "Employee last name does not match search result"
        );
    }

    @Then("search result should match job title {string}")
    public void verifySearchByJobTitle(String title) {
        Assert.assertTrue(
                emplist.searchResultJobTitle(title),
                "Employee job title does not match search result"
        );
    }

    @Then("search result should match sub unit {string}")
    public void verifySearchBySubUnit(String unit) {
        Assert.assertTrue(
                emplist.searchResultSubUnit(unit),
                "Employee sub-unit does not match search result"
        );
    }

    @Then("search result should match employment status {string}")
    public void verifySearchByEmploymentStatus(String status) {
        Assert.assertTrue(
                emplist.searchResultEmploymentStatus(status),
                "Employee employment status does not match search result"
        );
    }

    @Then("search result should match supervisor {string}")
    public void verifySearchBySupervisor(String name) {
        Assert.assertTrue(
                emplist.searchResultSupervisor(name),
                "Employee supervisor name does not match search result"
        );
    }


    @When("user reads employee table data")
    public void readEmployeeTable() {
        emplist.readTable();
    }


    @When("user clicks add new employee button")
    public void clickAddNewEmployeeButton() throws Exception {
        emplist.clickOnAddNewEmployeeButton();
    }


    @When("user selects all search results")
    public void selectAllEmployees() {
        Assert.assertTrue(
                emplist.clickOnSelectAll(),
                "Unable to select all employees"
        );
    }

    @When("user deletes selected employees")
    public void deleteSelectedEmployees() throws Exception {
        emplist.DeleteSelectedUsers();
    }

    @Then("no records toast should appear if no results")
    public void verifyInfoToastDisplayed() {
        Assert.assertTrue(
                emplist.infoToastMessage(),
                "No records toast not displayed when expected"
        );
    }
    
    @When("user clicks on add new employee button")
    public void clickOnAddNewEmployeeButton() {
        addemp.clickOnAddNewEmployeeButton();
    }


    @When("user selects profile picture")
    public void selectProfilePic() throws Exception {
    	addemp = new AddEmployeePage(Hooks.key);
        addemp.selectProfilePic();
    }

    @When("user uploads profile picture from path {string}")
    public void uploadProfileFromPath(String path) throws Exception {
        addemp.uploadFileUsingRobot(path);
    }


    @When("user enters employee first name {string} middle name {string} last name {string}")
    public void enterEmployeeFullName(String fname, String mname, String lname) throws Exception {
        addemp.EnterUserDetails(fname, mname, lname);
    }


    @When("user enters unique employee id")
    public void enterUniqueEmployeeId() throws Exception {
        addemp.enterEmployeeID();
    }


    @When("user clicks save employee button")
    public void clickSaveEmployeeButton() {
        addemp.clickOnSaveButton();
    }

    @Then("employee should be saved successfully")
    public void verifyEmployeeSaved() throws Exception {
        Assert.assertTrue(
                addemp.SaveToastMessageText(),
                "Save toast not displayed, employee may not have been saved"
        );
    }


    @When("user enables create login details")
    public void enableCreateLoginDetails() {
        addemp.clickOnCreateLoginDetails();
    }

    @When("user enters login username {string} password {string} and status {string}")
    public void enterLoginDetails(String username, String password, String status) {
        boolean isEnabled = status.equalsIgnoreCase("enabled");
        addemp.enterLoginDetails(username, password, isEnabled);
    }
    
    

}

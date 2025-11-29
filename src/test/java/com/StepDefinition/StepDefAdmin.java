package com.StepDefinition;

import org.testng.Assert;

import com.hooks.Hooks;
import com.pages.AdminJobTitles;
import com.pages.AdminMenu;
import com.pages.AdminOrganizationMenu;
import com.pages.AdminQualificationMenu;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefAdmin {
	
	AdminMenu admin = new AdminMenu(Hooks.key);
	AdminJobTitles jobs = new AdminJobTitles(Hooks.key);
	AdminOrganizationMenu orgMenu = new AdminOrganizationMenu(Hooks.key);
	AdminQualificationMenu qualification = new AdminQualificationMenu(Hooks.key);
	
	
	@When("user clicks on admin menu")
    public void clickOnAdminMenu() {
        admin.clickOnAdminMenu();
    }


    @When("user enters admin username {string}")
    public void enterAdminUsername(String user) {
        admin.enterUsername(user);
    }

    @When("user clicks on admin search button")
    public void clickAdminSearchButton() throws InterruptedException {
        admin.clickOnSearchButton();
    }

    @When("user resets admin search filter")
    public void clickAdminResetButton() throws InterruptedException {
        admin.clickOnResetButton();
    }

    @Then("username search result should show username {string}")
    public void verifyUsernameSearchResult(String expectedUser) {
        Assert.assertTrue(
                admin.searchResultUsername(expectedUser),
                "Username search result mismatch"
        );
    }


    @When("user selects admin user role {string}")
    public void selectAdminUserRole(String role) {
        admin.selectUserRole(role);
    }

    @Then("admin search result should show role {string}")
    public void verifyAdminRoleSearchResult(String expectedRole) {
        Assert.assertTrue(
                admin.searchResultRole(expectedRole),
                "User role search result mismatch"
        );
    }


    @When("user enters employee name {string} in admin search")
    public void enterEmployeeNameInAdmin(String name) throws InterruptedException {
        admin.enterEmployeeName(name);
    }

    @Then("admin search result should show employee name {string}")
    public void verifyEmployeeNameSearchResult(String expectedName) {
        Assert.assertTrue(
                admin.searchResultsEmployeeName(expectedName),
                "Employee name search result mismatch"
        );
    }


    @When("user selects admin login status {string}")
    public void selectAdminLoginStatus(String status) {
        admin.selectLoginStatus(status);
    }

    @Then("admin search result should show status {string}")
    public void verifyAdminStatusSearchResult(String expectedStatus) {
        Assert.assertTrue(
                admin.searchResultStatus(expectedStatus),
                "Login status search result mismatch"
        );
    }
    
    // Navigate to Admin module
    @When("user clicks on admin main menu")
    public void clickOnAdminMainMenu() {
    	jobs.clickOnAdminMenu();
    }

    // Click Job submenu options: Job Titles, Pay Grades, Employment Status, etc.
    @When("user selects job submenu option {string}")
    public void selectJobSubmenuOption(String optionText) {
        jobs.clickOnJobSubMenu(optionText);
    }

    // Assert element presence in first row of the Job table
    @Then("job table should contain entry {string}")
    public void verifyElementInJobTable(String element) throws InterruptedException {
        Assert.assertTrue(
                jobs.checkElementinTable(element),
                "Expected element not found in job table: " + element
        );
    }
    
    @When("user clicks on organization main menu")
    public void clickOnOrganizationMainMenu() {
        orgMenu.clickOnOrgMenu();
    }


    @When("user selects organization sub menu option {string}")
    public void selectOrganizationSubMenuOption(String subMenu) {
        orgMenu.clickOnOrgSubMenu(subMenu);
    }


    @When("user clicks on organization edit button")
    public void clickOnOrganizationEditButton() {
        orgMenu.clickOnEditButton();
    }

    @When("user updates registration number to {string}")
    public void updateRegistrationNumber(String regNo) {
        orgMenu.updateRegNum(regNo);
    }

    @Then("registration number should update to {string}")
    public void verifyUpdatedRegistrationNumber(String expected) {
        Assert.assertTrue(orgMenu.regNumberCheck(expected),
                "Registration number mismatch");
    }

    @When("user updates organization name to {string}")
    public void updateOrganizationName(String name) {
        orgMenu.updateOrgName(name);
    }

    @Then("organization name should update to {string}")
    public void verifyUpdatedOrganizationName(String expected) {
        Assert.assertTrue(orgMenu.orgNameTextCheck(expected),
                "Organization name mismatch");
    }

    @When("user updates tax id to {string}")
    public void updateTaxId(String tax) {
        orgMenu.updateTaxID(tax);
    }

    @Then("tax id should update to {string}")
    public void verifyUpdatedTaxId(String expected) {
        Assert.assertTrue(orgMenu.taxIDtextCheck(expected),
                "Tax ID mismatch");
    }

    @When("user saves organization info")
    public void saveOrganizationInformation() {
        orgMenu.clickOnSave();
    }

    @Then("organization info should be saved successfully")
    public void verifyOrganizationInfoSaved() throws Exception {
        Assert.assertTrue(orgMenu.SaveToastMessageText(),
                "Organization info save toast not displayed");
    }


    @When("user searches for location named {string}")
    public void searchLocationByName(String locationName) {
        orgMenu.searchLocation(locationName);
    }

    @Then("error toast should be displayed for no location found")
    public void verifyLocationNotFoundToast() throws Exception {
        Assert.assertTrue(orgMenu.errorToastMessageText(),
                "Expected error toast but not found");
    }


    @When("user deletes location named {string}")
    public void deleteLocation(String locationName) throws Exception {
        orgMenu.deleteSelected(locationName);
    }


    @When("user clicks on add new location button")
    public void clickOnAddNewLocationButton() {
        orgMenu.clickOnAddLocationButton();
    }

    @When("user enters location name {string}")
    public void enterLocationName(String name) {
        orgMenu.enterLocation(name);
    }

    @When("user enters city name {string}")
    public void enterCityName(String city) {
        orgMenu.enterCity(city);
    }

    @When("user enters state name {string}")
    public void enterStateName(String state) {
        orgMenu.enterState(state);
    }

    @When("user enters zip code {string}")
    public void enterZipCode(String zip) {
        orgMenu.enterZip(zip);
    }

    @When("user selects country {string}")
    public void selectLocationCountry(String country) throws Exception {
        orgMenu.selectCountry(country);
    }

    @When("user saves location")
    public void saveLocation() {
        orgMenu.clickOnSaveLocationButton();
    }

    @Then("location should be saved successfully")
    public void verifyLocationSaved() throws Exception {
        Assert.assertTrue(orgMenu.SaveToastMessageText(),
                "Location save toast not displayed");
    }
    
    @When("user clicks on qualification main menu")
    public void clickOnQualificationMainMenu() {
        qualification.clickOnQualificationMenu();
    }


    @When("user opens skills menu")
    public void openSkillsMenu() {
        qualification.clickOnSkills();
    }

    @When("user clicks on add skill button")
    public void clickAddSkillButton() {
        qualification.clickOnAddSkillsButton();
    }

    @When("user enters skill name {string}")
    public void enterSkillName(String skillName) {
        qualification.enterSkillName(skillName);
    }

    @When("user saves the skill")
    public void saveSkill() {
        qualification.clickOnSaveSkillButton();
    }

    @Then("skill should be saved successfully")
    public void verifySkillSavedSuccessfully() throws Exception {
        Assert.assertTrue(
                qualification.SaveToastMessageText(),
                "Skill save success toast not displayed"
        );
    }

    @When("user deletes skill named {string}")
    public void deleteSkillByName(String skill) {
        qualification.deleteEnteredSkill(skill);
    }
    
    
    
}

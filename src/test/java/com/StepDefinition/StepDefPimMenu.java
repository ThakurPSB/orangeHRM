package com.stepdefinition;

import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefPimMenu extends StepBase{
	
	@When("user clicks on PIM menu")
    public void clickOnPimMenu() {
        pim().clickOnPIM();
    }

    @When("user clicks on PIM configuration")
    public void clickOnPimConfiguration() {
        pim().clickOnConfiguration();
    }

    @When("user clicks on custom fields in configuration")
    public void clickOnCustomFields() {
        customField().clickOnCustomFields();
    }
    
    @When("user clicks on sub field in configuration {string}")
    public void clickOnSubMenuConfiguration(String s) {
    	pim().clickConfigMenuItem(s);
    }

    @When("user clicks on optional fields in configuration")
    public void clickOnOptionalFields() {
        pim().clickOnOptionalFields();
    }

    @When("user clicks on PIM configuration submenu {string}")
    public void clickOnPimConfigurationSubMenu(String menu) {
        pim().ClickOnSubMenuConfiguration(menu);
    }

    @When("user turns on show deprecated fields")
    public void turnOnDeprecatedFields() {
        pim().turnONshowDeprecatedField();
    }

    @When("user turns off show deprecated fields")
    public void turnOffDeprecatedFields() {
        pim().turnOFFshowDeprecatedField();
    }

    @When("user turns on SSN field")
    public void turnOnSsnField() {
        pim().turnONSSNfield();
    }

    @When("user turns off SSN field")
    public void turnOffSsnField() {
        pim().turnOFFSSNfield();
    }

    @When("user turns on SIN field")
    public void turnOnSinField() {
        pim().turnONSINfield();
    }

    @When("user turns off SIN field")
    public void turnOffSinField() {
        pim().turnOFFSINfield();
    }

    @When("user turns on US tax exemption menu field")
    public void turnOnUsTaxExemptionMenu() {
        pim().turnONUStaxExemptionMenufield();
    }

    @When("user turns off US tax exemption menu field")
    public void turnOffUsTaxExemptionMenu() {
        pim().turnOFFUStaxExemptionMenufield();
    }

    @When("user saves optional field preferences")
    public void clickSaveOptionalFieldPreferences() {
        pim().clickOnSaveButtonOptionalFidls();
    }

    @Then("optional field preferences should be saved successfully")
    public void verifyOptionalFieldSavedToast() {
        Assert.assertTrue(
                pim().SaveToastMessageText(),
                "Success toast message not displayed after saving preferences"
        );
    }
    
    @When("user clicks on custom fields")
    public void clickOnCustomField()  {
        customField().clickOnCustomFields();
    }

    @When("user clicks on add custom field button")
    public void clickOnAddCustomFieldButton() {
        customField().clickOnAddCustomFieldsButton();
    }

    @When("user enters custom field name {string}")
    public void enterCustomFieldName(String name) {
        customField().enterFieldName(name);
    }

    @When("user selects screen option")
    public void selectScreenOption() {
        customField().clickOnScreenOption();
    }

    @When("user selects input type dropdown")
    public void selectInputTypeDropdown() {
        customField().SelectTypeOfInputDropdown();
    }

    @When("user selects input type text")
    public void selectInputTypeText() {
        customField().selectTypeOfInputText();
    }

    @When("user enters custom field dropdown options {string}")
    public void enterDropdownOptions(String options) {
        customField().enterOptions(options);
    }

    @When("user saves the custom field")
    public void clickOnSaveCustomField() {
        customField().clickOnSaveCustomFieldButton();
    }

    @Then("custom field should be saved successfully")
    public void verifyCustomFieldSaved() {
        Assert.assertTrue(
                customField().SaveToastMessageText(),
                "Custom field save toast not displayed"
        );
    }

    @When("user deletes custom field named {string}")
    public void deleteCustomField(String fieldName) throws Exception {
        customField().SelectFieldToDelete(fieldName);
    }

    @When("user clicks on reporting method menu")
    public void clickOnReportingMethodMenu() {
        reportingMethod().clickOnReportingMethod();
    }

    @When("user clicks on add reporting method button")
    public void clickOnAddReportingMethodButton() {
        reportingMethod().AddReportingMethodButton();
    }

    @When("user enters reporting method name {string}")
    public void enterReportingMethodName(String name) {
        reportingMethod().addrReportingMethod(name);
    }

    @Then("reporting method should be saved or duplicate error should appear")
    public void verifyReportingMethodSaveOrExist() {
        Assert.assertTrue(
                reportingMethod().checkAssertReportingMethod(),
                "Reporting method not saved or error not displayed"
        );
    }

    @When("user deletes reporting method with name {string}")
    public void deleteReportingMethod(String name) throws Exception {
        reportingMethod().deleteSelectedReportingMethod(name);
    }

    public void verifyReportingMethodSuccessToast() throws Exception {
        Assert.assertTrue(
                reportingMethod().SaveToastMessageText(),
                "Success toast not displayed"
        );
    }

    @Then("error toast for reporting method should be displayed")
    public void verifyReportingMethodErrorToast() throws Exception {
        Assert.assertTrue(
                reportingMethod().errorToastMessageText(),
                "Error toast not displayed"
        );
    }
    
    @When("user clicks on termination reasons menu")
    public void clickOnTerminationReasonsMenu() {
        terminationReasons().clickOnTerminationReasonsMenu();
    }

    @When("user clicks on add termination reason button")
    public void clickOnAddTerminationReasonButton() {
        terminationReasons().ClickOnAddTerminationReasonButton();
    }

    @When("user enters termination reason {string}")
    public void enterTerminationReason(String reasonName) {
        terminationReasons().AddTerminationReason(reasonName);
    }

    @Then("termination reason should be saved or duplicate error should appear")
    public void verifyTerminationReasonSavedOrExists() {
        Assert.assertTrue(
                terminationReasons().checkAssertTerminationReason(),
                "Termination reason was neither saved nor identified as duplicate"
        );
    }

    @When("user deletes termination reason named {string}")
    public void deleteTerminationReason(String reason) throws Exception {
        terminationReasons().deleteSelectedTerminationReason(reason);
    }

    @Then("success toast for termination reason should be displayed")
    public void verifyTerminationReasonSuccessToast() {
        Assert.assertTrue(
                terminationReasons().SaveToastMessageText(),
                "Success toast not displayed for termination reason"
        );
    }

    @Then("error toast for termination reason should be displayed")
    public void verifyTerminationReasonErrorToast() throws Exception {
        Assert.assertTrue(
                terminationReasons().errorToastMessageText(),
                "Error toast not displayed for termination reason"
        );
    }
 
    @When("user clicks on employee list")
    public void clickOnEmployeeList() {
        user().clickOnEmployeeList();
    }

    @When("user clicks on first employee record")
    public void clickOnFirstUser() {
        user().clickOnUser1();
    }
    
    @Then("SSN field should be hidden")
    public void verifySsnHidden() {
        Assert.assertTrue(user().checkSSNvisibility(), "SSN field is not hidden");
    }

    @Then("SSN field should be visible")
    public void verifySsnVisible() {
        Assert.assertTrue(user().checkSSNdisplayed(), "SSN field is not displayed");
    }

    @Then("SIN field should be hidden")
    public void verifySinHidden() {
        Assert.assertTrue(user().checkSINvisibility(), "SIN field is not hidden");
    }

    @Then("SIN field should be visible")
    public void verifySinVisible() {
        Assert.assertTrue(user().checkSINdisplayed(), "SIN field is not displayed");
    }

    @Then("nickname field should be hidden")
    public void verifyNicknameHidden() {
        Assert.assertTrue(user().checknicknameVisibility(), "Nickname field is not hidden");
    }

    @Then("nickname field should be visible")
    public void verifyNicknameVisible() {
        Assert.assertTrue(user().checkNicknameDisplayed(), "Nickname field is not displayed");
    }

    @Then("tax exemption field should be hidden")
    public void verifyTaxExemptionHidden() {
        Assert.assertTrue(user().checkTaxExemptionvisibility(), "Tax exemption field is not hidden");
    }

    @Then("tax exemption field should be visible")
    public void verifyTaxExemptionVisible() {
        Assert.assertTrue(user().checkTaxExemptionDisplayed(), "Tax exemption field is not displayed");
    }
    
    @When("user opens membership section")
    public void openMembershipSection() {
        user().ClickOnMemebership();
    }
    
    @Then("insurance ID field should be hidden")
    public void verifyInsuranceIdHidden() {
        Assert.assertTrue(user().checkInsurandIDvisibility(), "Insurance ID field is not hidden");
    }

    @Then("insurance ID field should be visible")
    public void verifyInsuranceIdVisible() {
        Assert.assertTrue(user().checkInsuranceIDDisplayed(), "Insurance ID field is not displayed");
    }

    @Then("office transport field should be hidden")
    public void verifyOfficeTransportHidden() {
        Assert.assertTrue(user().checkOfficeTransportvisibility(), "Office transport field is not hidden");
    }

    @Then("office transport field should be visible")
    public void verifyOfficeTransportVisible() {
        Assert.assertTrue(user().checkOfficeTransportDisplayed(), "Office transport field is not displayed");
    }
    
    

	
}

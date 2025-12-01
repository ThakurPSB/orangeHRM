package com.stepdefinition;

import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefPerformance extends StepBase{
	
	@When("user opens performance menu")
    public void userOpensPerformanceMenu() {
        performance().clickOnPerformanceMenu();
    }

    @When("user clicks performance configure")
    public void userClicksPerformanceConfigure() throws Exception {
    	performance().clickOnPerformanceConfigure();
    }

    @When("user selects KPIs option")
    public void userSelectsKpisOption() {
    	performance().clickOnKPIs();
    }


    @When("user clicks add KPI")
    public void userClicksAddKpi() {
    	performance().clickOnAddKPIButton();
    }

    @When("user enters KPI name {string}")
    public void userEntersKpiName(String kpiName) {
    	performance().enterKeyPerformanceIndicator(kpiName);
    }

    @When("user selects job title for KPI")
    public void userSelectsJobTitleForKpi() throws Exception {
    	performance().selecrJobTitleToAddKPI();
    }

    @When("user enters min and max ratings")
    public void userEntersMinAndMaxRatings() {
    	performance().enterMinAndMaxRatings();
    }

    @When("user saves the KPI")
    public void userSavesTheKpi() {
    	performance().clickOnSaveKPIButton();
    }

    @Then("KPI {string} should appear in the KPI list")
    public void kpiShouldAppearInKpiList(String kpiName) {
        Assert.assertTrue(
        		performance().searchResultKPI(kpiName),
                "KPI not found in the list!"
        );
    }


    @When("user deletes KPI {string}")
    public void userDeletesKpi(String kpiName) {
    	performance().searchResultKPIAndDelete(kpiName);
    }

    @Then("KPI {string} should not exist")
    public void kpiShouldNotExist(String kpiName) {
        Assert.assertFalse(
        		performance().searchResultKPI(kpiName),
                "KPI still exists after delete!"
        );
    }


    @When("user clicks KPI delete icon")
    public void userClicksKpiDeleteIcon() {
    	performance().clickOnDeleteKPIIcon();
    }

}

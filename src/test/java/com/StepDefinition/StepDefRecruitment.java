package com.stepdefinition;

import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefRecruitment extends StepBase {

    @When("user opens recruitment menu")
    public void openRecruitmentMenu() {
        recruitment().clickOnRecruitmentMenu();
    }

    @When("user opens vacancies menu")
    public void openVacanciesMenu() {
        recruitment().clickOnVacanciesMenu();
    }

    @When("user opens candidates menu")
    public void openCandidatesMenu() {
        recruitment().clickOnCandidatesMenu();
    }

    @When("user clicks add vacancy")
    public void clickAddVacancy() {
        recruitment().clickOnAddVacancyButton();
    }

    @When("user selects job title {string} for vacancy")
    public void selectJobTitle(String title) throws Exception {
        recruitment().selectJobTitle(title);
    }

    @When("user enters vacancy name {string}")
    public void enterVacancyName(String vacancy) {
        recruitment().enterVacancyName(vacancy);
    }

    @When("user selects hiring manager {string}")
    public void selectHiringManager(String manager) throws Exception {
        recruitment().selectHiringManager(manager);
    }

    @When("user enters number of vacancies {string}")
    public void enterVacancyCount(String count) {
        recruitment().enterNumberOfVacancy(count);
    }

    @When("user saves vacancy")
    public void saveVacancy() throws Exception {
        recruitment().clickOnSaveVacancyButton();
    }

    @Then("vacancy {string} should be visible in vacancies list")
    public void verifyVacancyAdded(String vacancy) {
        Assert.assertTrue(recruitment().checkVacancyAddedOrNot(vacancy),
                "Vacancy NOT found after adding!");
    }


    @When("user deletes vacancy {string}")
    public void deleteVacancy(String title) {
        recruitment().deleteEnteredVacancy(title);
    }


    @When("user filters vacancy list by job title {string}")
    public void filterVacancyByJobTitle(String title) throws Exception {
        recruitment().clickOnJobTitleSearch(title);
    }

    @When("user clicks vacancy search")
    public void clickVacancySearch() {
        recruitment().clickOnVacancySearchButton();
    }


    @When("user clicks add candidate")
    public void clickAddCandidate() {
        recruitment().clickOnAddCandidateButton();
    }

    @When("user enters candidate full name {string} {string} {string}")
    public void enterCandidateFullName(String first, String middle, String last) {
        recruitment().enterCandidateFullName(first, middle, last);
    }

    @When("user selects candidate vacancy")
    public void selectCandidateVacancy() throws Exception {
        recruitment().clickOnOpenVacanciesList();
    }

    @When("user enters candidate email {string}")
    public void enterCandidateEmail(String email) throws Exception {
        recruitment().enterCandidateEmailID(email);
    }

    @When("user enters candidate contact number {string}")
    public void enterCandidatePhone(String phone) {
        recruitment().enterCandidateContactNumber(phone);
    }

    @When("user uploads candidate resume")
    public void uploadResume() throws Exception {
        recruitment().selectCandidateResume();
    }

    @When("user agrees to data consent")
    public void tickConsent() {
        recruitment().clickOnConcentClickBox();
    }

    @When("user saves candidate")
    public void saveCandidate() {
        recruitment().clickOnSaveCandidateButton();
    }

    @Then("candidate should be added successfully")
    public void verifyCandidateAdded() throws Exception {
        Assert.assertTrue(recruitment().SaveToastMessageText(),
                "Candidate add toast not displayed!");
    }


    @Then("email required error should be displayed")
    public void verifyEmailError() {
        Assert.assertTrue(recruitment().checkErrorMsg(),
                "Email required error NOT shown!");
    }
	
}

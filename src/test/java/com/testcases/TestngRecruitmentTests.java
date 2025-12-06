package com.testcases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestngRecruitmentTests extends TestBase {
	
	@Test(groups = {"regression", "recruitment"})
    @Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options vacancies")
    @Step("login and navigate to recruitment page, add vacancy for asst mananger")
    @Feature("Recruitment Menu")
    @Story("vacancies options")
	public void shouldAddVacancyWithCompleteDetailsAndReflectInCandidateForm() throws InterruptedException {
		
		login().logMeIn();
		recruitment().clickOnRecruitmentMenu();
		recruitment().clickOnVacanciesMenu();
		recruitment().clickOnAddVacancyButton();
		recruitment().enterVacancyName("Marketing Asst Manager");
		recruitment().selectJobTitle("Assistant Manager");
		recruitment().selectHiringManager("Piyush");
		recruitment().enterNumberOfVacancy("1");
		recruitment().clickOnSaveVacancyButton();
		recruitment().clickOnVacanciesMenu();
		recruitment().clickOnVacancySearchButton();
		Assert.assertTrue(recruitment().checkVacancyAddedOrNot("Marketing Asst Manager"));
		recruitment().deleteEnteredVacancy("Marketing Asst Manager");
		
	}
	
	
	@Test(groups = {"regression", "recruitment", "smoke"})
	@Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options Candidate")
    @Step("login and navigate to recruitment page, add candidate for QA Engineer")
    @Feature("Recruitment Menu")
    @Story("Candidate options")
	public void shouldAddCandidateToExistingVacancySuccessfully() throws InterruptedException, AWTException {
		
		login().logMeIn();
		recruitment().clickOnRecruitmentMenu();
		recruitment().clickOnCandidateMenu();
		recruitment().clickOnAddCandidateButton();
		recruitment().enterCandidateFullName("Sam", "Ron", "Miles");
		recruitment().clickOnOpenVacanciesList();
		recruitment().enterCandidateEmailID("samRMiles@gmail.com");
		recruitment().enterCandidateContactNumber("9558754562");
		recruitment().selectCandidateResume();
		recruitment().clickOnConcentClickBox();
		recruitment().clickOnSaveCandidateButton();
		Assert.assertTrue(recruitment().SaveToastMessageText());
		
	}
	
	@Test(groups = {"regression", "recruitment"})
	@Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options Candidate")
    @Step("login and navigate to recruitment page, add candidate without emailID")
    @Feature("Recruitment Menu")
    @Story("Candidate options")
	public void shouldPreventAddingCandidateWithoutEmail() throws InterruptedException, AWTException {
		
		login().logMeIn();
		recruitment().clickOnRecruitmentMenu();
		recruitment().clickOnCandidateMenu();
		recruitment().clickOnAddCandidateButton();
		recruitment().enterCandidateFullName("Sam", "Ron", "Miles");
		recruitment().clickOnOpenVacanciesList();
		recruitment().enterCandidateContactNumber("9558754562");
		recruitment().selectCandidateResume();
		recruitment().clickOnConcentClickBox();
		recruitment().clickOnSaveCandidateButton();
		Assert.assertTrue(recruitment().checkErrorMsg());
	}

}

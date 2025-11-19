package com.testcases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginPage;
import com.pages.PerformanceMenu;
import com.pages.RecruitmentMenu;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class TestSprint3 extends TestBase{

	private LoginPage login;
	private RecruitmentMenu recruitment;
	private PerformanceMenu performance;
	
	@BeforeMethod
	public void pageSetup() {
        login = new LoginPage(kw); 
        recruitment = new RecruitmentMenu(kw);
        performance = new PerformanceMenu(kw);
        
	}
	
	@AfterMethod
	public void tearDowntest() throws InterruptedException {
		login = null;
		recruitment = null;
		performance = null;
		
		Thread.sleep(3000);
	
	}
	
	@Test
    @Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options vacancies")
    @Step("login and navigate to recruitment page, add vacancy for asst mananger")
    @Feature("recruitment Features")
    @Story("vacancies options")
	public void addVacancywithallDetailsAndCheckIfReflectsInCandidateField() throws InterruptedException {
		
		login.logMeIn();
		recruitment.clickOnRecruitmentMenu();
		recruitment.clickOnVacanciesMenu();
		recruitment.clickOnAddVacancyButton();
		recruitment.enterVacancyName("Marketing Asst Manager");
		recruitment.selectJobTitle("Assistant Manager");
		recruitment.selectHiringManager("Piyush");
		recruitment.enterNumberOfVacancy("1");
		recruitment.clickOnSaveVacancyButton();
		recruitment.clickOnVacanciesMenu();
		recruitment.clickOnVacancySearchButton();
		Assert.assertTrue(recruitment.checkVacancyAddedOrNot("Marketing Asst Manager"));
		recruitment.deleteEnteredVacancy("Marketing Asst Manager");
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options Candidate")
    @Step("login and navigate to recruitment page, add candidate for QA Engineer")
    @Feature("Recruitment > Candidates")
    @Story("Candidate options")
	public void AddsNewCandidateForAnExistingVacancy() throws InterruptedException, AWTException {
		
		login.logMeIn();
		recruitment.clickOnRecruitmentMenu();
		recruitment.clickOnCandidateMenu();
		recruitment.clickOnAddCandidateButton();
		recruitment.enterCandidateFullName("Sam", "Ron", "Miles");
		recruitment.clickOnOpenVacanciesList();
		recruitment.enterCandidateEmailID("samRMiles@gmail.com");
		recruitment.enterCandidateContactNumber("9558754562");
		recruitment.selectCandidateResume();
		recruitment.clickOnConcentClickBox();
		recruitment.clickOnSaveCandidateButton();
		Assert.assertTrue(recruitment.SaveToastMessageText());
		
	}
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("recruitment page options Candidate")
    @Step("login and navigate to recruitment page, add candidate without emailID")
    @Feature("Recruitment > Candidates")
    @Story("Candidate options")
	public void AddsNewCandidateForAnExistingVacancyWithoutEmail() throws InterruptedException, AWTException {
		
		login.logMeIn();
		recruitment.clickOnRecruitmentMenu();
		recruitment.clickOnCandidateMenu();
		recruitment.clickOnAddCandidateButton();
		recruitment.enterCandidateFullName("Sam", "Ron", "Miles");
		recruitment.clickOnOpenVacanciesList();
		recruitment.enterCandidateContactNumber("9558754562");
		recruitment.selectCandidateResume();
		recruitment.clickOnConcentClickBox();
		recruitment.clickOnSaveCandidateButton();
		Assert.assertTrue(recruitment.checkErrorMsg());
	}
	
	@Test(enabled = false)
	public void test() throws InterruptedException, AWTException {
		
		login.logMeIn();
		
	}
	
	
	@Test
	@Severity(SeverityLevel.NORMAL)
    @Description("performace options adding KPI for job title")
    @Step("login and navigate to performance page, add KPI")
    @Feature("Performance > Configure > KPI")
    @Story("Performance Options")
	public void addNewKPIForTheJobTitle() throws InterruptedException {
		
		login.logMeIn();
		performance.clickOnPerformanceMenu();
		performance.clickOnPerformanceConfigure();
		performance.clickOnKPIs();
		performance.searchResultKPIAndDelete("Bug Detection Rate");
		performance.clickOnPerformanceConfigure();
		performance.clickOnKPIs();
		performance.clickOnAddKPIButton();
		performance.enterKeyPerformanceIndicator("Bug Detection Rate");
		performance.selecrJobTitleToAddKPI();
		performance.enterMinAndMaxRatings();
		performance.clickOnSaveKPIButton();
		performance.clickOnPerformanceConfigure();
		Assert.assertTrue(performance.searchResultKPI("Bug Detection Rate"));
	}
	
	
	
	
	
	
	
	
	
	
	
}

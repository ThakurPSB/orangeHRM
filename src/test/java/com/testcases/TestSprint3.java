package com.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.LoginPage;
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
	
	@BeforeMethod
	public void pageSetup() {
        login = new LoginPage(kw); 
        recruitment = new RecruitmentMenu(kw);
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
	public void test() throws InterruptedException {
		
		login.logMeIn();
		recruitment.clickOnRecruitmentMenu();
		recruitment.clickOnVacanciesMenu();
		recruitment.clickOnVacancySearchButton();
		recruitment.checkVacancyAddedOrNot("Marketing Asst Manager");
		
		Thread.sleep(10000);
	}
	
	
	
}

package com.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.main.Keywords;

public class RecruitmentMenu {
	
private Keywords kw ;
	
	public RecruitmentMenu(Keywords kw) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = kw;
	}
	
	private static final Logger LOG = Logger.getLogger(LeaveEntitlementMenu.class);
	
	@FindBy(css="aside > nav > div.oxd-sidepanel-body > ul > li:nth-child(5) > a > span")
	WebElement recruitmentMenu ;

	public void clickOnRecruitmentMenu() {
		kw.waitForElementToBeClickable(recruitmentMenu);
		recruitmentMenu.click();
		LOG.info("Successfully clicked on recruitment Menu");
	}
	
	@FindBy(css="header > div.oxd-topbar-body > nav > ul > li:nth-child(2)")
	WebElement vacanciesMenu ;
	
	public void clickOnVacanciesMenu() {
		kw.waitForElementToBeClickable(vacanciesMenu);
		vacanciesMenu.click();
		LOG.info("Successfully clicked on vacancies Menu");
	}
	
	@FindBy(css="header > div.oxd-topbar-body > nav > ul > li:nth-child(1)")
	WebElement candidatesMenu ;
	
	public void clickOnCandidatesMenu() {
		kw.waitForElementToBeClickable(candidatesMenu);
		candidatesMenu.click();
		LOG.info("Successfully clicked on candidates Menu");
	}
	
	@FindBy(css="form > div:nth-child(1) > div:nth-child(2) > div > div:nth-child(2) > div > div")
	WebElement jobTitle ;
	
	public void selectJobTitle(String s) throws InterruptedException {
		kw.waitForElementToBeClickable(jobTitle);
		kw.scrollToElement(jobTitle);
		jobTitle.click();
		kw.normalWait(1000);
		List <WebElement> options = kw.getDriver().findElements(By.cssSelector("div>div.oxd-select-option[role='option']"));
		
		for(WebElement option : options) {
			if(option.getText().contains(s)) {
				option.click();
				LOG.info("successfully clicked on "+ s);	
				break;
			}
		}
	}
	
	@FindBy(css="div.oxd-layout-context > div > div.orangehrm-paper-container > div.orangehrm-header-container > button")
	WebElement addVacancyButton ;
	
	public void clickOnAddVacancyButton() {
		kw.waitForElementToBeClickable(addVacancyButton);
		addVacancyButton.click();
		LOG.info("Successfully clicked on add vacancy button");
	}
	
	@FindBy(css="form > div:nth-child(1) > div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement vacancyNameText ;
	
	public void enterVacancyName(String s) {
		kw.waitForElementToBeClickable(vacancyNameText);
		vacancyNameText.sendKeys(s);
		LOG.info("successfully entered the vacancy name "+s);
	}
	
	@FindBy(css="form > div:nth-child(3) > div:nth-child(1) > div > div:nth-child(2) > div > div > input")
	WebElement hiringManager ;
	
	@FindBy(css="form > div:nth-child(3) > div:nth-child(1) > div > div:nth-child(2) > div > div.oxd-autocomplete-dropdown.--positon-bottom > div:nth-child(1)")
	WebElement tempName ;
	
	public void selectHiringManager(String s) throws InterruptedException {
		kw.waitForElementToBeClickable(hiringManager);
		hiringManager.click();
		hiringManager.sendKeys(s);
		kw.normalWait(3000);
		tempName.click();
		//hiringManager.sendKeys(Keys.DOWN);
		LOG.info("successfully selected the hiring mananger");
		
	}
	
	@FindBy(css="form > div:nth-child(3) > div:nth-child(2) > div > div > div > div:nth-child(2) > input")
	WebElement numberOfVacancy ;
	
	public void enterNumberOfVacancy(String s) {
		kw.waitForElementToBeClickable(numberOfVacancy);
		numberOfVacancy.clear();
		numberOfVacancy.sendKeys(s);
		LOG.info("Successfully entered required vacancies");
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement saveVacancyButton ;
	
	public void clickOnSaveVacancyButton() throws InterruptedException {
		kw.waitForElementToBeVisible(saveVacancyButton);
		kw.scrollToElement(saveVacancyButton);
		saveVacancyButton.click();
		LOG.info("Successfully clicked on Save Vacancy");
		kw.normalWait(500);
	}
	
	
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(1) > div > div:nth-child(2) > div > div")
	WebElement vacancyJobTitleSearch ;
	
	public void clickOnJobTitleSearch(String s) throws InterruptedException {
		kw.waitForElementToBeClickable(vacancyJobTitleSearch);
		vacancyJobTitleSearch.click();
		kw.normalWait(1000);
		List <WebElement> options = kw.getDriver().findElements(By.cssSelector("div>div.oxd-select-option[role='option']"));
		
		for(WebElement option : options) {
			if(option.getText().contains(s)) {
				option.click();
				LOG.info("successfully clicked on "+ s);	
				break;
			}
		}
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement vacancySearchButton ;
	
	public void clickOnVacancySearchButton() {
		kw.waitForElementToBeClickable(vacancyJobTitleSearch);
		vacancySearchButton.click();
		LOG.info("Successfully Clicked on Vacancy Search Button");
	}
	
	
	
	@FindBy(css=".oxd-table-row")
	List<WebElement>  rows;
	
	@FindBy(css="button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	WebElement confirmDeleteButton ;

	public void deleteEnteredVacancy(String s) {
		kw.waitForAllElementAreVisible(rows);
		for(WebElement row: rows) {
			
			List <WebElement> cells = row.findElements(By.cssSelector(".oxd-table-cell"));
			
			if(cells.size()>1 && cells.get(1).getText().equalsIgnoreCase(s)) {
				row.findElement(By.cssSelector(".oxd-icon.bi-trash")).click();
				break;
			}
		}
		kw.waitForElementToBeClickable(confirmDeleteButton);
		confirmDeleteButton.click();
		LOG.info("Successfully deleted the skill "+s);
	}
	
	
	public boolean checkVacancyAddedOrNot(String s) {
		kw.waitForAllElementAreVisible(rows);
		boolean isVacancyFound = false;
		for(WebElement row : rows) {
			List <WebElement> cells = row.findElements(By.cssSelector(".oxd-table-cell"));
			for(int i=0; i<cells.size(); i++) {
				if(cells.get(1).getText().equalsIgnoreCase(s)) {
					isVacancyFound = true;
					LOG.info("Successfully found vacancy we added");
					break;
				}
			}
		}
		return isVacancyFound;
	}
	
//-----------------------------Candidate---------------------------------------------
	
	@FindBy(css="header > div.oxd-topbar-body > nav > ul > li.oxd-topbar-body-nav-tab.--visited > a")
	WebElement candidateMenu;
	
	public void clickOnCandidateMenu() {
		kw.waitForElementToBeClickable(candidatesMenu);
		candidateMenu.click();
		LOG.info("Successfully Clicked on Candidate Menu");
	}
	
	
	
	
	
	
	
}

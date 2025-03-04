package com.pages;


import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.main.Keywords;

public class EmployeeListMenu {
	
	public EmployeeListMenu() {
		PageFactory.initElements(Keywords.driver, this);
	}

	Keywords kw = new Keywords();
	Actions action = new Actions(Keywords.driver);
	private static final Logger LOG = Logger.getLogger(EmployeeListMenu.class);
	
	@FindBy(css="header > div.oxd-topbar-body > nav > ul > li.oxd-topbar-body-nav-tab.--visited > a")
	WebElement employeeListMenu ;
	
	/**
	 * click on employee list menu in PIM Menu
	 */
	public void clickOnEmployeeListMenu() {
		kw.waitForElementToBeClickable(employeeListMenu);
		employeeListMenu.click();
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(1) > div > div:nth-child(2) > div > div")
	WebElement employeeNameTextBox ;
	
	/**
	 * @param name enter the name of the employee to search
	 */
	public void enterEmployeeName(String name) {
		kw.waitForElementToBeVisible(employeeNameTextBox);
		employeeNameTextBox.sendKeys(name);
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(2) > div > div:nth-child(2) > input")
	WebElement employeeIDTextBox ;
	
	/**
	 * @param id enter the id to search
	 */
	public void enterEmployeeID(int id){
		kw.waitForElementToBeVisible(employeeIDTextBox);
		employeeIDTextBox.click();
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(3) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement employmentStatus;
	

	
	public void selectEmploymentStatus(String status) throws InterruptedException {
		
		
		kw.waitForElementToBeClickable(employmentStatus);
		
		if(status.equals("Active")) {
			employmentStatus.click();
			kw.normalWait(1000);
			employmentStatus.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			employmentStatus.sendKeys(Keys.ENTER);
			
		}else if(status.equals("Inactive")) {
			employmentStatus.click();
			kw.normalWait(1000);
			employmentStatus.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			employmentStatus.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			employmentStatus.sendKeys(Keys.ENTER);
		} else {
			LOG.info("invalid employment status");
		}
		action.sendKeys(Keys.TAB).perform();
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(4) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement include ;
	
	/**
	 * @param old for older employee, new for new employees and both for all employees.
	 * @throws InterruptedException
	 */
	public void includePastCurrentEmp(String oldnew) throws InterruptedException {
		kw.waitForElementToBeClickable(include);
		
		if(oldnew.equals("old")) {
			include.click();
			kw.normalWait(1000);
			include.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			include.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			include.sendKeys(Keys.ENTER);
		} else if( oldnew.equals("both")) {
			include.click();
			kw.normalWait(1000);
			include.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			include.sendKeys(Keys.ENTER);
		} else if(oldnew.equals("new")) {
			include.sendKeys(Keys.TAB);
		}
		include.sendKeys(Keys.TAB);
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(5) > div > div:nth-child(2) > div > div > input")
	WebElement supervisor ;
	
	/**
	 * @param name enter the supervisor name
	 */
	public void enterSupervisorName(String name) {
		kw.waitForElementToBeClickable(supervisor);
		supervisor.sendKeys(name);
	}
	
	
	@FindBy(css = "form > div.oxd-form-row > div > div:nth-child(6) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement jobTitleDropdown;

	/**
	 * @param jobTitleSeq option sequence 
	 * @throws InterruptedException
	 */
	public void selectJobTitle(int jobTitleSeq) throws InterruptedException {

		kw.waitForElementToBeVisible(jobTitleDropdown);
		jobTitleDropdown.click();
		for(int i=0; i<=jobTitleSeq-1; i++) {
			jobTitleDropdown.sendKeys(Keys.ARROW_DOWN);
		}
		jobTitleDropdown.sendKeys(Keys.TAB);
		
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(7) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement subUnit;
	
	/**
	 * @param seq select the option number for location or sub unit
	 */
	public void selectSubUnit(int seq) {
		kw.waitForElementToBeVisible(subUnit);
		subUnit.click();
		for(int i=0; i<=seq-1; i++) {
			subUnit.sendKeys(Keys.ARROW_DOWN);
		}
		subUnit.sendKeys(Keys.TAB);
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement SearchButton;
	
	/**
	 * click on the search button 
	 */
	public void clickOnSearchButton() {
		kw.waitForElementToBeVisible(SearchButton);
		SearchButton.click();
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--ghost")
	WebElement resetButton ;
	
	/**
	 * click on reset search option button
	 */
	public void clickOnResetButton() {
		kw.waitForElementToBeVisible(resetButton);
		resetButton.click();
	}
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div.orangehrm-paper-container > div.orangehrm-container > div > div.oxd-table-body > div:nth-child(1) > div")
	List<WebElement> tablerow ;
	
	/**
	 * row sequence - id, first name, last name, job title, Employment status, sub unit, supervisor
	 */
	public void readtablerow() {
		kw.waitForAllElementAreVisible(tablerow);
		for(WebElement row: tablerow) {
			System.out.println(row.getText());
		}
	}
	
}

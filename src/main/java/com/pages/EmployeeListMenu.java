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
	 * @throws InterruptedException 
	 */
	public void clickOnEmployeeListMenu() throws InterruptedException {
		kw.waitForElementToBeClickable(employeeListMenu);
		kw.normalWait(1000);
		employeeListMenu.click();
		LOG.info("Successfully clicked the button.");
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(1) > div > div:nth-child(2) > div > div > input")
	WebElement employeeNameTextBox ;
	
	/**
	 * @param name enter the name of the employee to search
	 */
	public void enterEmployeeName(String name) {
		kw.waitForElementToBeVisible(employeeNameTextBox);
		employeeNameTextBox.sendKeys(name);
		supervisor.sendKeys(Keys.TAB);
		LOG.info("Successfully entered the name in search name field.");

	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(2) > div > div:nth-child(2) > input")
	WebElement employeeIDTextBox ;
	
	/**
	 * @param id enter the id to search
	 */
	public void enterEmployeeID(int id){
		kw.waitForElementToBeVisible(employeeIDTextBox);
		employeeIDTextBox.click();
		employeeIDTextBox.sendKeys(String.valueOf(id));
		supervisor.sendKeys(Keys.TAB);
		LOG.info("Successfully entered the employee ID in search field.");
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
		LOG.info("Successfully selected employement status ."+ status );
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
		LOG.info("Successfully selected the option to include "+oldnew+ " type of employees");
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(5) > div > div:nth-child(2) > div > div > input")
	WebElement supervisor ;
	
	/**
	 * @param name enter the supervisor name
	 */
	public void enterSupervisorName(String name) {
		kw.waitForElementToBeClickable(supervisor);
		supervisor.sendKeys(name);
		supervisor.sendKeys(Keys.TAB);
		LOG.info("Successfully entered the supervisor name in search field.");
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
		LOG.info("Successfully selected job title");
		
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
		LOG.info("Successfully selected sub unit");
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement SearchButton;
	
	/**
	 * click on the search button 
	 * @throws InterruptedException 
	 */
	public void clickOnSearchButton() throws InterruptedException  {
		kw.waitForElementToBeVisible(SearchButton);
		kw.normalWait(500);
		SearchButton.click();
		LOG.info("Successfully clicked the Search button.");
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--ghost")
	WebElement resetButton ;
	
	/**
	 * click on reset search option button
	 * @throws InterruptedException 
	 */
	public void clickOnResetButton() throws InterruptedException {
		kw.waitForElementToBeVisible(resetButton);
		kw.normalWait(500);
		resetButton.click();
		LOG.info("Successfully clicked the reset button.");
	}
	
	
	@FindBy(css="div.oxd-table-body > div:nth-child(1) div.oxd-table-cell")
	List<WebElement> tablerow ;
	
	/**
	 * row sequence - id, first name, last name, job title, Employment status, sub unit, supervisor
	 */
	public String searchResultID() {
		kw.waitForAllElementAreVisible(tablerow);
		String text =  tablerow.get(1).getText();
		LOG.info("Successfully searched employee with Employee ID");
		return text;
	}
	
	public String searchResultfirstName() throws InterruptedException {
		kw.waitForAllElementAreVisible(tablerow);
		kw.normalWait(500);
		String text = tablerow.get(2).getText();
		LOG.info("Successfully searched employee with first name");
		return text;
	}
	
	public String searchResultLastName() throws InterruptedException {
		kw.waitForAllElementAreVisible(tablerow);
		kw.normalWait(500);
		String text = tablerow.get(3).getText();
		LOG.info("Successfully searched employee with last name");
		return text;
	}
	
	public String searchResultJobTitle() {
		kw.waitForAllElementAreVisible(tablerow);
		String text = tablerow.get(4).getText();
		LOG.info("Successfully searched employee with job title");
		return text;
	}
	
	public String searchResultEmploymentStatus() {
		kw.waitForAllElementAreVisible(tablerow);
		String text = tablerow.get(5).getText();
		LOG.info("Successfully searched employee with employement status");
		return text;
	}

	public String searchResultSubUnit() {
		kw.waitForAllElementAreVisible(tablerow);
		String text = tablerow.get(6).getText();
		LOG.info("Successfully searched employee with sub unit");
		return text;
	}
	
	public String searchResultSupervisor() {
		kw.waitForAllElementAreVisible(tablerow);
		String text = tablerow.get(7).getText();
		LOG.info("Successfully searched employee with supervisor name");
		return text;
	}
	
	public void readTable() {
		kw.waitForAllElementAreVisible(tablerow);
		for(WebElement row: tablerow) {
			LOG.info(row.getText());
		}
		
	}
	
	@FindBy(css="div > div.orangehrm-paper-container > div.orangehrm-header-container > button")
	WebElement addNewEmployeeButton ;
	
	public void clickOnAddNewEmployeeButton() {
		kw.waitForElementToBeClickable(addNewEmployeeButton);
		addNewEmployeeButton.click();
		LOG.info("Successfully clicked on add new employee button");
	}
	
	@FindBy(css="div.orangehrm-container > div > div.oxd-table-header > div > div:nth-child(1) > div > label")
	WebElement selectAll ;
	
	public void clickOnSelectAll() {
		kw.waitForElementToBeClickable(selectAll);
		kw.scrollToElement(selectAll);
		selectAll.click();
		LOG.info("Selected all the search results");
	}
	
	@FindBy(css="div.oxd-layout-context > div > div.orangehrm-paper-container > div:nth-child(2) > div > div > button")
	WebElement deleteSelectedButton ;
	
	@FindBy(css="button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	WebElement  confirmDelete;
	
	public void DeleteSelectedUsers() throws InterruptedException {
		kw.waitForElementToBeClickable(deleteSelectedButton);
		deleteSelectedButton.click();
		LOG.info("Successfully Clicked on delete button");
		kw.normalWait(500);
		kw.waitForElementToBeClickable(confirmDelete);
		confirmDelete.click();
		LOG.info("Successfully clicked on confirm delete button");
	}
	
	
}

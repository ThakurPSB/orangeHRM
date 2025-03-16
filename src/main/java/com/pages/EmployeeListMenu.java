package com.pages;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		LOG.info("Successfully clicked the Employee list Menu.");
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(1) > div > div:nth-child(2) > div > div > input")
	WebElement employeeNameTextBox ;
	
	/**
	 * @param name enter the name of the employee to search
	 */
	public void enterEmployeeName(String name) {
		kw.waitForElementToBeVisible(employeeNameTextBox);
		employeeNameTextBox.sendKeys(name);
		supervisor.sendKeys(Keys.ENTER);
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
		supervisor.sendKeys(Keys.ENTER);
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
		action.sendKeys(Keys.ENTER).perform();
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
			include.sendKeys(Keys.ENTER);
		}
		include.sendKeys(Keys.ENTER);
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
		supervisor.sendKeys(Keys.ENTER);
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
		for(int i=0; i<=jobTitleSeq; i++) {
			jobTitleDropdown.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(350);
		}
		
		jobTitleDropdown.sendKeys(Keys.ENTER);
		
		//for log message
		switch (jobTitleSeq) {
			case 1:
				LOG.info("Successfully selected job title Assistant Manager");
				break;
			case 2:
				LOG.info("Successfully selected job title Executive");
				break;
			case 3:
				LOG.info("Successfully selected job title HR Executive");
				break;
			case 4:
				LOG.info("Successfully selected job title HR Manager");
				break;
			case 5:
				LOG.info("Successfully selected job title HR Recruiter");
				break;
			case 6:
				LOG.info("Successfully selected job title Manager");
				break;
			case 7:
				LOG.info("Successfully selected job title TC");
				break;
			case 8:
				LOG.info("Successfully selected job title TL");
				break;
		}
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(7) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement subUnit;
	
	/**
	 * @param seq select the option number for location or sub unit
	 * @throws InterruptedException 
	 */
	public void selectSubUnit(int seq) throws InterruptedException {
		kw.waitForElementToBeVisible(subUnit);
		subUnit.click();
		kw.normalWait(500);
		for(int i=0; i<=seq; i++) {
			subUnit.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(300);
		}
		subUnit.sendKeys(Keys.ENTER);
		
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
		kw.scrollToElement(resetButton);
		kw.normalWait(500);
		resetButton.click();
		LOG.info("Successfully clicked the reset button.");
	}
	
	
	@FindBy(css="div.oxd-table-body > div:nth-child(1) div.oxd-table-cell")
	List<WebElement> tablerow ;
	
	public WebElement getTableRow(int index) {
	    List<WebElement> freshTableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div:nth-child(1) div.oxd-table-cell"));
	    if (index < freshTableRows.size()) {
	        return freshTableRows.get(index);
	    } else {
	        throw new IndexOutOfBoundsException("Table row index out of bounds");
	    }
	}
	
	/**
	 * row sequence - id, first name, last name, job title, Employment status, sub unit, supervisor
	 */
	public String searchResultID() {
		kw.waitForAllElementAreVisible(tablerow);
		WebElement id = tablerow.get(1);
		kw.scrollToElement(id);
		String text = id.getText();
		LOG.info("Successfully searched employee with Employee ID");
		return text;
	}
	
	public String searchResultfirstName() throws InterruptedException {
		kw.normalWait(200);
		kw.waitForAllElementAreVisible(tablerow);
		WebElement firstNameElement = tablerow.get(2);
		kw.scrollToElement(firstNameElement);
		String text = firstNameElement.getText();
		LOG.info("Successfully searched employee with first name");
		return text;
	}
	
	public String searchResultLastName() throws InterruptedException {
		kw.normalWait(200);
		kw.waitForAllElementAreVisible(tablerow);
		WebElement lastNameElement = tablerow.get(3);
		kw.scrollToElement(lastNameElement);
		String text = lastNameElement.getText();
		LOG.info("Successfully searched employee with last name");
		return text;
	}
	
	public boolean searchResultJobTitle(String jtitle) throws InterruptedException {
		if (kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement jobtitle = tablerow.get(4);
			kw.scrollToElement(jobtitle);
			String text = jobtitle.getText();
			LOG.info("Successfully searched employee with job title");
			return text.contains(jtitle);
		}else {
			return infoToastMessage();
		}
	}
	
	public boolean searchResultSubUnit(String location) throws InterruptedException {
		if (kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement subunit = tablerow.get(6);
			kw.scrollToElement(subunit);
			String text = subunit.getText();
			LOG.info("Successfully searched employee with sub unit");
			return text.contains(location);
		}else {
			return infoToastMessage();
		}
	}
	
	public String searchResultEmploymentStatus() throws InterruptedException {
		kw.waitForAllElementAreVisible(tablerow);
		kw.normalWait(500);
		WebElement status = tablerow.get(5);
		kw.scrollToElement(status);
		String text = status.getText();
		LOG.info("Successfully searched employee with employement status");
		return text;
	}

	
	public String searchResultSupervisor() throws InterruptedException {
		kw.waitForAllElementAreVisible(tablerow);
		kw.normalWait(500);
		WebElement supervis = tablerow.get(7);
		kw.scrollToElement(supervis);
		String text = supervis.getText();
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
	
	public void clickOnAddNewEmployeeButton() throws InterruptedException {
		kw.waitForElementToBeClickable(addNewEmployeeButton);
		kw.normalWait(200);
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
		kw.normalWait(200);
		deleteSelectedButton.click();
		LOG.info("Successfully Clicked on delete button");
		kw.normalWait(500);
		kw.waitForElementToBeClickable(confirmDelete);
		confirmDelete.click();
		LOG.info("Successfully clicked on confirm delete button");
	}
	
	@FindBy(css=".oxd-toast.oxd-toast--info.oxd-toast-container--toast")
	WebElement infoToast ;
	
	/**
	 * @return save successful toast message
	 */
	public boolean infoToastMessage() {
		kw.waitForElementToBeVisible(infoToast);
		kw.scrollToElement(infoToast);
		boolean isDisplayed = infoToast.isDisplayed();
		LOG.info("No records found for the search criteria");
		return isDisplayed;
		
	}
	
	@FindBy(css="oxd-table-card")
	WebElement tableCards ;
}

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
	 * @throws InterruptedException 
	 */
	public void enterEmployeeName(String name) throws InterruptedException {
		kw.waitForElementToBeVisible(employeeNameTextBox);
		employeeNameTextBox.click();
		employeeNameTextBox.sendKeys(name);
		kw.normalWait(200);
		employeeNameTextBox.sendKeys(Keys.ENTER);
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
		employeeIDTextBox.sendKeys(Keys.ENTER);
		LOG.info("Successfully entered the employee ID in search field.");
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(3) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement employmentStatus;
	

	
	public void selectEmploymentStatus(String status) throws InterruptedException {
		
		kw.waitForElementToBeClickable(employmentStatus);
		employmentStatus.click();
		while(!employmentStatus.getText().equals(status)) {
			employmentStatus.sendKeys(Keys.ARROW_DOWN);
		}
		employmentStatus.sendKeys(Keys.ENTER);
		LOG.info("Successfully selected employement status - "+ status );
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
			include.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			include.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			include.sendKeys(Keys.ENTER);
		} else if( oldnew.equals("both")) {
			include.click();
			include.sendKeys(Keys.ARROW_DOWN);
			kw.normalWait(500);
			include.sendKeys(Keys.ENTER);
			kw.normalWait(500);
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
	 * @throws InterruptedException 
	 */
	public void enterSupervisorName(String name) throws InterruptedException {
		kw.waitForElementToBeClickable(supervisor);
		supervisor.click();
		supervisor.sendKeys(name);
		kw.normalWait(1000);
		supervisor.sendKeys(Keys.ARROW_DOWN);
		supervisor.sendKeys(Keys.ENTER);
		supervisor.sendKeys(Keys.TAB);
		LOG.info("Successfully entered the supervisor name in search field "+name);
	}
	
	
	@FindBy(css = "form > div.oxd-form-row > div > div:nth-child(6) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement jobTitleDropdown;

	/**
	 * @param jobTitleSeq option sequence 
	 * @throws InterruptedException
	 */
	public void selectJobTitle(String job) {
		kw.waitForElementToBeVisible(jobTitleDropdown);
		jobTitleDropdown.click();
		while(!jobTitleDropdown.getText().equals(job)) {
			jobTitleDropdown.sendKeys(Keys.ARROW_DOWN);
		}
		jobTitleDropdown.sendKeys(Keys.ENTER);
		LOG.info("Successfully selected job title - "+ job);
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(7) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement subUnit;
	
	/**
	 * @param seq select the option number for location or sub unit
	 * @throws InterruptedException 
	 */
	public void selectSubUnit(String unit) {
		kw.waitForElementToBeVisible(subUnit);
		subUnit.click();
		while(!subUnit.getText().equals(unit)) {
			subUnit.sendKeys(Keys.ARROW_DOWN);
		}
		subUnit.sendKeys(Keys.ENTER);
		LOG.info("Successfully selected sub unit - " + unit);
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement SearchButton;
	
	/**
	 * click on the search button 
	 * @throws InterruptedException 
	 */
	public void clickOnSearchButton() throws InterruptedException  {
		kw.waitForElementToBeVisible(SearchButton);
		kw.normalWait(1000);
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
	public boolean searchResultID(String ids) {
		if(kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement id = tablerow.get(1);
			kw.scrollToElement(id);
			String text = id.getText();
			LOG.info("Successfully searched employee with Employee ID "+text);
			return text.equals(ids);
		}else {
			return infoToastMessage();
		}
		
	}
	
	public boolean searchResultfirstName(String ln) {
		if(kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement firstNameElement = tablerow.get(2);
			kw.scrollToElement(firstNameElement);
			String text = firstNameElement.getText();
			LOG.info("Successfully searched employee with first name "+ text);
			return text.contains(ln);
		}else {
			return infoToastMessage();
		}
	}
	
	public boolean searchResultLastName(String nm) {
		if(kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement lastNameElement = tablerow.get(3);
			kw.scrollToElement(lastNameElement);
			String text = lastNameElement.getText();
			LOG.info("Successfully searched employee with last name "+text);
			return text.contains(nm);
		}else {
			return infoToastMessage();
		}
		
	}
	
	public boolean searchResultJobTitle(String jtitle) {
		if (kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement jobtitle = tablerow.get(4);
			kw.scrollToElement(jobtitle);
			String text = jobtitle.getText();
			LOG.info("Successfully searched employee with job title "+ text);
			return text.contains(jtitle);
		}else {
			return infoToastMessage();
		}
	}
	
	public boolean searchResultSubUnit(String location) {
		if (kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement subunit = tablerow.get(6);
			kw.scrollToElement(subunit);
			String text = subunit.getText();
			LOG.info("Successfully searched employee with sub unit "+ text);
			return text.contains(location);
		}else {
			return infoToastMessage();
		}
	}
	
	public boolean searchResultEmploymentStatus(String status)  {
		if(kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement temp = tablerow.get(5);
			kw.scrollToElement(temp);
			String text = temp.getText();
			LOG.info("Successfully searched employee with employement status "+ text);
			return text.contains(status);
		}else {
			return infoToastMessage();
		}
	}

	
	public boolean searchResultSupervisor(String supervisor)  {
		if(kw.isElementListPresent(tablerow)) {
			kw.waitForAllElementAreVisible(tablerow);
			WebElement supervis = tablerow.get(7);
			kw.scrollToElement(supervis);
			String text = supervis.getText();
			System.out.println(text);
			LOG.info("Successfully searched employee with supervisor name "+ text);
			return text.contains(supervisor);
		}else {
			return infoToastMessage();
		}
		
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

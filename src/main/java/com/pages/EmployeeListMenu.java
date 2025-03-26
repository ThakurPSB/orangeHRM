package com.pages;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class EmployeeListMenu {
	
	private Keywords kw;
	public EmployeeListMenu(Keywords kw) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = kw;
	}

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
		LOG.info("Clicked the Employee list Menu.");
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
		LOG.info("Entered the name in search name field.");

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
		LOG.info("Entered the employee ID in search field.");
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
		LOG.info("Selected employement status - "+ status );
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
		LOG.info("Selected the option to include "+oldnew+ " type of employees");
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(5) > div > div:nth-child(2) > div > div > input")
	WebElement supervisor ;
	
	
	@FindBy(css = ".oxd-autocomplete-option")  
	private List<WebElement> suggestionsList;
	
	/**
	 * @param name enter the supervisor name
	 * @throws InterruptedException 
	 */
	public void enterSupervisorName(String name) throws InterruptedException {
		kw.waitForElementToBeVisible(supervisor);
		supervisor.click();
		supervisor.sendKeys(name);
		kw.normalWait(3000);
		kw.waitForAllElementAreVisible(suggestionsList);
		supervisor.sendKeys(Keys.ARROW_DOWN);

		if (!suggestionsList.isEmpty()) {
	        suggestionsList.get(0).click();
	        LOG.info("Selected Supervisor: " + name);
	    } else {
	        LOG.info("No suggestions found for: " + name);
	    }

	    // Ensure the selection is registered
	    supervisor.sendKeys(Keys.ENTER);
		
	    LOG.info("Entered the supervisor name in search name field.");
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
		LOG.info("Selected job title - "+ job);
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
		LOG.info("Selected sub unit - " + unit);
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
		LOG.info("Clicked the Search button.");
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
		LOG.info("Clicked the reset button.");
	}
	
	
	@FindBy(css="div.oxd-table-body > div:nth-child(1) div.oxd-table-cell")
	List<WebElement> tablerow ;
	
	/**
	 * Gets the fresh element css selector reference to avoid stale element error
	 * @param index
	 * @return
	 */
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
	    // Check for toast message first
	    if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return true;
	    }
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));

	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement idElement = getTableRow(1); 
	        action.moveToElement(idElement).perform();
	        String text = idElement.getText();
	        LOG.info("Successfully Searched employee with Employee ID: " + text);
	        return text.equals(ids);
	    } else {
	        return false;
	    }
		
	}
	
	public boolean searchResultfirstName(String ln) {
		// Check for toast message first
	    if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return true;
	    }
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));

	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(2); 
	        kw.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched employee with first name: " + text);
	        return text.equals(ln);
	    } else {
	        return false;
	    }
		
	}
	
	public boolean searchResultLastName(String nm) {
		// Check for toast message first
	    if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return true;
	    }
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));

	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(3); 
	        kw.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched employee with last name: " + text);
	        return text.equals(nm);
	    } else {
	        return false;
	    }
		
	}
	
	public boolean searchResultJobTitle(String jtitle) {
		// Check for toast message first
	    if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return true;
	    }
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));

	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(4); 
	        kw.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched employee with job title: " + text);
	        return text.equals(jtitle);
	    } else {
	        return false;
	    }
	}
	
	public boolean searchResultSubUnit(String location) {
		// Check for toast message first
	    if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return true;
	    }
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));

	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(6); 
	        kw.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched employee with location: " + text);
	        return text.equals(location);
	    } else {
	        return false;
	    }
	}
	
	public boolean searchResultEmploymentStatus(String status)  {
		// Check for toast message first
	    if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return true;
	    }
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));

	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(5); 
	        kw.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched employee with status: " + text);
	        return text.equals(status);
	    } else {
	        return false;
	    }
	}

	
	public boolean searchResultSupervisor(String supervisor)  {
		// Check for toast message first
	    if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return true;
	    }
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));

	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(7); 
	        action.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched employee with supervisor name: " + text);
	        return text.equals(supervisor);
	    } else {
	        return false;
	    }
	}
	
	public void readTable() {
		kw.waitForAllElementAreVisible(tablerow);
		for(WebElement row: tablerow) {
			LOG.info("Reading empoloyee list table " +row.getText());
		}
		
	}
	
	@FindBy(css="div > div.orangehrm-paper-container > div.orangehrm-header-container > button")
	WebElement addNewEmployeeButton ;
	
	public void clickOnAddNewEmployeeButton() throws InterruptedException {
		kw.waitForElementToBeClickable(addNewEmployeeButton);
		kw.normalWait(200);
		addNewEmployeeButton.click();
		LOG.info("Clicked on add new employee button");
	}
	
	@FindBy(css="div.orangehrm-container > div > div.oxd-table-header > div > div:nth-child(1) > div > label")
	WebElement selectAll ;
	
	public boolean clickOnSelectAll() {
		if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return false;
	    }else {
			kw.waitForElementToBeClickable(selectAll);
			kw.scrollToElement(selectAll);
			selectAll.click();
			LOG.info("Selected all the search results");
			return true;
	    }
	}
	
	@FindBy(css="div.oxd-layout-context > div > div.orangehrm-paper-container > div:nth-child(2) > div > div > button")
	WebElement deleteSelectedButton ;
	
	@FindBy(css="button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	WebElement  confirmDelete;
	
	public void DeleteSelectedUsers() throws InterruptedException {
		
		if(clickOnSelectAll()) {
			kw.waitForElementToBeClickable(deleteSelectedButton);
			kw.normalWait(200);
			deleteSelectedButton.click();
			LOG.info("Successfully Clicked on delete button");
			kw.normalWait(500);
			kw.waitForElementToBeClickable(confirmDelete);
			confirmDelete.click();
			LOG.info("Clicked on confirm delete button");
		}
		kw.waitInvisibilityOfElement(confirmDelete);
	}
	
	@FindBy(css=".oxd-toast.oxd-toast--info.oxd-toast-container--toast")
	WebElement infoToast ;
	
	/**
	 * @return save successful toast message
	 */
	public boolean infoToastMessage() {
		try {
			kw.waitForElementToBeVisibleShort(infoToast,5);
			kw.scrollToElement(infoToast);
			boolean isDisplayed = infoToast.isDisplayed();
			LOG.info("No records found for the search criteria");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("No match found - Toast not found in DOM.");
	    }
		return false;
		
	}
	
	@FindBy(css="oxd-table-card")
	WebElement tableCards ;
}

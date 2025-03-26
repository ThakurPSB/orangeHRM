package com.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class AdminMenu {
	
	private Keywords kw;
	
	public AdminMenu(Keywords kw) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = kw;
	}
	
	private static final Logger LOG = Logger.getLogger(AdminMenu.class);
	
	@FindBy(css="div.oxd-sidepanel-body > ul > li:nth-child(1) > a > span")
	WebElement adminMenu ;
	
	/**
	 * clicks on Admin menu of sidebar
	 */
	public void clickOnAdminMenu() {
		kw.waitForElementToBeVisible(adminMenu);
		adminMenu.click();
		LOG.info("Cliked on Admin Menu");
	}
	
	@FindBy(css="div.oxd-layout.orangehrm-upgrade-layout  div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement usernametextbox ;
	
	public void enterUsername(String user) {
		kw.waitForElementToBeClickable(usernametextbox);
		usernametextbox.sendKeys(user);
		LOG.info("entered the username in system user search  menu");
	}
	
	@FindBy(css="div.orangehrm-paper-container > div.orangehrm-container > div > div.oxd-table-body > div:nth-child(1) > div")
	List <WebElement> searchResults ;
	
	/**
	 * Gets the fresh element css selector reference to avoid stale element error
	 * @param index
	 * @return
	 */
	public WebElement getTableRow(int index) {
	    List<WebElement> freshTableRows = kw.getDriver().findElements(By.cssSelector(".oxd-table-row.oxd-table-row--with-border:first-of-type .oxd-table-cell"));
	    if (index < freshTableRows.size()) {
	        return freshTableRows.get(index);
	    } else {
	        throw new IndexOutOfBoundsException("Table row index out of bounds");
	    }
	}
	
	/**
	 * row sequence - id, first name, last name, job title, Employment status, sub unit, supervisor
	 */
	public boolean searchResultUsername(String userNm) {
		// Check for toast message first
	    if (infoToastMessage()) {
	        LOG.info("Toast message found. No records available.");
	        return true;
	    }
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));

	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(1); 
	        kw.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched employee with  username: " + text);
	        return text.equals(userNm);
	    } else {
	        return false;
	    }
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement userRole ;
	
	public void selectUserRole(String role) {
		kw.waitForElementToBeClickable(userRole);
		userRole.click();
		while(!userRole.getText().equals(role)) {
			userRole.sendKeys(Keys.ARROW_DOWN);
		}
		userRole.sendKeys(Keys.ENTER);
		LOG.info("Selected the user role as "+role);
	}
	
	public boolean searchResultRole(String rol) {
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
	        LOG.info("Successfully Searched employee with role: " + text);
	        return text.equals(rol);
	    } else {
	        return false;
	    }
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
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement SearchButton;
	
	/**
	 * click on the search button 
	 * @throws InterruptedException 
	 */
	public void clickOnSearchButton() throws InterruptedException  {
		kw.waitForElementToBeVisible(SearchButton);
		kw.scrollToElement(SearchButton);
		kw.waitForElementToBeClickable(SearchButton);
		SearchButton.click();
		LOG.info("Clicked the Search button.");		
		kw.normalWait(500);
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
		kw.waitForElementToBeClickable(resetButton);
		resetButton.click();
		LOG.info("Clicked the reset button.");
	}
	
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(3) > div > div:nth-child(2) > div > div > input")
	WebElement employeeNameTextbox ;
	
	@FindBy(css = ".oxd-autocomplete-option")  
	private List<WebElement> suggestionsList;
	
	public void enterEmployeeName(String user) throws InterruptedException {
		
		kw.waitForElementToBeVisible(employeeNameTextbox);
		employeeNameTextbox.click();
		employeeNameTextbox.sendKeys(user);
		kw.normalWait(3000);
		kw.waitForAllElementAreVisible(suggestionsList);
		employeeNameTextbox.sendKeys(Keys.ARROW_DOWN);

		if (!suggestionsList.isEmpty()) {
	        suggestionsList.get(0).click();
	        LOG.info("Selected employee: " + user);
	    } else {
	        LOG.info("No suggestions found for: " + user);
	    }

	    // Ensure the selection is registered
		employeeNameTextbox.sendKeys(Keys.ENTER);
		
	    LOG.info("Entered the employee name in search name field.");
	
		
	}
	
	public boolean searchResultsEmployeeName(String nm) {
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
	        LOG.info("Successfully Searched employee with employee name: " + text);
	        return text.equals(nm);
	    } else {
	        return false;
	    }

	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(4) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement  status;
	
	public void selectLoginStatus(String state) {
		kw.waitForElementToBeClickable(status);
		status.click();
		while(!status.getText().equals(state)) {
			status.sendKeys(Keys.ARROW_DOWN);
		}
		status.sendKeys(Keys.ENTER);
		LOG.info("Selected the user role as "+state);
	}
	
	
	public boolean searchResultStatus(String state) {
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
	        LOG.info("Successfully Searched employee with status: " + text);
	        return text.equals(state);
	    } else {
	        return false;
	    }
	}
	
	
}

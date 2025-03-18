package com.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class AdminMenu {
	
	public AdminMenu() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
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
		LOG.info("entered the username in system user seach  menu");
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
		if(!kw.isElementListPresent(searchResults)) {
			return infoToastMessage();
		}else {
			kw.waitForAllElementAreVisible(searchResults);
			WebElement uname = getTableRow(1);
			kw.scrollToElement(uname);
			String text = uname.getText();
			LOG.info("Searched employee with user name "+ text);
			return text.equals(userNm);
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
		if(!kw.isElementListPresent(searchResults)) {
			return infoToastMessage();
		}else {
			kw.waitForAllElementAreVisible(searchResults);
			WebElement role = getTableRow(2);
			kw.scrollToElement(role);
			String text = role.getText();
			LOG.info("Searched employee with role type "+ text);
			return text.equals(rol);
		}
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
		kw.normalWait(500);
		resetButton.click();
		LOG.info("Clicked the reset button.");
	}
	
	
}

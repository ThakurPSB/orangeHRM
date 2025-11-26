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

public class LeaveEntitlementMenu {
	
	//This variable allows Page object to use the keywords methods
	private Keywords kw;

	/*
	 * Constructor to initialize the PageFactory elements
	 * @param kw - Keywords class reference
	 * this.kw = key means Take the Keywords object passed into the constructor and 
	 * assign it to the page object’s private field, 
	 * so this page object can use it later.
	 */
	
	public LeaveEntitlementMenu(Keywords key) {
		this.kw = key;
	    PageFactory.initElements(kw.getDriver(), this);
	}
	
	private static final Logger LOG = Logger.getLogger(LeaveEntitlementMenu.class);
	
	
	@FindBy(css="header > div.oxd-topbar-body > nav > ul > li:nth-child(3) > span")
	WebElement leaveEntitlementDropdown ;
	
	public void clickOnLeaveEntitlementMenu() {
		kw.waitForElementToBeClickable(leaveEntitlementDropdown);
		leaveEntitlementDropdown.click();
		LOG.info("Successfully clicked on Leave Entitlement");
	}
	
	@FindBy(css="li:nth-child(1)>[role='menuitem']")
	WebElement addEntitlementMenu ;
	
	public void clickOnAddEntitlement() {
		kw.waitForElementToBeClickable(addEntitlementMenu);
		addEntitlementMenu.click();
		LOG.info("Successfully clicked on Add Entitlement button");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div > div > div:nth-child(2) > div > div > input")
	WebElement employeeNameTextBox ;
	
	public void enterEmployeeName(String s) throws InterruptedException {
		kw.waitForElementToBeVisible(employeeNameTextBox);
		employeeNameTextBox.click();
		employeeNameTextBox.sendKeys(s);
		kw.normalWait(3000);
		employeeNameTextBox.sendKeys(Keys.ARROW_DOWN);
		employeeNameTextBox.sendKeys(Keys.ENTER);
		LOG.info("Successfully entered employee name as "+s);
	}
	
	@FindBy(css="span.oxd-radio-input")
	List<WebElement> addToIndividualOrMultiple ;
	
	public void selectIndividualOrMultipleEmployee(String s) {
		kw.waitForAllElementAreVisible(addToIndividualOrMultiple);
		if(s.equalsIgnoreCase("individual")) {
			addToIndividualOrMultiple.get(0).click();
			LOG.info("Successfully selected the "+s);
		}else if(s.equalsIgnoreCase("multiple")) {
			addToIndividualOrMultiple.get(1).click();
			LOG.info("Successfully selected the "+s);
		}else {
			LOG.error("Incorrect input selected");
		}
	}
	
	
	@FindBy(css="form > div:nth-child(3) > div > div:nth-child(1) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement leaveType ;
	
	
	public void selectLeaveType(String s) throws InterruptedException {
		kw.waitForElementToBeClickable(leaveType);
		leaveType.click();
		kw.normalWait(1000);
		List <WebElement> types = kw.getDriver().findElements(By.cssSelector("div.oxd-select-dropdown div.oxd-select-option"));
		for(WebElement type: types) {
			if(type.getText().contains(s)) {
				type.click();
				break;
			}
		}
		LOG.info("successfully selected leave type");
	}
	
	@FindBy(css="form > div:nth-child(3) > div > div:nth-child(3) > div > div:nth-child(2) > input")
	WebElement entitlement ;
	
	public void enterEntitlement(String s) {
		kw.waitForElementToBeClickable(entitlement);
		entitlement.click();
		entitlement.sendKeys(s);
		LOG.info("Successfully updated the entitlement leave - "+s);
	}
	
	@FindBy(css="div>button[type='submit']")
	WebElement submitButton ;
	
	public void clickOnSaveButton() {
		kw.waitForElementToBeClickable(submitButton);
		submitButton.click();
		LOG.info("Successfully clicked on save button");
	}
	
	
	@FindBy(css="div>button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-button-margin")
	WebElement confirmAdd ;
	
	public void clickOnConfirmButton() {
		kw.waitForElementToBeClickable(confirmAdd);
		confirmAdd.click();
		LOG.info("Clicked on Confirm add entitlement");
	}

	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successful toast message
	 * @throws TimeoutException 
	 */
	public boolean SaveToastMessageText() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(saveSuccessfullToast,10);
			kw.scrollToElement(saveSuccessfullToast);
			boolean isDisplayed = saveSuccessfullToast.isDisplayed();
			LOG.info("Successfully added the leave for employee");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
	}
	
	
	//----------------------------------------------------------------
	
	@FindBy(css="li:nth-child(2)>[role='menuitem']")
	WebElement employeeEntitlements ;
	
	public void clickEmployeeEntitlement() {
		kw.waitForElementToBeClickable(employeeEntitlements);
		employeeEntitlements.click();
		LOG.info("Successfully clicked on employee Entitlements button");
	}
	
	@FindBy(css="div.oxd-autocomplete-text-input input")
	WebElement enterEmployeeNameEntitlement ;
	
	@FindBy(css = ".oxd-autocomplete-dropdown > div") // This matches the dropdown items
	List<WebElement> dropdownOptions;
	
	public void enterEmployeeNameInSearchBoxEmplpyeeEntitlement(String s) throws InterruptedException {
		kw.waitForElementToBeVisible(enterEmployeeNameEntitlement);
		enterEmployeeNameEntitlement.click();
		enterEmployeeNameEntitlement.sendKeys(s);
		kw.normalWait(3000);
		enterEmployeeNameEntitlement.sendKeys(Keys.ARROW_DOWN);
		enterEmployeeNameEntitlement.sendKeys(Keys.ENTER);
		LOG.info("Successfully entered employee name as "+s);
	}

	// add method for select leave type here for this page as well
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement leaveTypeSelectEntitlement ;
	
	public void selectLeaveTypeInEntitlement(String s) throws InterruptedException {
		kw.waitForElementToBeClickable(leaveTypeSelectEntitlement);
		leaveTypeSelectEntitlement.click();
		kw.normalWait(1000);
		List <WebElement> types = kw.getDriver().findElements(By.cssSelector("div>div.oxd-select-option"));
		for(WebElement type: types) {
			if(type.getText().contains(s)) {
				type.click();
				break;
			}
		}
		LOG.info("successfully selected leave type");
	}
	
	
	
	@FindBy(css="form > div.oxd-form-actions > button")
	WebElement searchButton ;
	
	public void clickOnSearchButton() {
		kw.waitForElementToBeClickable(searchButton);
		searchButton.click();
		LOG.info("Successfully clicked on Search Button");
	}
	
	@FindBy(css="div>div.orangehrm-container>div>div:nth-child(2)>div>div>div:nth-child(6)")
	WebElement leaveBalance ;
	
	public double CheckLeaveBalance() {
		kw.waitForElementToBeVisible(leaveBalance);
		String s = leaveBalance.getText();
		LOG.info("LeaveBalance Checked: "+s);
		return Double.parseDouble(s);
	}
	
	
	
}


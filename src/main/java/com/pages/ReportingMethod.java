package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import com.main.Keywords;

public class ReportingMethod {

	public ReportingMethod() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	private static final Logger LOG = Logger.getLogger(ReportingMethod.class);
	
	@FindBy(css="nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(4) > a")
	WebElement reportingMethodMenu ;
	
	/**
	 * clicks on reporting Method menu in configuration menu
	 */
	public void clickOnReportingMethod() {
		kw.waitForElementToBeClickable(reportingMethodMenu);
		reportingMethodMenu.click();
		LOG.info("Successfully clicked on Reporting Method");
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div.orangehrm-header-container > div > button")
	WebElement AddReportingMethod ;
	
	/**
	 * click on ADD button in reporting method menu
	 */
	public void AddReportingMethodButton() {
		kw.waitForElementToBeClickable(AddReportingMethod);
		kw.scrollToElement(AddReportingMethod);
		AddReportingMethod.click();
		LOG.info("Successfully clicked on Add Reporting Method Button ");
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > form > div.oxd-form-row > div > div:nth-child(2) > input")
	WebElement reportingMethodName;
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement reportingMethodSaveButton ;
	
	@FindBy(css="div.oxd-layout-context > div > div > form > div.oxd-form-row > div > span")
	WebElement alreadyExistError ;
	
	public void addrReportingMethod(String nm) {
		kw.waitForElementToBeClickable(reportingMethodName);
		reportingMethodName.sendKeys(nm);
		if(alreadyExistError.isDisplayed()) {
			LOG.error("Reporing Method already Exists");
		}else {
			reportingMethodSaveButton.click();
			LOG.info("Successfully entered the reporting method in text box");
		}
	}
	
	public boolean checkAssertReportingMethod() {
		if(alreadyExistError.isDisplayed()) {
			LOG.error("Reporing Method already Exists");
			return true;
		}else {
			return SaveToastMessageText();
		}
	}
	
	@FindBy(css="div.oxd-table-row")
	List<WebElement> reportingMethodList;
	
	@FindBy(css="i.oxd-icon.bi-trash.oxd-button-icon")
	WebElement confirmDeleteYes ;
	
	public void deleteSelectedReportingMethod(String name) throws InterruptedException {
		kw.waitForAllElementAreVisible(reportingMethodList);
		boolean isMethodFound = false;
		WebElement found = null;
		for(WebElement row: reportingMethodList) {
			if(row.getText().contains(name)) {
				isMethodFound = true;
				found = row;
				break;
			}else {
				isMethodFound = false;
			}
		}
		 	
		if(isMethodFound) {
			LOG.info("Found the reporting Method");
			WebElement deleteButton = found.findElement(By.cssSelector("button.oxd-icon-button > i.oxd-icon.bi-trash"));
			deleteButton.click();
			if(!errorToastMessageText()) {
				kw.waitForElementToBeClickable(confirmDeleteYes);
		        confirmDeleteYes.click();
		        LOG.info(name+" Reporing option deleted");
			}else {
				LOG.error("The reporting method already in use");
			}
		}else {
			LOG.info("No such method found");
		}
	}
	
	
	@FindBy(css=".oxd-toast.oxd-toast--error.oxd-toast-container--toast")
	WebElement errorToast ;
	
	public boolean errorToastMessageText() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(errorToast,5);
			kw.scrollToElement(errorToast);
			boolean isDisplayed = errorToast.isDisplayed();
			LOG.info("Error toast found "+errorToast.getText());
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
	}
	
	
	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successful toast message
	 * @throws TimeoutException 
	 */
	public boolean SaveToastMessageText() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(saveSuccessfullToast,5);
			kw.scrollToElement(saveSuccessfullToast);
			boolean isDisplayed = saveSuccessfullToast.isDisplayed();
			LOG.info("Successfully added the employee");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
		
		
	}
	
	
	
	
	
	
	
	
}

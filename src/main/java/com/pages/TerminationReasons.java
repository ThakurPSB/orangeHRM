package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import com.main.Keywords;

public class TerminationReasons {
	
	private Keywords kw;
	public TerminationReasons(Keywords kw) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = kw;
	}
	
	private static final Logger LOG = Logger.getLogger(TerminationReasons.class);
	
	@FindBy(css="nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(5) > a")
	WebElement TerminationReasons ;
	
	/**
	 * click on the Termination Reasons field in configuration menu
	 */
	public void clickOnTerminationReasonsMenu() {
		kw.waitForElementToBeClickable(TerminationReasons);
		TerminationReasons.click();
		LOG.info("Successfully click on the Termination Reason Menu");
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div.orangehrm-header-container > div > button")
	WebElement AddTerminationReasonButton ;
	
	/**
	 * click on the add button in termination reason menu
	 */
	public void ClickOnAddTerminationReasonButton() {
		
		kw.waitForElementToBeClickable(AddTerminationReasonButton);
		kw.scrollToElement(AddTerminationReasonButton);
		AddTerminationReasonButton.click();
		LOG.info("Successfully clicked on the Add Termindation reason button");
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(2) > input")
	WebElement EnterTerminationReason ;
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement AddTerminationReasonSaveButton ;
	
	@FindBy(css="div.oxd-layout-context > div > div > form > div.oxd-form-row > div > span")
	WebElement alreadyExistError ;
	
	/**
	 * @param description of reason. 
	 * Add the reason in list of termination list.
	 */
	public void AddTerminationReason(String name) {
		kw.waitForElementToBeClickable(AddTerminationReasonSaveButton);
		kw.waitForElementToBeClickable(EnterTerminationReason);
		EnterTerminationReason.sendKeys(name);
		EnterTerminationReason.sendKeys(Keys.TAB);
		if(isAlreadyExistErrorPresent()) {
			LOG.error("Termination reason already Exists");
		}else {
			AddTerminationReasonSaveButton.click();
			LOG.info("Successfully added the termination reason");
		}
	}
	
	public boolean isAlreadyExistErrorPresent() {
		List<WebElement> errorElements = kw.getDriver().findElements(By.cssSelector("div.oxd-layout-context > div > div > form > div.oxd-form-row > div > span"));
	    return !errorElements.isEmpty();
	}
	
	
	public boolean checkAssertTerminationReason() {
		if(isAlreadyExistErrorPresent()) {
			LOG.error("Termination reason already Exists");
			return true;
		}else {
			return SaveToastMessageText();
		}
	}
	
	@FindBy(css="div.oxd-table-row")
	List<WebElement> TerminationReasonList ;
	
	@FindBy(css="i.oxd-icon.bi-trash.oxd-button-icon")
	WebElement confirmDeleteYes ;
	
	@FindBy(css="i.oxd-icon.bi-trash-fill.oxd-button-icon")
	WebElement deleteButton ;
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
	WebElement confirmDelete ;
	
	public void deleteSelectedTerminationReason(String name) throws InterruptedException {
		kw.waitForAllElementAreVisible(TerminationReasonList);
		boolean isReasonFound = false;
		WebElement found = null;
		
		for(WebElement row: TerminationReasonList) {
			if(row.getText().contains(name)) {
				isReasonFound = true;
				found = row;
				break;
			}else {
				isReasonFound = false;
			}
		}
		if(isReasonFound) {
			LOG.info("Found the Termination Reason");
			WebElement deleteButton = found.findElement(By.cssSelector("button.oxd-icon-button > i.oxd-icon.bi-trash"));
			deleteButton.click();
			if(!errorToastMessageText()) {
				kw.waitForElementToBeClickable(confirmDeleteYes);
		        confirmDeleteYes.click();
		        LOG.info(name+" - Termination option deleted");
			}else {
				LOG.error("The Termination reason already in use");
			}
		}else {
			LOG.info("No such Termination reason found.");
		}
		kw.waitInvisibilityOfElement(confirmDeleteYes);
	}
	
	@FindBy(css=".oxd-toast.oxd-toast--error.oxd-toast-container--toast")
	WebElement errorToast ;
	
	public boolean errorToastMessageText() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(errorToast,5);
			kw.scrollToElement(errorToast);
			boolean isDisplayed = errorToast.isDisplayed();
			LOG.info("Error toast found ");
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
	 */
	public boolean SaveToastMessageText() {
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

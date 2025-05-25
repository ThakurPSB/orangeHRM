package com.pages;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class LeaveApplyMenu {
	
	private Keywords kw;
	public LeaveApplyMenu(Keywords kw) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = kw;
	}
	
	private static final Logger LOG = Logger.getLogger(LeaveApplyMenu.class);
	
	@FindBy(css="li.oxd-main-menu-item-wrapper:nth-of-type(3)")
	WebElement leaveMenu ;
	
	public void clickOnLeaveMenu() {
		kw.waitForElementToBeClickable(leaveMenu);
		kw.scrollToElement(leaveMenu);
		leaveMenu.click();
		LOG.info("Successfully clicked on Leave Menu");
	}
	
	@FindBy(css="header > div.oxd-topbar-body > nav > ul > li:nth-child(1) > a")
	WebElement applyLeave ;
	
	public void clickOnApplyLeave() {
		kw.waitForElementToBeClickable(applyLeave);
		applyLeave.click();
		LOG.info("Successfully clicked on Apply button");
	}
	
	@FindBy(css="div[tabindex='0']")
	WebElement leaveType ;
	
	@FindBy(css="div[role='option']")
	WebElement LeaveOptionsTemp ;
	
	public void selectLeaveType(String s) {
		kw.waitForElementToBeClickable(leaveType);
		leaveType.click();
		leaveType.sendKeys(s);
		kw.waitForElementToBeVisible(LeaveOptionsTemp);
		leaveType.sendKeys(Keys.ENTER);
		LOG.info("successfully selected leave type");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(1) > div > div:nth-child(2) > div > div > input")
	WebElement fromCalButton ;
	
	LocalDate today = LocalDate.now();
	
	public void selectFromDate() {
		kw.waitForElementToBeClickable(fromCalButton);
		fromCalButton.click();
		String s = "";
		if(today.getDayOfWeek().toString().equals("SATURDAY")) {
			s = today.plusDays(2).toString();
		}else if(today.getDayOfWeek().toString().equals("SUNDAY")) {
			s = today.plusDays(1).toString();
		}else {
			s = today.toString();
		}
		
		fromCalButton.sendKeys(s);
		fromCalButton.sendKeys(Keys.ENTER);
		LOG.info("Successfully from date selected");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(2) > div > div:nth-child(2) > div > div > input")
	WebElement toCalButton ;
	
	public void selectToDate() {
		kw.waitForElementToBeClickable(toCalButton);
		toCalButton.click();
		toCalButton.sendKeys(Keys.LEFT_CONTROL+ "A");
		toCalButton.sendKeys(Keys.DELETE);
		String s = "";
		if(today.getDayOfWeek().toString().equals("SATURDAY")) {
			s = today.plusDays(2).toString();
		}else if(today.getDayOfWeek().toString().equals("SUNDAY")) {
			s = today.plusDays(1).toString();
		}else {
			s = today.toString();
		}
		toCalButton.sendKeys(s);
		toCalButton.sendKeys(Keys.TAB);
		LOG.info("Successfully to date selected");
	}
	
	@FindBy(css="form>div:nth-of-type(5)>button[type='submit']")
	WebElement applyButton ;
	
	public void clickOnApplyLeaveButton() {
		kw.waitForElementToBeClickable(applyButton);
		applyButton.click();
		LOG.info("Successfully clicked on apply leave button ");
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
			LOG.info("Success Toast Message Displayed");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
	}
	
	@FindBy(css="div.oxd-toast--warn")
	WebElement warningFailedToSubmitToast ;
	
	public boolean failedToApplyLeaveToastText() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(warningFailedToSubmitToast,10);
			boolean isDisplayed = warningFailedToSubmitToast.isDisplayed();
			LOG.info("Failed to submit toast Displayed");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
	}
	
	@FindBy(css="form>div:nth-of-type(1)>div>div:nth-of-type(1)>div>span.oxd-text.oxd-text--span.oxd-input-field-error-message.oxd-input-group__message")
	WebElement  leaveTypeRequiredWarning;
	
	public boolean checkWarningForLeaveType() {
		kw.waitForElementToBeVisible(leaveTypeRequiredWarning);
		boolean isDisplayed = false;
		if(leaveTypeRequiredWarning.isDisplayed()) {
			isDisplayed = true;
			LOG.info("Warning Displayed for not selecting leave Type");
		}else {
			LOG.info("Warning not displayed");
		}
		return isDisplayed;
	}
	
	
	public void selectPastFromDate() {
		kw.waitForElementToBeClickable(fromCalButton);
		fromCalButton.click();
		String s = "";
		if(today.getDayOfWeek().toString().equals("SATURDAY")) {
			s = today.minusDays(1).toString();
		}else if(today.getDayOfWeek().toString().equals("SUNDAY")) {
			s = today.minusDays(2).toString();
		}else {
			s = today.toString();
		}
		
		fromCalButton.sendKeys(s);
		fromCalButton.sendKeys(Keys.ENTER);
		LOG.info("Successfully from date selected for past Date");
	}

	
	public void selectPastToDate() {
		kw.waitForElementToBeClickable(toCalButton);
		toCalButton.click();
		toCalButton.sendKeys(Keys.LEFT_CONTROL+ "A");
		toCalButton.sendKeys(Keys.DELETE);
		String s = "";
		if(today.getDayOfWeek().toString().equals("SATURDAY")) {
			s = today.minusDays(1).toString();
		}else if(today.getDayOfWeek().toString().equals("SUNDAY")) {
			s = today.minusDays(2).toString();
		}else {
			s = today.toString();
		}
		toCalButton.sendKeys(s);
		toCalButton.sendKeys(Keys.TAB);
		LOG.info("Successfully to date selected for past date");
	}
	
	@FindBy(css="div.oxd-toast-content.oxd-toast-content--error > p.oxd-text.oxd-text--p.oxd-text--toast-message.oxd-toast-content-text")
	WebElement errorToastLeaveBalance ;
	
	public boolean errorLeaveBalance() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(errorToastLeaveBalance,10);
			boolean isDisplayed = errorToastLeaveBalance.isDisplayed();
			LOG.info("\"Error - Leave balance\" toast Displayed");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
	}
	
	
	
}

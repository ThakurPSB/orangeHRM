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
	
	public boolean leaveAppliedSuccessfullyOrOverlapingLeave() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(saveSuccessfullToast, 10);
			kw.scrollToElement(saveSuccessfullToast);
			boolean isDisplayed = saveSuccessfullToast.isDisplayed();
			LOG.info("Leave applied successfully toast Displayed");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
		} catch (NoSuchElementException e) {
			if (checkOverlappingLeaveMessage()) {
				return true;
			} else {
				LOG.warn("Toast element not found in DOM.");
			}
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
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > aside:nth-child(1) > nav:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)")
	WebElement userLeaveMenu;
	 
	public void clickOnUserLeaveMenu() {
		kw.waitForElementToBeClickable(userLeaveMenu);
		kw.scrollToElement(userLeaveMenu);
		userLeaveMenu.click();
		LOG.info("Successfully clicked on User Leave Menu");
	}

	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--label-success oxd-table-cell-action-space']")
	WebElement approveLeaveButton ;

	public void clickOnApproveLeaveButton() {
		
		kw.waitForElementToBeClickable(approveLeaveButton);
		kw.scrollToElement(approveLeaveButton);
		approveLeaveButton.click();
		LOG.info("Successfully clicked on Approve leave button");
	}
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--label-danger oxd-table-cell-action-space']")
	WebElement rejectLeaveButton ;
	
	public void clickOnRejectLeaveButton() {
        kw.waitForElementToBeClickable(rejectLeaveButton);
        kw.scrollToElement(rejectLeaveButton);
        rejectLeaveButton.click();
        LOG.info("Successfully clicked on Reject leave button");
    }
	
	@FindBy(css="div[class='oxd-multiselect-wrapper'] div[class='oxd-select-text-input']")
	WebElement leaveStatusDropdown ;
	
	@FindBy(css=".oxd-icon.bi-x.--clear")
	WebElement pendingLeaveFilterRemove;
	
	
	
	public void selectLeaveStatus(String s) throws InterruptedException {
		kw.waitForElementToBeClickable(pendingLeaveFilterRemove);
		pendingLeaveFilterRemove.click();
		kw.waitForElementToBeClickable(leaveStatusDropdown);
		leaveStatusDropdown.click();
		leaveStatusDropdown.sendKeys(s);
		leaveStatusDropdown.sendKeys(Keys.ENTER);
		kw.normalWait(1000);
		LOG.info("successfully selected leave status filter as taken");
	}
	
	@FindBy(css="button[type='submit']")
	WebElement searchLeaveButton ;
	
	public void clickOnSearchLeaveButton() {
		kw.waitForElementToBeClickable(searchLeaveButton);
		searchLeaveButton.click();
		LOG.info("Successfully clicked on Search leave button ");
	}
	
	@FindBy(css="i.oxd-icon.bi-three-dots-vertical")
	WebElement moreOptionsButton ;
	
	public void clickOnMoreOptionsButton() {
		kw.waitForElementToBeInvisible(loader);
		kw.waitForElementToBeVisible(moreOptionsButton);
		kw.scrollToElement(moreOptionsButton);
		moreOptionsButton.click();
		LOG.info("Successfully clicked on More options button");
	}
	
	@FindBy(css="li:nth-child(4) p:nth-child(1)")
	WebElement cancelLeaveOption ;
	
	public void clickOnCancelLeaveOption() {
		kw.waitForElementToBeClickable(cancelLeaveOption);
		cancelLeaveOption.click();
		LOG.info("Successfully clicked on Cancel leave option");
	}
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--label-warn oxd-table-cell-action-space']")
	WebElement userCancelLeaveButton ;
	
	@FindBy(css=".oxd-form-loader")
	WebElement loader;
	
	@FindBy(css="div[class='oxd-table-card'] div:nth-child(7)")
	WebElement leaveLine ;
	
	public void clickOnUserCancelLeaveButton() {

		kw.waitForElementToBeInvisible(loader);
		kw.waitForElementToBeClickable(leaveLine);
		kw.waitForElementToBeClickable(userCancelLeaveButton);
		kw.scrollToElement(userCancelLeaveButton);
		userCancelLeaveButton.click();
		LOG.info("Successfully clicked on User Cancel leave button");
	}
	
	@FindBy(css="div[class='orangehrm-header-container'] h6[class='oxd-text oxd-text--h6 orangehrm-main-title']")
	WebElement OVerlappingLeaveMessage ;
	
	public boolean checkOverlappingLeaveMessage() {
		kw.waitForElementToBeVisible(OVerlappingLeaveMessage);
		boolean isDisplayed = false;
		if (OVerlappingLeaveMessage.isDisplayed()) {
			isDisplayed = true;
			LOG.info("Overlapping leave message is displayed");
		} else {
			LOG.info("Overlapping leave message is not displayed");
		}
		return isDisplayed;
	}
	
	
	
	
	
}

package com.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class LeaveMyLeaveMenu {
	
	private Keywords kw;
	public LeaveMyLeaveMenu(Keywords kw) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = kw;
	}

	private static final Logger LOG = Logger.getLogger(LeaveMyLeaveMenu.class);
	
	@FindBy(css="div>nav>ul>li:nth-of-type(2)")
	WebElement myLeaveMenu ;
	
	public void clickOnMyLeaveMenu() {
		kw.waitForElementToBeVisible(myLeaveMenu);
		myLeaveMenu.click();
		LOG.info("Successfully clicked on My Leave menu");
	}
	
	
	@FindBy(css="li.oxd-main-menu-item-wrapper:nth-of-type(3)")
	WebElement leaveMenu ;
	
	public void clickOnLeaveMenu() {
		kw.waitForElementToBeClickable(leaveMenu);
		kw.scrollToElement(leaveMenu);
		leaveMenu.click();
		LOG.info("Successfully clicked on Leave Menu");
	}
	
	
	
	@FindBy(css="Button[type='button'].oxd-button.oxd-button--medium")
	List<WebElement> cancelButtons;
	
	public void cancelAllLeaves() throws InterruptedException {
		kw.normalWait(3000);
		List<WebElement> cancelButtons = kw.getDriver().findElements(By.cssSelector("Button[type='button'].oxd-button.oxd-button--medium"));
		int count = cancelButtons.size();
		if (cancelButtons.isEmpty()) {
	        LOG.info("No leave application to cancel");
	    }else {
			kw.waitForAllElementAreVisible(cancelButtons);
			for(WebElement button: cancelButtons) {
				kw.scrollToElement(button);
				button.click();
				kw.normalWait(500);
			}
			LOG.info("Cancelled the Leave application : "+count);
	    }
	}
	
}

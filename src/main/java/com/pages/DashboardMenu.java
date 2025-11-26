package com.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class DashboardMenu {
	
	//This variable allows Page object to use the keywords methods
	private Keywords kw;

	/*
	 * Constructor to initialize the PageFactory elements
	 * @param kw - Keywords class reference
	 * this.kw = kw means Take the Keywords object passed into the constructor and 
	 * assign it to the page object’s private field, 
	 * so this page object can use it later.
	 */
	public DashboardMenu(Keywords key) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = key;
	}
	
	private static final Logger LOG = Logger.getLogger(TimeMenu.class);
	
	@FindBy(css=".oxd-main-menu-item")
	WebElement menuSearchResult ;
	
	@FindBy(css="input[placeholder='Search']")
	WebElement menuSearchField ;
	
	public void clickOnMenu(String menuName) {
		kw.waitForElementToBeClickable(menuSearchField);
		menuSearchField.click();
		menuSearchField.sendKeys(menuName);
		menuSearchResult.click();
		LOG.info("Successfully clicked on Menu "+menuName);
	}
	
	@FindBy(css="button[class='oxd-icon-button oxd-icon-button--solid-main orangehrm-attendance-card-action']")
	WebElement timeEntryButton;
	
	public void clickOnPunchInButton() {
		kw.waitForElementToBeClickable(timeEntryButton);
		timeEntryButton.click();
		LOG.info("Successfully clicked on timeEntry button on dashboard");
	}
	
	public boolean checkUrlChangeToAttendance(String s) {
		kw.waitForUrlToBeChanged("http://localhost/orangehrm/web/index.php/dashboard/index");
		if(kw.getCurrentURL().equalsIgnoreCase(s)) {
			LOG.info("Successfully diverted URL "+s);
			return true;
		} else {
			LOG.info("Page not changed");
			return false;
		}
		
	}
	
	@FindBy(css="button[title='Apply Leave'] svg")
	WebElement dashboardApplyLeaveShortcut;
	
	public void clickOnDashboardApplyLeaveShortcutbutton() {
		kw.waitForElementToBeClickable(dashboardApplyLeaveShortcut);
		kw.scrollToElement(dashboardApplyLeaveShortcut);
		dashboardApplyLeaveShortcut.click();
		LOG.info("Successfully clicked on dashboard apply leave shortcut");
	}
	
	@FindBy(css="button[title='My Timesheet'] svg")
	WebElement dashboardMyTimesheetShortcut;
	
	public void clickOnDashboardMyTimesheetShortcutbutton() {
		kw.waitForElementToBeClickable(dashboardMyTimesheetShortcut);
		kw.scrollToElement(dashboardMyTimesheetShortcut);
		dashboardMyTimesheetShortcut.click();
		LOG.info("Successfully clicked on dashboard my timesheet shortcut");
	}
	
	
	
	
	
	
}

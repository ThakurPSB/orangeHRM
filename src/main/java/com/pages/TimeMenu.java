package com.pages;


import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class TimeMenu {
	
	//This variable allows Page object to use the keywords methods
	private Keywords kw;

	/*
	 * Constructor to initialize the PageFactory elements
	 * @param kw - Keywords class reference
	 * this.kw = key means Take the Keywords object passed into the constructor and 
	 * assign it to the page object’s private field, 
	 * so this page object can use it later.
	 */
	public TimeMenu(Keywords key) {
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
	
	@FindBy(css="div > div.oxd-alert-content.oxd-alert-content--warn > p")
	WebElement noTimesheetFoundMessage ;
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--secondary']")
	WebElement createTimesheetButton ;
	
	public void clickOnCreateTimesheetButtonOrEditTimesheetButton() {
	    try {
	        if (kw.isElementPresent(createTimesheetButton) && createTimesheetButton.isDisplayed()) {
	            kw.waitForElementToBeClickable(createTimesheetButton);
	            createTimesheetButton.click();
	            LOG.info("Clicked on Create Timesheet Button");
	        } else{
	            kw.waitForElementToBeClickable(EditTimesheetButton);
	            EditTimesheetButton.click();
	            LOG.info("Clicked on Edit Timesheet Button");
	        }
	    } catch (Exception e) {
	        LOG.error("Error while clicking on Timesheet button", e);
	    }

    }
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--ghost']")
	WebElement EditTimesheetButton ;
	
	public void clickOnEditTimesheetButton() {
		kw.waitForElementToBeClickable(EditTimesheetButton);
		EditTimesheetButton.click();
		LOG.info("Successfully clicked on Edit Timesheet Button");
	}
	
	@FindBy(css= "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
	WebElement SubmitTimesheetButton ;
	
	public void clickOnSubmitTimesheetButton() {
		kw.waitForElementToBeClickable(SubmitTimesheetButton);
		SubmitTimesheetButton.click();
		LOG.info("Successfully clicked on Submit Timesheet Button");
	}
	
	@FindBy(css="form > div.orangehrm-timesheet-body > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > div:nth-child(2) > div > div > input")
	WebElement projectNameField ;
	
	@FindBy(css="#app > div.oxd-layout > div.oxd-layout-container > div.oxd-layout-context > div > form > div.orangehrm-timesheet-body > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > div:nth-child(2) > div > div.oxd-autocomplete-dropdown.--positon-bottom")
	WebElement projectNameFieldList ;
	
	@FindBy(css="#app > div.oxd-layout > div.oxd-layout-container > div.oxd-layout-context > div > form > div.orangehrm-timesheet-body > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div:nth-child(2) > div > div.oxd-select-dropdown.--positon-bottom")
	WebElement activityFieldList ;
	
	@FindBy(css=".oxd-icon.bi-trash")
	WebElement deleteTimesheetEntryIcon ;
	
	public void clickOnDeleteTimesheetEntryIcon() {
		kw.waitForElementToBeClickable(deleteTimesheetEntryIcon);
		deleteTimesheetEntryIcon.click();
		LOG.info("Successfully cleared previous timesheet entry details");
	}
	
	public void enterProjectName(String projectName) throws InterruptedException {
		kw.waitForElementToBeVisible(projectNameField);
		kw.clearTextBox(projectNameField);
		projectNameField.sendKeys(Keys.TAB);
		projectNameField.sendKeys(Keys.ESCAPE);
		projectNameField.click();
		projectNameField.sendKeys(projectName);
		kw.waitForElementToBeClickable(projectNameFieldList);
		kw.normalWait(2000);
		projectNameField.sendKeys(Keys.ARROW_DOWN);
		projectNameField.sendKeys(Keys.ENTER);
		//projectNameField.sendKeys(Keys.TAB);
		LOG.info("Successfully entered project name");
	}
	
	@FindBy(css=".oxd-select-text-input")
	WebElement activityDropdown ;
	
	public void selectActivity(String activity) throws InterruptedException {
		kw.waitForElementToBeClickable(activityDropdown);
		activityDropdown.click();
		//kw.waitForElementToBeVisible(activityFieldList);
		kw.normalWait(1000);
		activityDropdown.sendKeys(activity);
		activityDropdown.sendKeys(Keys.ENTER);
		LOG.info("successfully selected activity");
	}
	
	@FindBy(css="table > tbody > tr:nth-child(1) > td:nth-child(3) > div > div:nth-child(2) > input")
	WebElement mondayHoursField ;
	
	@FindBy(css="table > tbody > tr:nth-child(1) > td:nth-child(4) > div > div:nth-child(2) > input")
	WebElement tuesdayHoursField ;
	
	public void enterHoursForMondayAndTuesday() {
		kw.waitForElementToBeVisible(mondayHoursField);
		mondayHoursField.click();
		kw.clearTextBox(mondayHoursField);
		mondayHoursField.sendKeys("6");
		kw.waitForElementToBeVisible(tuesdayHoursField);
		tuesdayHoursField.click();
		kw.clearTextBox(tuesdayHoursField);
		tuesdayHoursField.sendKeys("6");
		LOG.info("Successfully entered hours for Monday and Tuesday");
	}
	
	@FindBy(css="button[type='submit']")
	WebElement saveTimesheetButton ;
	
	public void clickOnSaveTimesheetButton() {
        kw.waitForElementToBeClickable(saveTimesheetButton);
        saveTimesheetButton.click();
        LOG.info("Successfully clicked on Save Timesheet Button");
    }
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > form:nth-child(1) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(3) > span:nth-child(2)")
	WebElement verifyMondayHours ;
	
	public String getMondayHours() {
        kw.waitForElementToBeVisible(verifyMondayHours);
        String hours = verifyMondayHours.getText();
        LOG.info("Successfully retrieved Monday hours");
        hrString = hours;
        return hours;
	}
	
	String hrString;
	
	public String randomIncrementDecrementInHours() {
	
		int hr = Integer.parseInt(hrString.split(":")[0]);
		double randomValue = Math.random();
		if (randomValue < 0.5) {
			hr = hr - 1;
		} else {
			hr = hr + 1;
		}
		return Integer.toString(hr);
	}
	
	
	public void enterUpdatedMondayHours() {
		kw.waitForElementToBeVisible(mondayHoursField);
		mondayHoursField.click();
		kw.clearTextBox(mondayHoursField);
		mondayHoursField.sendKeys(randomIncrementDecrementInHours());
		LOG.info("Successfully entered updated Monday hours");
	}
	
	public boolean checkIfMondayHoursUpdated() {
		kw.waitForElementToBeVisible(verifyMondayHours);
		String updatedHours = verifyMondayHours.getText();
		if(!updatedHours.equals(getMondayHours())) {
            LOG.info("Monday hours updated successfully to: " + updatedHours);
            return true;
        } else {
            LOG.error("Monday hours update failed. Current hours: " + updatedHours);
            return false;
        }
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
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--text oxd-table-cell-action-space']")
	WebElement viewTimesheetButton ;
	
	public void clickOnViewTimesheetButton() {
        kw.waitForElementToBeClickable(viewTimesheetButton);
        viewTimesheetButton.click();
        LOG.info("Successfully clicked on View Timesheet Button");
	}
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--danger']")
	WebElement rejectTimesheetButton ;
	
	public void clickOnRejectTimesheetButton() {
        kw.waitForElementToBeClickable(rejectTimesheetButton);
        rejectTimesheetButton.click();
        LOG.info("Successfully clicked on Reject Timesheet Button");
	}
	
	

	
}

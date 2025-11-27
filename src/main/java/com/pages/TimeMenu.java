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

	
	public TimeMenu() {
		this.kw = Keywords.getInstance();;
	    PageFactory.initElements(kw.getDriver(), this);
	}
	
	private static final Logger LOG = Logger.getLogger(TimeMenu.class);
	
	@FindBy(css=".oxd-main-menu-item")
	WebElement menuSearchResult ;
	
	@FindBy(css="input[placeholder='Search']")
	WebElement menuSearchField ;
	
	public void clickOnMenu(String menuName) {
		kw.waitForElementToBeClickable(menuSearchField);
		kw.scrollToElement(menuSearchField);
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
	            kw.scrollToElement(createTimesheetButton);
	            createTimesheetButton.click();
	            LOG.info("Clicked on Create Timesheet Button");
	        } else{
	            kw.waitForElementToBeClickable(EditTimesheetButton);
	            kw.scrollToElement(EditTimesheetButton);
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
		kw.scrollToElement(EditTimesheetButton);
		EditTimesheetButton.click();
		LOG.info("Successfully clicked on Edit Timesheet Button");
	}
	
	@FindBy(css= "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
	WebElement SubmitTimesheetButton ;
	
	public void clickOnSubmitTimesheetButton() {
		kw.waitForElementToBeClickable(SubmitTimesheetButton);
		kw.scrollToElement(SubmitTimesheetButton);
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
		kw.scrollToElement(deleteTimesheetEntryIcon);
		deleteTimesheetEntryIcon.click();
		LOG.info("Successfully cleared previous timesheet entry details");
	}
	
	public void enterProjectName(String projectName) throws InterruptedException {
		kw.waitForElementToBeVisible(projectNameField);
		kw.scrollToElement(projectNameField);
		kw.clearTextBox(projectNameField);
		projectNameField.sendKeys(Keys.TAB);
		projectNameField.sendKeys(Keys.ESCAPE);
		projectNameField.click();
		projectNameField.sendKeys(projectName);
		kw.waitForElementToBeClickable(projectNameFieldList);
		kw.scrollToElement(projectNameFieldList);
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
		kw.scrollToElement(activityDropdown);
		activityDropdown.click();
		//kw.waitForElementToBeVisible(activityFieldList);
		kw.normalWait(1000);
		activityDropdown.sendKeys(activity);
		activityDropdown.sendKeys(Keys.ENTER);
		LOG.info("successfully selected activity");
	}
	
	@FindBy(css="table tbody tr:first-child td:nth-child(3) input")
	WebElement mondayHoursField ;
	
	@FindBy(css="table tbody tr:first-child td:nth-child(4) input")
	WebElement tuesdayHoursField ;
	
	public void enterHoursForMondayAndTuesday() {
		kw.waitForElementToBeVisible(mondayHoursField);
		kw.scrollToElement(mondayHoursField);
		mondayHoursField.click();
		kw.clearTextBox(mondayHoursField);
		mondayHoursField.sendKeys("6");
		kw.waitForElementToBeVisible(tuesdayHoursField);
		kw.scrollToElement(tuesdayHoursField);
		tuesdayHoursField.click();
		kw.clearTextBox(tuesdayHoursField);
		tuesdayHoursField.sendKeys("6");
		LOG.info("Successfully entered hours for Monday and Tuesday");
	}
	
	@FindBy(css="button[type='submit']")
	WebElement saveTimesheetButton ;
	
	public void clickOnSaveTimesheetButton() {
        kw.waitForElementToBeClickable(saveTimesheetButton);
        kw.scrollToElement(saveTimesheetButton);
        saveTimesheetButton.click();
        LOG.info("Successfully clicked on Save Timesheet Button");
    }
	
	
	
	@FindBy(css="input[name='monday']")
	WebElement mondayHoursInputField;
	
	public String getMondayHoursAfterEdit() {
	    kw.waitForElementToBeVisible(mondayHoursInputField);
	    kw.scrollToElement(mondayHoursInputField);
	    String hours = mondayHoursInputField.getDomProperty("value");
	    LOG.info("Successfully retrieved updated Monday hours");
	    return hours;
	}
	
	@FindBy(css="table tbody tr:first-child td:nth-child(3) span")
	WebElement verifyMondayHours ;
	
	public String getMondayHours() {
        kw.waitForElementToBeVisible(verifyMondayHours);
        kw.scrollToElement(verifyMondayHours);
        String hours = verifyMondayHours.getText();
        LOG.info("Successfully retrieved Monday hours");
        return hours;
	}
	
	@FindBy(css = "table tbody tr:first-child td:nth-child(3) span")
	WebElement mondayHoursViewMode;

	public String getMondayHours_ViewMode() {
	    kw.waitForElementToBeVisible(mondayHoursViewMode);
	    kw.scrollToElement(mondayHoursViewMode);
	    return mondayHoursViewMode.getText().trim();
	}
	
	@FindBy(css = "input[name='monday']")
	WebElement mondayHoursEditMode;

	public String getMondayHours_EditMode() {
	    kw.waitForElementToBeVisible(mondayHoursEditMode);
	    kw.scrollToElement(mondayHoursEditMode);
	    return mondayHoursEditMode.getDomProperty("value").trim();
	}
	
	public String randomIncrementDecrementInHours(String hrString) {
	
		 if (hrString == null) {
		        hrString = getMondayHours();
		    }
		    int hr = Integer.parseInt(hrString.split(":")[0]);
		    double randomValue = Math.random();
		    hr = randomValue < 0.5 ? hr - 1 : hr + 1;
		    return Integer.toString(hr);
	}
	
	
	public void enterUpdatedMondayHours(String currentHours) {
		kw.waitForElementToBeVisible(mondayHoursField);
		kw.scrollToElement(mondayHoursField);
		mondayHoursField.click();
		kw.clearTextBox(mondayHoursField);
		mondayHoursField.sendKeys(randomIncrementDecrementInHours(currentHours));
		LOG.info("Successfully entered updated Monday hours");
	}
	
	public boolean checkIfMondayHoursUpdated() {
		kw.waitForElementToBeVisible(verifyMondayHours);
		kw.scrollToElement(verifyMondayHours);
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
        kw.scrollToElement(viewTimesheetButton);
        viewTimesheetButton.click();
        LOG.info("Successfully clicked on View Timesheet Button");
	}
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--danger']")
	WebElement rejectTimesheetButton ;
	
	public void clickOnRejectTimesheetButton() {
        kw.waitForElementToBeClickable(rejectTimesheetButton);
        kw.scrollToElement(rejectTimesheetButton);
        rejectTimesheetButton.click();
        LOG.info("Successfully clicked on Reject Timesheet Button");
	}
	
	

	
}

package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import com.main.Keywords;

public class TerminationReasons {
	
	public TerminationReasons() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	private static final Logger LOG = Logger.getLogger(TerminationReasons.class);
	
	@FindBy(css="nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(5) > a")
	WebElement TerminationReasons ;
	
	/**
	 * click on the Termination Reasons field in configuration menu
	 */
	public void clickOnTerminationReasonsMenu() {
		kw.waitForElementToBeVisible(TerminationReasons);
		TerminationReasons.click();
		LOG.info("Successfully click on the Termination Reason Menu");
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div.orangehrm-header-container > div > button")
	WebElement AddTerminationReasonButton ;
	
	/**
	 * click on the add button in termination reason menu
	 */
	public void ClickOnAddTerminationReasonButton() {
		kw.waitForElementToBeVisible(AddTerminationReasonButton);
		AddTerminationReasonButton.click();
		LOG.info("Successfully clicked on the Add Termindation reason button");
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(2) > input")
	WebElement EnterTerminationReason ;
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement AddTerminationReasonSaveButton ;
	
	/**
	 * @param description of reason. 
	 * Add the reason in list of termination list.
	 */
	public void AddTerminationReason(String name) {
		kw.waitForElementToBeVisible(AddTerminationReasonSaveButton);
		kw.waitForElementToBeVisible(EnterTerminationReason);
		EnterTerminationReason.sendKeys(name);
		AddTerminationReasonSaveButton.click();
		LOG.info("Successfully added the termination reason");
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
			kw.normalWait(200);
			kw.waitForElementToBeClickable(confirmDeleteYes);
	        confirmDeleteYes.click();
	        LOG.info(name+" - Termination option deleted");
		}else {
			LOG.info("No such Termination reason found.");
		}
		
	}
	
	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successful toast message
	 */
	public boolean SaveToastMessageText() {
		kw.waitForElementToBeVisible(saveSuccessfullToast);
		boolean isDisplayed =  saveSuccessfullToast.isDisplayed();
		LOG.info("Successfully saved the Termination reason to list ");
		return isDisplayed;
	}
	
	
	
}

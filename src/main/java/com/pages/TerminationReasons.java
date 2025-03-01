package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class TerminationReasons {
	
	public TerminationReasons() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	
	@FindBy(css="nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(5) > a")
	WebElement TerminationReasons ;
	
	/**
	 * click on the Termination Reasons field in configuration menu
	 */
	public void clickOnTerminationReasonsMenu() {
		kw.waitForElementToBeVisible(TerminationReasons);
		TerminationReasons.click();
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div.orangehrm-header-container > div > button")
	WebElement AddTerminationReasonButton ;
	
	/**
	 * click on the add button in termination reason menu
	 */
	public void ClickOnAddTerminationReasonButton() {
		kw.waitForElementToBeVisible(AddTerminationReasonButton);
		AddTerminationReasonButton.click();
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
	}
	
	@FindBy(css="div.oxd-table-row.oxd-table-row")
	List<WebElement> TerminationReasonList ;
	
	@FindBy(css="i.oxd-icon.bi-trash.oxd-button-icon")
	WebElement confirmDeleteYes ;
	
	@FindBy(css="i.oxd-icon.bi-trash-fill.oxd-button-icon")
	WebElement deleteButton ;
	
	@FindBy(css="button[class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
	WebElement confirmDelete ;
	
	public void deleteSelectedTerminationReason(String name) {
		kw.waitForAllElementAreVisible(TerminationReasonList);
		for(WebElement row: TerminationReasonList) {
			if(row.getText().contains(name)) {
				WebElement checkboxTick = row.findElement(By.cssSelector("span.oxd-checkbox-input"));
				kw.waitForElementToBeClickable(checkboxTick);
				checkboxTick.click();
				break;
			}
			
		}
		kw.waitForElementToBeClickable(deleteButton);
        deleteButton.click();	
        kw.waitForElementToBeClickable(confirmDelete);
        confirmDelete.click();
	}
	
	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successful toast message
	 */
	public boolean SaveToastMessageText() {
		kw.waitForElementToBeVisible(saveSuccessfullToast);
		return saveSuccessfullToast.isDisplayed();
	}
	
	
	
}

package com.pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

import com.main.Keywords;

public class PimCustomFields {
	
	public PimCustomFields() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	private static final Logger LOG = Logger.getLogger(PimCustomFields.class);
	
	Actions action = new Actions(Keywords.driver);
	
	@FindBy(css="nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(2) > a")
	WebElement customFields ;

	/**
	 * Clicks on PIM>Configuration>Customfields
	 */
	public void clickOnCustomFields() {
		kw.waitForElementToBeVisible(customFields);
		customFields.click();
		LOG.info("Successfully clicked on Custom Field Option ");
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div.orangehrm-header-container > button")
	WebElement addCustomFields ;
	
	/**
	 * Click on add custom fields button.
	 */
	public void clickOnAddCustomFieldsButton() {
		kw.waitForElementToBeClickable(addCustomFields);
		addCustomFields.click();
		LOG.info("Successfully clicked on the Add custom Field Button");
	}
	
	@FindBy(css="form > div:nth-child(1) > div > div.oxd-grid-item.oxd-grid-item--gutters.organization-name-container > div > div:nth-child(2) > input")
	WebElement fieldName ;
	
	public void enterFieldName(String field) {
		kw.waitForElementToBeClickable(fieldName);
		fieldName.click();
		fieldName.sendKeys(field);
		LOG.info("Successfully entered the field name in text box");
	}
	
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(1) > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement screen;
	
	/**
	 * select the memebership screen to add the custom field
	 */
	public void clickOnScreenOption() {
		kw.waitForElementToBeClickable(screen);
		screen.click();
		for(int i = 1; i<=11;i++) {
			screen.sendKeys(Keys.ARROW_DOWN);
		}
		screen.sendKeys(Keys.ENTER);
		LOG.info("Successfully selected the screen option");
	}
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(2) > div > div > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement typeOfInput;
	
	/**
	 * select the type of input as dropdown
	 */
	public void SelectTypeOfInputDropdown() {
		kw.waitForElementToBeClickable(typeOfInput);
		typeOfInput.sendKeys(Keys.ARROW_DOWN);
		typeOfInput.sendKeys(Keys.ARROW_DOWN);
		typeOfInput.sendKeys(Keys.ENTER);		
		LOG.info("Successfully selected the input style as dropdown");
	}
	
	/**
	 * select the type of input as text or number
	 */
	public void selectTypeOfInputText() {
		kw.waitForElementToBeClickable(typeOfInput);
		typeOfInput.sendKeys(Keys.ARROW_DOWN);
		typeOfInput.sendKeys(Keys.ENTER);
		LOG.info("Successfully selected the input style as Text");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(2) > div > div:nth-child(2) > input")
	WebElement selectOptionstextbox ;
	
	/**
	 * @param options for drop down separated by commas
	 */
	public void enterOptions(String opt) {
		kw.waitForElementToBeVisible(selectOptionstextbox);
		selectOptionstextbox.sendKeys(opt);
		LOG.info("Successfully entered the options for dropdown");
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement saveCustomField ;
	
	/**
	 * click on save button after entering details for custom field
	 */
	public void clickOnSaveCustomFieldButton() {
		kw.waitForElementToBeClickable(saveCustomField);
		saveCustomField.click();
		LOG.info("Successfully clicked on the Save Custom Field button ");
	}
	
	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successful toast message
	 */
	public boolean SaveToastMessageText() {
		kw.waitForElementToBeVisible(saveSuccessfullToast);
		boolean isDisplayed = saveSuccessfullToast.isDisplayed();
		LOG.info("Successfully saved the custom field");
		return isDisplayed;
	}
	
	
	
	//list of customfiels
	@FindBy(css="div.oxd-table div.oxd-table-row")
	List<WebElement> rows ;
	
	@FindBy(css="div > div > div > div.orangehrm-modal-footer > button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	WebElement confirmDeleteYes ;
	
	public void SelectFieldToDelete(String fieldname) throws InterruptedException {
		kw.waitForAllElementAreVisible(rows);
		boolean isFieldFound = false;
		WebElement found = null;
		for (WebElement row : rows) {
			if(row.getText().contains(fieldname)) {
				isFieldFound = true;
				found = row;
				break;
			}else {
				isFieldFound = false;
			}
		}
	    if (isFieldFound) { 
	    	LOG.info("Found custome field to be deleted ");
	        WebElement delButton = found.findElement(By.cssSelector("button.oxd-icon-button.oxd-table-cell-action-space i.oxd-icon.bi-trash"));
	    	delButton.click();
	    	kw.normalWait(200);
	    	kw.waitForElementToBeClickable(confirmDeleteYes);
	        confirmDeleteYes.click();
	    }else {
	    	LOG.info(fieldname+ " not found in the custom fields list");
	    }
	}
	
}



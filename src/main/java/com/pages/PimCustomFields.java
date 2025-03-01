package com.pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class PimCustomFields {
	
	public PimCustomFields() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	
	Actions action = new Actions(Keywords.driver);
	
	@FindBy(css="nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(2) > a")
	//           nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(5) > a
	WebElement customFields ;

	/**
	 * Clicks on PIM>Configuration>Customfields
	 */
	public void clickOnCustomFields() {
		kw.waitForElementToBeVisible(customFields);
		customFields.click();
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div.orangehrm-header-container > button")
	WebElement addCustomFields ;
	
	/**
	 * Click on add custom fields button.
	 */
	public void clickOnAddCustomFieldsButton() {
		kw.waitForElementToBeClickable(addCustomFields);
		addCustomFields.click();
	}
	
	@FindBy(css="form > div:nth-child(1) > div > div.oxd-grid-item.oxd-grid-item--gutters.organization-name-container > div > div:nth-child(2) > input")
	WebElement fieldName ;
	
	public void enterFieldName(String field) {
		kw.waitForElementToBeClickable(fieldName);
		fieldName.click();
		fieldName.sendKeys(field);
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
	}
	
	/**
	 * select the type of input as text or number
	 */
	public void selectTypeOfInputText() {
		kw.waitForElementToBeClickable(typeOfInput);
		typeOfInput.sendKeys(Keys.ARROW_DOWN);
		typeOfInput.sendKeys(Keys.ENTER);
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(2) > div > div:nth-child(2) > input")
	WebElement selectOptionstextbox ;
	
	/**
	 * @param options for drop down separated by commas
	 */
	public void enterOptions(String opt) {
		kw.waitForElementToBeVisible(selectOptionstextbox);
		selectOptionstextbox.sendKeys(opt);
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement saveCustomField ;
	
	/**
	 * click on save button after entering details for custom field
	 */
	public void clickOnSaveCustomFieldButton() {
		kw.waitForElementToBeClickable(saveCustomField);
		saveCustomField.click();
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
	
	
	
	//list of customfiels
	@FindBy(css="div.oxd-table div.oxd-table-row")
	List<WebElement> rows ;
	
	@FindBy(css="div > div > div > div.orangehrm-modal-footer > button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	WebElement confirmDeleteYes ;
	
	public void SelectFieldToDelete(String fieldname) {
		kw.waitForAllElementAreVisible(rows);
		for (WebElement row : rows) {
			
            if (row.getText().contains(fieldname)) {  
                WebElement delButton = row.findElement(By.cssSelector("button.oxd-icon-button.oxd-table-cell-action-space i.oxd-icon.bi-trash"));
            	delButton.click();
            }
        }
		 kw.waitForElementToBeClickable(confirmDeleteYes);
         confirmDeleteYes.click();
	}
	
}



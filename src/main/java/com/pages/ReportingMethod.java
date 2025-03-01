package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class ReportingMethod {

	public ReportingMethod() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	
	@FindBy(css="nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(4) > a")
	WebElement reportingMethodMenu ;
	
	/**
	 * clicks on reporting Method menu in configuration menu
	 */
	public void clickOnReportingMethod() {
		kw.waitForElementToBeClickable(reportingMethodMenu);
		reportingMethodMenu.click();
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div.orangehrm-header-container > div > button")
	WebElement AddReportingMethod ;
	
	/**
	 * click on ADD button in reporting method menu
	 */
	public void AddReportingMethodButton() {
		kw.waitForElementToBeVisible(AddReportingMethod);
		AddReportingMethod.click();
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > form > div.oxd-form-row > div > div:nth-child(2) > input")
	WebElement reportingMethodName;
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement reportingMethodSaveButton ;
	
	public void addrReportingMethod(String nm) {
		kw.waitForElementToBeVisible(reportingMethodName);
		reportingMethodName.sendKeys(nm);
		reportingMethodSaveButton.click();
	}
	
	@FindBy(css="div.oxd-table-row")
	List<WebElement> reportingMethodList;
	
	@FindBy(css="i.oxd-icon.bi-trash.oxd-button-icon")
	WebElement confirmDeleteYes ;
	
	public void deleteSelectedReportingMethod(String name) {
		kw.waitForAllElementAreVisible(reportingMethodList);
		for(WebElement row: reportingMethodList) {
			if(row.getText().contains(name)) {
				System.out.println("found");
				WebElement deleteButton = row.findElement(By.cssSelector("button.oxd-icon-button > i.oxd-icon.bi-trash"));
				deleteButton.click();
			}
		}
		 kw.waitForElementToBeClickable(confirmDeleteYes);
         confirmDeleteYes.click();		
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

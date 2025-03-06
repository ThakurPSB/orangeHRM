package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.main.Keywords;

public class ReportingMethod {

	public ReportingMethod() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	private static final Logger LOG = Logger.getLogger(ReportingMethod.class);
	
	@FindBy(css="nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(4) > a")
	WebElement reportingMethodMenu ;
	
	/**
	 * clicks on reporting Method menu in configuration menu
	 */
	public void clickOnReportingMethod() {
		kw.waitForElementToBeClickable(reportingMethodMenu);
		reportingMethodMenu.click();
		LOG.info("Successfully clicked on Reporting Method");
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div.orangehrm-header-container > div > button")
	WebElement AddReportingMethod ;
	
	/**
	 * click on ADD button in reporting method menu
	 */
	public void AddReportingMethodButton() {
		kw.waitForElementToBeVisible(AddReportingMethod);
		AddReportingMethod.click();
		LOG.info("Successfully clicked on Add Reporting Method Button ");
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > form > div.oxd-form-row > div > div:nth-child(2) > input")
	WebElement reportingMethodName;
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement reportingMethodSaveButton ;
	
	public void addrReportingMethod(String nm) {
		kw.waitForElementToBeVisible(reportingMethodName);
		reportingMethodName.sendKeys(nm);
		reportingMethodSaveButton.click();
		LOG.info("Successfully entered the reporting method in text box");
	}
	
	@FindBy(css="div.oxd-table-row")
	List<WebElement> reportingMethodList;
	
	@FindBy(css="i.oxd-icon.bi-trash.oxd-button-icon")
	WebElement confirmDeleteYes ;
	
	public void deleteSelectedReportingMethod(String name) throws InterruptedException {
		kw.waitForAllElementAreVisible(reportingMethodList);
		
		for(WebElement row: reportingMethodList) {
			if(row.getText().contains(name)) {
				LOG.info("Found the reporting Method");
				WebElement deleteButton = row.findElement(By.cssSelector("button.oxd-icon-button > i.oxd-icon.bi-trash"));
				deleteButton.click();
				kw.normalWait(200);
				kw.waitForElementToBeClickable(confirmDeleteYes);
		        confirmDeleteYes.click();
		        LOG.info(name+" Reporing option deleted");
			}else {
				LOG.info("No such method found");
			}
		}
		 	
	}
	
	
	
	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successful toast message
	 */
	public boolean SaveToastMessageText() {
		kw.waitForElementToBeVisible(saveSuccessfullToast);
		boolean isDisplayed = saveSuccessfullToast.isDisplayed();
		LOG.info("Successfully saved the repoting Method");
		return isDisplayed;
		
	}
	
	
	
	
	
	
	
	
}

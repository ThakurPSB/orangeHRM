package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import com.main.Keywords;

public class UserProfile {
	
	public UserProfile() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	
	private static final Logger LOG = Logger.getLogger(UserProfile.class);
	
	@FindBy(css="div.oxd-layout-navigation > header > div.oxd-topbar-body > nav > ul > li.oxd-topbar-body-nav-tab.--visited > a")
	WebElement EmployeeList ;
	
	public void clickOnEmployeeList() {
		kw.waitForElementToBeVisible(EmployeeList);
		EmployeeList.click();
		LOG.info("Clicked employee list menu.");
	}
	
	@FindBy(css="div[role='rowgroup'] div:nth-child(1) div:nth-child(1) div:nth-child(3) div:nth-child(1)")
	WebElement user1 ;
	
	/**
	 * clicks on the first line of user list
	 */
	public void clickOnUser1() {
		kw.waitForElementToBeClickable(user1);
		user1.click();
		LOG.info("Selected the 1st user.");
	}
	
	@FindBy(css="div.orangehrm-edit-employee-navigation > div.orangehrm-tabs > div:nth-child(1) > a")
	WebElement PersonalDetails ;
	
	/**
	 * Clicks on personal details menu inside user profile
	 */
	public void clickOnPersonalDetails() {
		kw.waitForElementToBeClickable(PersonalDetails);
		PersonalDetails.click();
		LOG.info("Clicked on personal details section .");
	}
	
	@FindBy(css="form > div:nth-child(3) > div:nth-child(3) > div:nth-child(1) > div > div.oxd-input-group__label-wrapper > label")
	WebElement SSN;
	
	/**
	 * @return name of SSN field to check with assert
	 */
	public boolean checkSSNvisibility() {
		boolean isInvisible = kw.waitInvisibilityOfElement(SSN);
		LOG.info("Check SSN is visible or hidden : " + (isInvisible ? "Hidden" : "Visible"));
		return isInvisible;
	}
	
	/**
	 * @return true if SSN field is displayed
	 */
	public boolean checkSSNdisplayed() {
		kw.waitForElementToBeVisible(SSN);
        boolean isDisplayed = SSN.isDisplayed();
        LOG.info("check SSN is diplayed : " + isDisplayed);
        return isDisplayed;
	}
	
	@FindBy(css="form > div:nth-child(3) > div:nth-child(3) > div:nth-child(2) > div > div.oxd-input-group__label-wrapper > label")
	WebElement  SIN;
	
	/**
	 * @return name of SIN field to check with assert
	 */
	public boolean checkSINvisibility() {
        boolean isInvisible = kw.waitInvisibilityOfElement(SIN);
        LOG.info("Check SIN is visible or hidden : " + (isInvisible ? "Hidden" : "Visible"));
        return isInvisible;
	}
	
	/**
	 * @return true if SIN field is displayed
	 */
	public boolean checkSINdisplayed() {
		kw.waitForElementToBeVisible(SIN);
		boolean isDisplayed = SIN.isDisplayed();
        LOG.info("Check SIN is displayed : " + isDisplayed);
        return isDisplayed;
	}
	
	

	@FindBy(css="form > div:nth-child(7) > div > div:nth-child(1) > div > div.oxd-input-group__label-wrapper > label")
	WebElement nickname;
	
	/**
	 * @return name of nickname field to check with assert
	 */
	public boolean checknicknameVisibility() {
		boolean isInvisible =  kw.waitInvisibilityOfElement(nickname);
		LOG.info("Check Nickname is visible or hidden "+ (isInvisible ? "Hidden":"Visible"));
		return isInvisible;
	}
	
	/**
	 * @return true if nickname and other fields are displayed
	 */
	public boolean checkNicknameDisplayed() {
		kw.waitForElementToBeVisible(nickname);
		boolean isDisplayed = nickname.isDisplayed();
        LOG.info("check nickname is displayed: " + isDisplayed);
        return isDisplayed;
	}

	
	@FindBy(css="div.orangehrm-edit-employee-navigation > div.orangehrm-tabs > div:nth-child(8) > a")
	WebElement TaxExemptionMenu;
	
	/**
	 * @return name of tax exemption field to check with assert
	 */
	public boolean checkTaxExemptionvisibility() {
		boolean isInvisible = kw.waitInvisibilityOfElement(TaxExemptionMenu);
        LOG.info("Tax exemption field visibility status: " + (isInvisible ? "Hidden" : "Visible"));
        return isInvisible;
	}
	
	/**
	 * @return true if tax exemption field is dispalyed
	 */
	public boolean checkTaxExemptionDisplayed() {
		kw.waitForElementToBeVisible(TaxExemptionMenu);
		boolean isDisplayed = TaxExemptionMenu.isDisplayed();
        LOG.info("Tax exemption field display status: " + isDisplayed);
        return isDisplayed;
	}
	
	@FindBy(css=" div.orangehrm-edit-employee-navigation > div.orangehrm-tabs > div:nth-child(11) > a")
	WebElement memberships ;
	
	/**
	 * click on membership option in user profile
	 */
	public void ClickOnMemebership() {
		kw.waitForElementToBeVisible(memberships);
		memberships.click();
		LOG.info("Clicked on membership menu.");
		
	}
	
	@FindBy(css="div.oxd-layout-context > div > div > div > div.orangehrm-edit-employee-content > div.orangehrm-custom-fields > div > form > div.oxd-form-row > div > div:nth-child(1) > div > div.oxd-input-group__label-wrapper > label")
	WebElement insuranceID ;
	
	/**
	 * @return name of insuranceID field to check with assert
	 */
	public boolean checkInsurandIDvisibility() {
		boolean isInvisible = kw.waitInvisibilityOfElement(insuranceID);
        LOG.info("Insurance ID field visibility status: " + (isInvisible ? "Hidden" : "Visible"));
        return isInvisible;
	}
	
	/**
	 * @return true if insuranceID field is dispalyed
	 */
	public boolean checkInsuranceIDDisplayed() {
		kw.waitForElementToBeVisible(insuranceID);
		boolean isDisplayed = insuranceID.isDisplayed();
        LOG.info("Insurance ID field display status: " + isDisplayed);
        return isDisplayed;
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(2) > div > div.oxd-input-group__label-wrapper > label")
	WebElement  officeTransport;
	
	/**
	 * @return name of insuranceID field to check with assert
	 */
	public boolean checkOfficeTransportvisibility() {
		boolean isInvisible = kw.waitInvisibilityOfElement(officeTransport);
        LOG.info("Office transport field visibility status: " + (isInvisible ? "Hidden" : "Visible"));
        return isInvisible;
	}
	
	/**
	 * @return true if insuranceID field is dispalyed
	 */
	public boolean checkOfficeTransportDisplayed() {
		kw.waitForElementToBeVisible(officeTransport);
		boolean isDisplayed = officeTransport.isDisplayed();
        LOG.info("Office transport field display status: " + isDisplayed);
        return isDisplayed;
	}
	
	
}

package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class UserProfile {
	
	public UserProfile() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	
	@FindBy(css="li[class='oxd-topbar-body-nav-tab --visited'] a[class='oxd-topbar-body-nav-tab-item']")
	WebElement EmployeeList ;
	
	public void clickOnEmployeeList() {
		kw.waitForElementToBeClickable(EmployeeList);
		EmployeeList.click();
	}
	
	@FindBy(css="div[role='rowgroup'] div:nth-child(1) div:nth-child(1) div:nth-child(3) div:nth-child(1)")
	WebElement user1 ;
	
	/**
	 * clicks on the first line of user list
	 */
	public void clickOnUser1() {
		kw.waitForElementToBeClickable(user1);
		user1.click();
	}
	
	@FindBy(css="div.orangehrm-edit-employee-navigation > div.orangehrm-tabs > div:nth-child(1) > a")
	WebElement PersonalDetails ;
	
	/**
	 * Clicks on personal details menu inside user profile
	 */
	public void clickOnPersonalDetails() {
		kw.waitForElementToBeClickable(PersonalDetails);
		PersonalDetails.click();
	}
	
	@FindBy(css="form > div:nth-child(3) > div:nth-child(3) > div:nth-child(1) > div > div.oxd-input-group__label-wrapper > label")
	WebElement SSN;
	
	/**
	 * @return name of SSN field to check with assert
	 */
	public boolean checkSSNvisibility() {
		return kw.waitInvisibilityOfElement(SSN);
	}
	
	@FindBy(css="form > div:nth-child(3) > div:nth-child(3) > div:nth-child(2) > div > div.oxd-input-group__label-wrapper > label")
	WebElement  SIN;
	
	/**
	 * @return name of SIN field to check with assert
	 */
	public boolean checkSINvisibility() {
		return kw.waitInvisibilityOfElement(SIN);
	}

	@FindBy(css="form > div:nth-child(7) > div > div:nth-child(1) > div > div.oxd-input-group__label-wrapper > label")
	WebElement nickname;
	
	/**
	 * @return name of nickname field to check with assert
	 */
	public boolean checknicknameVisibility() {
		return kw.waitInvisibilityOfElement(nickname);
	}
	
	@FindBy(css="div.orangehrm-edit-employee-navigation > div.orangehrm-tabs > div:nth-child(8) > a")
	WebElement TaxExemptionMenu;
	
	/**
	 * @return name of tax exemption field to check with assert
	 */
	public boolean checkTaxExemptionvisibility() {
		return kw.waitInvisibilityOfElement(TaxExemptionMenu);
	}
	
	
	
	
}

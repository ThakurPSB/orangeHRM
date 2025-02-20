package orangeHRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import orangeHRM.main.keywords;

public class userDropdownMenu {
	
	public userDropdownMenu() {
		PageFactory.initElements(keywords.driver, this);
	}
	
	keywords kw = new keywords();
	
	@FindBy(css=".oxd-userdropdown-name")
	WebElement userDropdown ;
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > header:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)")
	WebElement about;
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > header:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)")
	WebElement 	support ;
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > header:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(3) > a:nth-child(1)")
	WebElement changePassword;
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > header:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(4) > a:nth-child(1)")
	WebElement logoutButton ;
	
	@FindBy(css="#app > div.oxd-overlay.oxd-overlay--flex.oxd-overlay--flex-centered > div > div > div > div.orangehrm-modal-header > h6")
	WebElement aboutInfo;
	
	@FindBy(css=".oxd-dialog-close-button.oxd-dialog-close-button-position")
	WebElement closeAboutInfo ;
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > div > div > div:nth-child(2) > p.oxd-text.oxd-text--p.orangehrm-sub-title")
	WebElement supportInfo ;
	
	@FindBy(css=".oxd-text.oxd-text--h6.orangehrm-main-title")
	WebElement passwordChangePage ;
	
	/**
	 * clicks on user profile
	 */
	public void clickOnUserProfile() {
		kw.waitForElementToBeVisible(userDropdown);
		userDropdown.click();		
	}
	
	/**
	 * click on about in user profile
	 */
	public void clickOnAbout() {
		kw.waitForElementToBeClickable(about);
		about.click();
	}
	
	/**
	 * click on support option
	 */
	public void clickOnSupport() {
		kw.waitForElementToBeVisible(support);
		support.click();
	}
	
	/**
	 * click on change password
	 */
	public void clickOnChangePassword() {
		kw.waitForElementToBeClickable(changePassword);
		changePassword.click();
	}
	
	/**
	 * clicks on logout button
	 */
	public void logout() {
		kw.waitForElementToBeClickable(logoutButton);
		logoutButton.click();
	}
	
	/**
	 * @return String to show the info is visible for company.
	 */
	public boolean aboutInformation() {
		kw.waitForElementToBeVisible(aboutInfo);
		return aboutInfo.isDisplayed();
	}
	
	/**
	 * close the about info dispaly box.
	 */
	public void closeAboutInformatio() {
		kw.waitForElementToBeClickable(closeAboutInfo);
		closeAboutInfo.click();
	}
	
	/**
	 * @return String tp show the infor is visible for support
	 */
	public boolean supportInformation() {
		kw.waitForElementToBeVisible(supportInfo);
		return supportInfo.isDisplayed();
	}
	
	/**
	 * @return True if directed to the password change page.
	 */
	public boolean checkIfChangePasswordPage() {
		kw.waitForElementToBeVisible(passwordChangePage);
		return passwordChangePage.isDisplayed();
	}
	
	
	
	
}

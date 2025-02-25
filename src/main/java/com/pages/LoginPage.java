package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

/**
 * 
 */
public class LoginPage {

	public LoginPage() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	
	@FindBy(css = "button.oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button")
    WebElement login;

    @FindBy(css="input[placeholder='Password']")
    WebElement password;

    @FindBy(css="input[placeholder='Username']")
    WebElement username;
    
    @FindBy(css=".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module")
	WebElement dashboardLink ;
    
    @FindBy(css=".oxd-text.oxd-text--p.oxd-alert-content-text")
	WebElement  errorInvalidCredentials;
    
    @FindBy(css=".oxd-userdropdown-tab")
	WebElement userDropdown;
    
    @FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > header:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(1) > ul:nth-child(2) > li:nth-child(4) > a:nth-child(1)")
	WebElement logoutButton ;

    /**
     * Enter the user name and password
     * @param user pass
     */
    public void enterCredentials(String user, String pass) {
    	kw.waitForElementToBeVisible(username);
    	username.sendKeys(user);
    	kw.waitForElementToBeVisible(password);
    	password.sendKeys(pass);
	}
    
    /**
     * clicks on login button
     */
    public void ClickOnLoginButton() {
    	kw.waitForElementToBeClickable(login);
    	login.click();
	}
    
    
    /**
     * Checks if dashboard page has opened
     * @return text from the dashboard link
     */
    public String dashboardText() {
    	kw.waitForElementToBeVisible(dashboardLink);
    	return dashboardLink.getText();
	}
    
    /**
     * Checks if error message page has opened
     * @return text from the error
     */
    public String errorText() {
    	kw.waitForElementToBeVisible(errorInvalidCredentials);
    	return errorInvalidCredentials.getText();
	}
    
    /**
     * wait logout button to be visible and click on logout
     */
    public void clickOnLogoutButton() {
    	kw.waitForElementToBeClickable(userDropdown);
    	userDropdown.click();
    	kw.waitForElementToBeClickable(logoutButton);
    	logoutButton.click();
	}
    
    public void logMeIn() {
    	kw.waitForElementToBeVisible(username);
    	username.sendKeys("admin");
    	kw.waitForElementToBeVisible(password);
    	password.sendKeys("Piyush@2050");
    	login.click();
	}
   
}

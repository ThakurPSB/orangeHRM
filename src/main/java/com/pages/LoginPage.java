package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import com.main.Keywords;

/**
 * 
 */
public class LoginPage {
	
	//This variable allows Page object to use the keywords methods
	private Keywords kw;

	/*
	 * Constructor to initialize the PageFactory elements
	 * @param kw - Keywords class reference
	 * this.kw = key means Take the Keywords object passed into the constructor and 
	 * assign it to the page object’s private field, 
	 * so this page object can use it later.
	 */
	public LoginPage(Keywords key) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = key;
	}
	
	
	private static final Logger LOG = Logger.getLogger(LoginPage.class);
	
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
    
    @FindBy(css="a.oxd-userdropdown-link[href*=\"auth/logout\"]")
	WebElement logoutButton ;
    
    @FindBy(css="form:nth-child(2) > div:nth-child(2) > div:nth-child(1) > span:nth-child(3)")
    WebElement requiredUsernameError;
    
    
    
   

    /**
     * Enter the user name and password
     * @param user pass
     */
    public void enterCredentials(String user, String pass) {
    	kw.waitForElementToBeVisible(username);
    	username.sendKeys(user);
    	kw.waitForElementToBeVisible(password);
    	password.sendKeys(pass);
    	LOG.info("Successfully entered login credentials");
	}
    
    /**
     * clicks on login button
     */
    public void ClickOnLoginButton() {
    	kw.waitForElementToBeClickable(login);
    	login.click();
    	LOG.info("Successfully clicked on login button");
	}
    
    
    /**
     * Checks if dashboard page has opened
     * @return text from the dashboard link
     */
    public String dashboardText() {
    	kw.waitForElementToBeVisible(dashboardLink);
    	String text =  dashboardLink.getText();
    	LOG.info("Successfully logged in to the Dashboard page");
    	return text;
	}
    
    /**
     * Checks if error message page has opened
     * @return text from the error
     */
    public String errorText() {
    	kw.waitForElementToBeVisible(errorInvalidCredentials);
    	String text = errorInvalidCredentials.getText();
    	LOG.info("Successfully displayed error for invalid credentials ");
    	return text;
	}
    
    /**
     * wait logout button to be visible and click on logout
     */
    public void clickOnLogoutButton() {
    	kw.waitForElementToBeClickable(userDropdown);
    	userDropdown.click();
    	kw.waitForElementToBeClickable(logoutButton);
    	logoutButton.click();
    	LOG.info("Successfully clicked on Logout button");
	}
    
    public void logMeIn() {
    	kw.waitForElementToBeVisible(username);
    	username.sendKeys("admin");
    	kw.waitForElementToBeVisible(password);
    	password.sendKeys("Piyush@2050");
    	login.click();
    	LOG.info("Successfully Loogged in");
	}
    
    public void logMeInAsUser() {
    	kw.waitForElementToBeVisible(username);
    	username.sendKeys("dummyUser");
    	kw.waitForElementToBeVisible(password);
    	password.sendKeys("Dumadm@12");
    	login.click();
    	LOG.info("Successfully Loogged in as user");
	}
    
    public String usernameRequiredError() {
		kw.waitForElementToBeClickable(requiredUsernameError);
		String text = requiredUsernameError.getText();
		LOG.info("Successfully displayed error for required username field ");
		return text;
    }
    
    @FindBy(css=".oxd-main-menu-item")
	WebElement menuSearchResult ;
	
	@FindBy(css="input[placeholder='Search']")
	WebElement menuSearchField ;
	
	public void clickOnMenu(String menuName) {
		kw.waitForElementToBeClickable(menuSearchField);
		menuSearchField.click();
		menuSearchField.sendKeys(menuName);
		menuSearchResult.click();
		LOG.info("Successfully clicked on Menu "+menuName);
	}
	
   
}

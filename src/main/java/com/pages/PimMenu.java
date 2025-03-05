package com.pages;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.main.Keywords;

public class PimMenu {
	
	public PimMenu() {
		PageFactory.initElements(Keywords.driver, this);
	}
	
	Keywords kw = new Keywords();
	
	private static final Logger LOG = Logger.getLogger(PimMenu.class);
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > aside > nav > div.oxd-sidepanel-body > ul > li:nth-child(2) > a > span")
	WebElement PIMmenu ;
	
	/**
	 * clicks on PIM Menu from Dash-board.
	 */
	public void clickOnPIM() {
		kw.waitForElementToBeVisible(PIMmenu);
		PIMmenu.click();
		LOG.info("Successfully clicked the PIM Menu button.");
	}
	
	@FindBy(css="span[class='oxd-topbar-body-nav-tab-item']")
	WebElement configuration ;
	
	/**
	 * clicks on configuration sub menu in PIM
	 */
	public void clickOnConfiguration() {
		kw.waitForElementToBeVisible(configuration);
		configuration.click();
		LOG.info("Successfully clicked the configuration menu button.");
	}

	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > header > div.oxd-topbar-body > nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent.--visited > ul")
	List<WebElement> ConfigOptList ;
	
	
	/**
	 * Clicks on PIM>Configuration>Customfields
	 */
	public void clickOnCustomFields() {
		kw.waitForElementToBeVisible(ConfigOptList.get(1));
		ConfigOptList.get(1).click();
		LOG.info("Successfully clicked the custome field button.");
	}
	
	@FindBy(css="li[class='--active oxd-topbar-body-nav-tab --parent'] li:nth-child(1) a:nth-child(1)")
	WebElement optionalFields;
	
	/**
	 * click on the optional fields sub menu in configuration.
	 */
	public void clickOnOptionalFields() {
		kw.waitForElementToBeClickable(optionalFields);
		optionalFields.click();
		LOG.info("Successfully selected the optional filed option.");
	}
	
	
	@FindBy(css="form > div:nth-child(1) > div > div > div > label")
	WebElement showDeprecatedFieldsLabel;
	
	@FindBy(css="form > div:nth-child(1) > div > div > div > label > input[type=checkbox]")
	WebElement showDeprecatedFieldsInput;
	
	/**
	 * turn on Show Deprecated field button if its off.
	 */
	public void turnONshowDeprecatedField() {
		kw.waitForElementToBeClickable(showDeprecatedFieldsLabel);
		showDeprecatedFieldsLabel.click();
		if(!showDeprecatedFieldsInput.isSelected()) {
			LOG.info("Show Deprecated field Option is OFF Turning it ON");
			showDeprecatedFieldsLabel.click();
		}else {
			LOG.info("Show Deprecated field Option is already ON");
		}
	}
	
	/**
	 * turn OFF Show Deprecated field button if its ON.
	 */
	public void turnOFFshowDeprecatedField() {
		kw.waitForElementToBeClickable(showDeprecatedFieldsLabel);
		showDeprecatedFieldsLabel.click();
		if(showDeprecatedFieldsInput.isSelected()) {
			LOG.info("Show Deprecated field Option is ON Turning it OFF");
			showDeprecatedFieldsLabel.click();
		}else {
			LOG.info("Show Deprecated field Option is already OFF");
		}
	}
	
	@FindBy(css="div:nth-child(3) > div > div:nth-child(1) > div > label")
	WebElement SSNfieldLabel ;
	
	@FindBy(css="form > div:nth-child(3) > div > div:nth-child(1) > div > label > input[type=checkbox]")
	WebElement SSNfieldInput ;
	
	/**
	 * turn on show SSN field button if its off.
	 */
	public void turnONSSNfield() {
		kw.waitForElementToBeClickable(SSNfieldLabel);
		SSNfieldLabel.click();
		if(!SSNfieldInput.isSelected()) {
			LOG.info("SSN field Option is OFF Turning it ON");
			SSNfieldLabel.click();
		}else {
			LOG.info("SSN field Option is already ON");
		}
	}
	
	/**
	 * turn OFF show SSN field button if its ON.
	 */
	public void turnOFFSSNfield() {
		kw.waitForElementToBeClickable(SSNfieldLabel);
		SSNfieldLabel.click();
		if(SSNfieldInput.isSelected()) {
			LOG.info("SSN field Option is ON Turning it OFF");
			SSNfieldLabel.click();
		}else {
			LOG.info("SSN field Option is already OFF");
		}
	}
	
	@FindBy(css="form > div:nth-child(3) > div > div:nth-child(2) > div > label")
	WebElement SINfieldLabel ;
	
	@FindBy(css="form > div:nth-child(3) > div > div:nth-child(2) > div > label > input[type=checkbox]")
	WebElement SINfieldInput ;
	
	/**
	 * turn on show SIN field button if its off.
	 */
	public void turnONSINfield() {
		kw.waitForElementToBeClickable(SINfieldLabel);
		SINfieldLabel.click();
		if(!SINfieldInput.isSelected()) {
			LOG.info("SIN field Option is OFF Turning it ON");
			SINfieldLabel.click();
		}else {
			LOG.info("SIN field Option is already ON");
		}
	}
	
	/**
	 * turn OFF show SIN field button if its ON.
	 */
	public void turnOFFSINfield() {
		kw.waitForElementToBeClickable(SINfieldLabel);
		SINfieldLabel.click();
		if(SINfieldInput.isSelected()) {
			LOG.info("SIN field Option is ON Turning it OFF");
			SINfieldLabel.click();
		}else {
			LOG.info("SIN field Option is already OFF");
		}
	}
	
	
	@FindBy(css="form > div:nth-child(3) > div > div:nth-child(3) > div > label")
	WebElement UStaxExemptionMenuLabel ;
	
	@FindBy(css="form > div:nth-child(3) > div > div:nth-child(3) > div > label > input[type=checkbox]")
	WebElement UStaxExemptionMenuInput;
	

	/**
	 * turn on US Tax exemptions Menu field button if its off.
	 */
	public void turnONUStaxExemptionMenufield() {
		kw.waitForElementToBeClickable(UStaxExemptionMenuLabel);
		UStaxExemptionMenuLabel.click();
		if(!UStaxExemptionMenuInput.isSelected()) {
			LOG.info("Tax exemptions Menu field Option is OFF Turning it ON");
			UStaxExemptionMenuLabel.click();
		}else {
			LOG.info("Tax exemptions Menu field Option is already ON");
		}
	}
	
	/**
	 * turn OFF show Tax exemptions Menu field button if its ON.
	 */
	public void turnOFFUStaxExemptionMenufield() {
		kw.waitForElementToBeClickable(UStaxExemptionMenuLabel);
		UStaxExemptionMenuLabel.click();
		if(UStaxExemptionMenuInput.isSelected()) {
			LOG.info("Tax exemptions Menu field Option is ON Turning it OFF");
			UStaxExemptionMenuLabel.click();
		}else {
			LOG.info("Tax exemptions Menu field Option is already OFF");
		}
	}
	
	@FindBy(css="button[type='submit']")
	WebElement OptionalFieldsSaveButton;
	
	/**
	 * Click on save optional field preferences
	 */
	public void clickOnSaveButtonOptionalFidls() {
		kw.waitForElementToBeClickable(OptionalFieldsSaveButton);
		OptionalFieldsSaveButton.click();
		LOG.info("Successfully clicked optional field preferences.");
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
	
	@FindBy(css="oxd-topbar-body-nav-tab-link")
	List<WebElement> ConfigurationMenuList;
	
	public void ClickOnSubMenuConfiguration(String menu) {
		kw.waitForAllElementAreVisible(ConfigurationMenuList);
		for(WebElement e : ConfigurationMenuList) {
			if(e.getText().contains(menu)) {
				e.click();
				break;
			}
		}
	}
	
}

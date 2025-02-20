package orangeHRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import orangeHRM.main.keywords;

public class PIMMenu {
	
	public PIMMenu() {
		PageFactory.initElements(keywords.driver, this);
	}
	
	keywords kw = new keywords();
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > aside > nav > div.oxd-sidepanel-body > ul > li:nth-child(2) > a > span")
	WebElement PIMmenu ;
	
	/**
	 * clicks on PIM Menu from Dash-board.
	 */
	public void clickOnPIM() {
		kw.waitForElementToBeVisible(PIMmenu);
		PIMmenu.click();
	}
	
	@FindBy(css="span[class='oxd-topbar-body-nav-tab-item']")
	WebElement configuration ;
	
	/**
	 * clicks on configuration sub menu in PIM
	 */
	public void clickOnConfiguration() {
		kw.waitForElementToBeVisible(configuration);
		configuration.click();
	}
	
	@FindBy(css="li[class='--active oxd-topbar-body-nav-tab --parent'] li:nth-child(1) a:nth-child(1)")
	WebElement optionalFields;
	
	/**
	 * click on the optional fields sub menu in configuration.
	 */
	public void clickOnOptionalFields() {
		kw.waitForElementToBeClickable(optionalFields);
		optionalFields.click();
	}
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > label:nth-child(1) > span:nth-child(2)")
	WebElement showDeprecatedFields;
	
	/**
	 * turn on show deprecated fields radio button to show nick name, military services and smoker etc.
	 */
	public void turnOnShowDrprecatedField() {
		kw.waitForElementToBeClickable(showDeprecatedFields);
		showDeprecatedFields.click();
	}
	
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > label:nth-child(1) > span:nth-child(2)")
	WebElement SSNfield ;
	
	/**
	 * turn on show SSN field button
	 */
	public void turnOnSSNfield() {
		kw.waitForElementToBeClickable(SSNfield);
		SSNfield.click();
	}
	
	@FindBy(css="body > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > form:nth-child(3) > div:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > label:nth-child(1) > span:nth-child(2)")
	WebElement SINfield;
	
	/**
	 * turn on show SIN field button
	 */
	public void turnOnSINfield() {
		kw.waitForElementToBeClickable(SSNfield);
		SINfield.click();
	}
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > form > div:nth-child(3) > div > div:nth-child(3) > div > label > span")
	WebElement UStaxExemptionMenu ;
	
	/**
	 * turn on show US Tax Exception Menu button
	 */
	public void turnOnUStaxExemptionMenu() {
		kw.waitForElementToBeClickable(UStaxExemptionMenu);
		UStaxExemptionMenu.click();
	}
	
	@FindBy(css="button[type='submit']")
	WebElement OptionalFieldsSaveButton;
	
	/**
	 * Click on save optional field preferances
	 */
	public void clickOnSaveButtonOptionalFidls() {
		OptionalFieldsSaveButton.click();
	}

	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successfull toast message
	 */
	public boolean SaveToastMessageText() {
		kw.waitForElementToBeVisible(saveSuccessfullToast);
		return saveSuccessfullToast.isDisplayed();
	}
	
}

package com.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class AdminOrganizationMenu {
	
	//This variable allows Page object to use the keywords methods
	private Keywords kw;

	/*
	 * Constructor to initialize the PageFactory elements
	 * @param kw - Keywords class reference
	 * this.kw = key means Take the Keywords object passed into the constructor and 
	 * assign it to the page object’s private field, 
	 * so this page object can use it later.
	 */
	public AdminOrganizationMenu(Keywords key) {
		PageFactory.initElements(Keywords.driver, this);
		this.kw = key;
	}
	
	private static final Logger LOG = Logger.getLogger(AdminOrganizationMenu.class);
	
	@FindBy(css = "nav.oxd-topbar-body-nav li:nth-of-type(3) span.oxd-topbar-body-nav-tab-item")
	WebElement orgMenu;
	
	public void clickOnOrgMenu() {
		kw.waitForElementToBeVisible(orgMenu);
		orgMenu.click();
		LOG.info("Cliked on Organization Menu");
	}
	
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > div")
	WebElement pageLoadStatus ;
	
	@FindBy(css="div.oxd-table-body > div:nth-child(1) div.oxd-table-cell")
	List<WebElement> menuList ;
	
	public void clickOnOrgSubMenu(String optionText) {
		kw.waitForElementToBeVisible(orgMenu);
	    kw.scrollToElement(orgMenu);
	    kw.waitForElementToBeClickable(orgMenu);
	    orgMenu.click();
	    LOG.info("Clicked on the organization Menu in Admin");
	    // Find all menu options
	    kw.waitForAllElementAreVisible(menuList);
	    
	    List<WebElement> menuOptions = kw.getDriver().findElements(By.cssSelector("ul.oxd-dropdown-menu a.oxd-topbar-body-nav-tab-link"));

	    for (WebElement option : menuOptions) {
	        if (option.getText().trim().equals(optionText)) {
	            kw.waitForElementToBeClickable(option);
	            option.click();
	            LOG.info("Clicked on : " + optionText);
	            kw.waitForTextToBe(pageLoadStatus, optionText);
	            return;
	            
	        }
	    }
	    LOG.error("Option not found: " + optionText);
	}
	
	@FindBy(css="div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > div > div > label")
	WebElement editButton ;
	
	public void clickOnEditButton() {
		kw.waitForElementToBeVisibleShort(editButton, 5);
		editButton.click();
		LOG.info("Cliked on edit button in organization Menu");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement registrationNumber ;
	
	public void updateRegNum(String s) {
		kw.visibilityOfElementLocated(registrationNumber);
		kw.scrollToElement(registrationNumber);
		registrationNumber.click();
		registrationNumber.sendKeys(Keys.CONTROL + "A");
		registrationNumber.sendKeys(Keys.BACK_SPACE);
		registrationNumber.sendKeys(s);
		LOG.info("updated the registration number to "+s);
	}
	
	public boolean regNumberCheck(String s) {
		boolean isEqual = false;
		kw.visibilityOfElementLocated(registrationNumber);
		kw.scrollToElement(registrationNumber);
		String st = (String)kw.JSExecutor("return arguments[0].value;", registrationNumber);
		isEqual = st.equals(s);
		if(isEqual == true) {
			LOG.info("registration number matched");
			return isEqual;
		}else {
			LOG.info("registration number does not match "+st);
			return isEqual;
		}
		
		
	}
	
	@FindBy(css="div.organization-name-container input")
	WebElement  orgName;
	
	public void updateOrgName(String s) {
		kw.visibilityOfElementLocated(orgName);
		kw.scrollToElement(orgName);
		orgName.click();
		orgName.sendKeys(Keys.CONTROL + "A");
		orgName.sendKeys(Keys.BACK_SPACE);
		orgName.sendKeys(s);
		LOG.info("Updated organization name to "+s);
	}
	
	public boolean orgNameTextCheck(String s) {
		boolean isEqual = false;
		kw.visibilityOfElementLocated(orgName);
		kw.scrollToElement(orgName);
		String st = (String)kw.JSExecutor("return arguments[0].value;", orgName);
		isEqual = st.equals(s);
		if(isEqual == true) {
			LOG.info("Organization name matched");
			return isEqual;
		}else {
			LOG.info("Organization name does not match"+st);
			return isEqual;
		}
		
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(2) > div > div:nth-child(2) > input")
	WebElement taxID ;
	
	public void updateTaxID(String s) {
		kw.visibilityOfElementLocated(taxID);
		kw.scrollToElement(taxID);
		taxID.click();
		taxID.sendKeys(Keys.CONTROL + "A");
		taxID.sendKeys(Keys.BACK_SPACE);
		taxID.sendKeys(s); //PVT1419S8809
		LOG.info("Updated the Tax ID to "+s);
	}
	
	public boolean taxIDtextCheck(String s) {
		boolean isEqual = false;
		kw.visibilityOfElementLocated(taxID);
		kw.scrollToElement(taxID);
		String st = (String)kw.JSExecutor("return arguments[0].value;", taxID);
		isEqual = st.equals(s);
		if(isEqual == true) {
			LOG.info("TaxID number matched");
			return isEqual;
		}else {
			LOG.info("TaxID number does not match "+st);
			return isEqual;
		}
	}

	
	@FindBy(css="form > div.oxd-form-actions > button")
	WebElement saveButton ;
	
	public void clickOnSave() {
		kw.waitForElementToBeVisible(saveButton);
		kw.scrollToElement(saveButton);
		saveButton.click();
		LOG.info("Cliked on Save button");
	}
	
	@FindBy(css=".oxd-toast.oxd-toast--success.oxd-toast-container--toast")
	WebElement saveSuccessfullToast ;
	
	/**
	 * @return save successful toast message
	 * @throws TimeoutException 
	 */
	public boolean SaveToastMessageText() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(saveSuccessfullToast,5);
			kw.scrollToElement(saveSuccessfullToast);
			boolean isDisplayed = saveSuccessfullToast.isDisplayed();
			LOG.info("Successfully added orgnization details");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
		
		
	}
	
	@FindBy(css="form > div.oxd-form-row > div > div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement locationNameSearchText;
	
	@FindBy(css="div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement searchButton;
	
	public void searchLocation(String nm) {
		//kw.waitForElementToBeClickable(locationNameSearchText);
		//kw.scrollToElement(locationNameSearchText);
		locationNameSearchText.click();
		locationNameSearchText.sendKeys(nm);
		locationNameSearchText.sendKeys(Keys.TAB);
		searchButton.click();
		LOG.info("Successfully searched the location with name " + nm);
	}
	
	@FindBy(css=".oxd-toast.oxd-toast--error.oxd-toast-container--toast")
	WebElement errorToast ;
	
	/**
	 * Check if the element is present or not
	 * @return true if the error toast is displayed
	 * @throws TimeoutException
	 */
	public boolean errorToastMessageText() throws TimeoutException {
		try {
			kw.waitForElementToBeVisibleShort(errorToast,5);
			kw.scrollToElement(errorToast);
			boolean isDisplayed = errorToast.isDisplayed();
			LOG.info("Error toast found ");
			return isDisplayed;
		} catch (TimeoutException e) {
			LOG.warn("Toast message not found within timeout.");
	        
		}catch (NoSuchElementException e) {
	        LOG.warn("Toast element not found in DOM.");
	    }
		return false;
	}

	
	@FindBy(css="i.oxd-icon.bi-trash.oxd-button-icon")
	WebElement confirmDeleteYes ;
	
	
	public void deleteSelected(String loc) throws InterruptedException {
		kw.normalWait(1000);
		List<WebElement> searchResultList = kw.getDriver().findElements(By.cssSelector("div.oxd-table-row"));
		kw.waitForAllElementAreVisible(searchResultList);
		boolean isLocationFound = false;
		WebElement found = null;
		for(WebElement row: searchResultList) {
			if(row.getText().contains(loc)) {
				isLocationFound = true;
				found = row;
				break;
			}else {
				isLocationFound = false;
			}
		}
			if(isLocationFound) {
				LOG.info("Found the reporting Method");
				WebElement deleteButton = found.findElement(By.cssSelector("button.oxd-icon-button > i.oxd-icon.bi-trash"));
				deleteButton.click();
				if(!errorToastMessageText()) {
					kw.waitForElementToBeClickable(confirmDeleteYes);
			        confirmDeleteYes.click();
			        LOG.info("Successfully Deleted the location "+loc);
				}else {
					LOG.error("The location already in use");
				}
			}else {
				LOG.info("No such location found");
			}
			kw.waitInvisibilityOfElement(confirmDeleteYes);
	}
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div.orangehrm-paper-container > div.orangehrm-header-container > div > button")
	WebElement addLocationButton ;
	
	/**
	 * click on ths add location button in admin > organization > location menu.
	 */
	public void clickOnAddLocationButton() {
		kw.waitForElementToBeVisible(addLocationButton);
		kw.scrollToElement(addLocationButton);
		addLocationButton.click();
		LOG.info("Clicked on Add Location button ");
	}
	
	@FindBy(css="form > div:nth-child(1) > div > div > div > div:nth-child(2) > input")
	WebElement enterLocationName ;
	
	public void enterLocation(String name) {
		kw.waitForElementToBeClickable(enterLocationName);
		enterLocationName.sendKeys(name);
		LOG.info("Entered the location name ");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement enterCityName ;
	
	public void enterCity(String name) {
		kw.waitForElementToBeClickable(enterCityName);
		enterCityName.sendKeys(name);
		LOG.info("Entered the City Name ");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(2) > div > div:nth-child(2) > input")
	WebElement enterStateName ;
	
	public void enterState(String name) {
		kw.waitForElementToBeClickable(enterStateName);
		enterStateName.sendKeys(name);
		LOG.info("Entered the State name ");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(3) > div > div:nth-child(2) > input")
	WebElement enterZipCode ;
	
	public void enterZip(String name) {
		kw.waitForElementToBeClickable(enterZipCode);
		enterZipCode.sendKeys(name);
		LOG.info("Entered the Zip code ");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(4) > div > div:nth-child(2) > div > div")
	WebElement selectCountryName ;
	
	
	public void selectCountry(String country) throws InterruptedException {
		kw.waitForElementToBeClickable(selectCountryName);
		
		selectCountryName.click();
		List<WebElement> countries = kw.getDriver().findElements(By.cssSelector("div.oxd-select-option > span"));
		boolean found = false;
		
		for (WebElement option : countries) {
		    if (option.getText().equalsIgnoreCase(country)) {
		        kw.waitForElementToBeClickable(option);
		        option.click();
		        found = true;
		        LOG.info("Selected the Country ");
		        break;
		    }
		}
		if (!found) {
		    throw new RuntimeException("Country '" + country + "' not found in the dropdown.");
		}
		
	}
	
	@FindBy(css="button[type=\"submit\"]")
	WebElement locationSaveButton ;
	
	public void clickOnSaveLocationButton() {
		kw.waitForElementToBeClickable(locationSaveButton);
		locationSaveButton.click();
		LOG.info("Clicked on Save button and Saved the location ");
	}
	
	
	
	
	
}

package com.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;

public class PerformanceMenu {
	
	//This variable allows Page object to use the keywords methods
	private Keywords kw;

	//Constructor to initialize the Page object and its elements
	public PerformanceMenu(Keywords key) {
		this.kw = key;
	    PageFactory.initElements(kw.getDriver(), this);
	}
	
	private static final Logger LOG = Logger.getLogger(PerformanceMenu.class);
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > aside > nav > div.oxd-sidepanel-body > ul > li:nth-child(7) > a")
	WebElement performanceTab ;
	
	public void clickOnPerformanceMenu() {
		kw.waitForElementToBeClickable(performanceTab);
		kw.scrollToElement(performanceTab);
		performanceTab.click();
		LOG.info("Successfully clicked on Performance tab");
	}
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > header > div.oxd-topbar-body > nav > ul > li:nth-child(1) > span")
	WebElement performanceConfigure  ;

	public void clickOnPerformanceConfigure() throws InterruptedException {
		kw.waitForElementToBeInvisible(loader);
		kw.waitForElementToBeClickable(performanceConfigure);
		kw.scrollToElement(performanceConfigure);
		kw.normalWait(200);
		performanceConfigure.click();
		LOG.info("Successfully clicked on Configure in performance menu");
	}
	
	@FindBy(css="button[class = 'oxd-icon-button oxd-table-cell-action-space']:nth-of-type(2)")
	WebElement deleteKPIIcon ;
	
	@FindBy(css="button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	WebElement confirmDeleteKPI ;
	
	public void clickOnDeleteKPIIcon() {
		kw.waitForElementToBeInvisible(loader);
		kw.waitForElementToBeClickable(deleteKPIIcon);
		kw.scrollToElement(deleteKPIIcon);
		deleteKPIIcon.click();
		kw.waitForElementToBeClickable(confirmDeleteKPI);
		confirmDeleteKPI.click();
		LOG.info("Successfully clicked on delete KPI button");
	}
	
	@FindBy(css="#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-navigation > header > div.oxd-topbar-body > nav > ul > li.--active.oxd-topbar-body-nav-tab.--parent > ul > li:nth-child(1)")
	WebElement KPIs ;
	
	public void clickOnKPIs() {
		kw.waitForElementToBeClickable(KPIs);
		kw.scrollToElement(KPIs);
		KPIs.click();
		LOG.info("Successfully clicked on KPIs option in configure");
	}
	
	@FindBy(css="div > div.orangehrm-paper-container > div.orangehrm-header-container > button")
	WebElement  addKPIbutton;
	
	public void clickOnAddKPIButton() {
		kw.waitForElementToBeVisible(addKPIbutton);
		kw.scrollToElement(addKPIbutton);
		addKPIbutton.click();
		LOG.info("Successfully clicked on add KPI button");
	}
	
	@FindBy(css="form > div:nth-child(1) > div > div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement keyPerformanceIndicator ;
	
	public void enterKeyPerformanceIndicator(String s) {
		kw.waitForElementToBeClickable(keyPerformanceIndicator);
		keyPerformanceIndicator.click();
		keyPerformanceIndicator.sendKeys(s);
		LOG.info("Successfully entered the KPI "+s);
	}
	
	@FindBy(css="form > div:nth-child(1) > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div.oxd-select-text-input")
	WebElement performanceKPIJobTitle ;
	
	@FindBy(css="form > div:nth-child(1) > div > div:nth-child(2) > div > div:nth-child(2) > div > div.oxd-select-dropdown.--positon-bottom")
	WebElement performanceKJobTitleList ;
	
	public void selecrJobTitleToAddKPI() throws InterruptedException {
		kw.waitForElementToBeClickable(performanceKPIJobTitle);
		performanceKPIJobTitle.click();
		kw.waitForElementToBeVisible(performanceKJobTitleList);
		performanceKPIJobTitle.sendKeys(Keys.chord("q"));
		performanceKPIJobTitle.sendKeys(Keys.ENTER);
		LOG.info("Successfully updated the job Title for KPI");
	}
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(1) > div > div:nth-child(2) > input")
	WebElement 	minRating ;
	
	@FindBy(css="form > div:nth-child(2) > div > div:nth-child(2) > div > div:nth-child(2) > input")
	WebElement maxRating ;
	
	public void enterMinAndMaxRatings() {
		kw.waitForElementToBeClickable(minRating);
		minRating.sendKeys("1");
		maxRating.sendKeys("5");
		LOG.info("Uppdated min rating 1 and max rating 5");
	}
	
	@FindBy(css="form > div.oxd-form-actions > button.oxd-button.oxd-button--medium.oxd-button--secondary.orangehrm-left-space")
	WebElement saveKPIButton ;
	
	public void clickOnSaveKPIButton() {
		kw.waitForElementToBeClickable(saveKPIButton);
		saveKPIButton.click();
		LOG.info("Successfully added the KPI for QA Engineer");
	}
	
	@FindBy(css="div.oxd-table-body > div:nth-child(1) div.oxd-table-cell")
	List<WebElement> tablerow ;
	
	/**
	 * Gets the fresh element css selector reference to avoid stale element error
	 * @param index
	 * @return
	 */
	public WebElement getTableRow(int index) {
	    List<WebElement> freshTableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div:nth-child(1) div.oxd-table-cell"));
	    if (index < freshTableRows.size()) {
	        return freshTableRows.get(index);
	    } else {
	        throw new IndexOutOfBoundsException("Table row index out of bounds");
	    }
	}
	
	/**
	 * first saved the locatorCss into By object and waited till table is visible
	 * Then made a list of table rows and named tableRows 
	 * Then checked if table has 0 rows or is empty
	 * waiting again for table element to be visible
	 * saved the single element of the list of element in row to temp
	 * method getTableRow(take argument as index) and get that element from row
	 * 
	 */
	public boolean searchResultKPI(String s) {
		
		//as the findElement running too fast hence need to load the css selector and check the visibility first
		By locatorcss = By.cssSelector("div.oxd-table-body > div");
		kw.waitForElementToBeVisible(locatorcss);
		
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));
	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(1); 
	        kw.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched KPI: " + text);
	        return text.equals(s);
	    } else {
	        return false;
	    }
	}
	
    @FindBy(css="button.oxd-button.oxd-button--medium.oxd-button--label-danger.orangehrm-button-margin")
	WebElement  confirmDelete;
	
	public void searchResultKPIAndDelete(String s) {
		
		//as the findElement running too fast hence need to load the css selector and check the visibility first
		By locatorcss = By.cssSelector("div.oxd-table-body > div");
		List<WebElement> elements = kw.getDriver().findElements(locatorcss);
		if (!elements.isEmpty()) {
		    kw.waitForElementToBeVisible(locatorcss);
		    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));
		    if (tableRows != null && !tableRows.isEmpty()) {
		        kw.waitForAllElementAreVisible(tableRows);
		        WebElement temp = getTableRow(1); 
		        kw.scrollToElement(temp);
		        String text = temp.getText();
		        LOG.info("Successfully Searched KPI, deleting it " + text);
		        if( text.equals(s)) {
		        	WebElement delete = getTableRow(6);
		        	kw.waitForElementToBeVisible(delete);
		        	delete.click();
		        	kw.waitForElementToBeVisible(confirmDelete);
		        	kw.waitForElementToBeClickable(confirmDelete);
		        	kw.scrollToElement(confirmDelete);
		        	confirmDelete.click();
		        }
		    }
		} else {
		    LOG.info("Table not present. Skipping wait.");
		}
	    
	}
	
	@FindBy(css=".oxd-form-loader")
	WebElement loader;

	    
	
}

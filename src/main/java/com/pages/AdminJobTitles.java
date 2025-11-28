package com.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.main.Keywords;


public class AdminJobTitles {
	
	//This variable allows Page object to use the keywords methods
		private Keywords kw;

	/*
	 * Constructor to initialize the Page object and its elements
	 */
	public AdminJobTitles(Keywords key) {
		this.kw = key;
	    PageFactory.initElements(kw.getDriver(), this);
	}
	
	private static final Logger LOG = Logger.getLogger(AdminJobTitles.class);
	
	@FindBy(css="div.oxd-sidepanel-body > ul > li:nth-child(1) > a > span")
	WebElement adminMenu ;
	
	/**
	 * clicks on Admin menu of sidebar
	 */
	public void clickOnAdminMenu() {
		kw.waitForElementToBeVisible(adminMenu);
		kw.scrollToElement(adminMenu);
		adminMenu.click();
		LOG.info("Cliked on Admin Menu");
	}
	
	@FindBy(css = "nav.oxd-topbar-body-nav li:nth-of-type(2) span.oxd-topbar-body-nav-tab-item")
	WebElement jobMenu;
	
	
	
	public void clickOnJobSubMenu(String optionText) {
		kw.waitForElementToBeVisible(jobMenu);
	    kw.scrollToElement(jobMenu);
	    kw.waitForElementToBeClickable(jobMenu);
	    jobMenu.click();
	    LOG.info("Clicked on the Job Menu in Admin");
	    // Find all menu options
	    kw.waitForAllElementAreVisible(menuList);
	    
	    List<WebElement> menuOptions = kw.getDriver().findElements(By.cssSelector("ul.oxd-dropdown-menu a.oxd-topbar-body-nav-tab-link"));

	    for (WebElement option : menuOptions) {
	        if (option.getText().trim().equals(optionText)) {
	            kw.waitForElementToBeClickable(option);
	            kw.scrollToElement(option);
	            option.click();
	            LOG.info("Clicked on : " + optionText);
	            kw.waitForTextToBe(pageLoadStatus, optionText);
	            return;
	            
	        }
	    }
	    LOG.error("Option not found: " + optionText);
	}
	
	@FindBy(css="div.oxd-table-body > div:nth-child(1) div.oxd-table-cell")
	List<WebElement> menuList ;
	
	/**
	 *
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
	
	@FindBy(css="div.oxd-layout-container > div.oxd-layout-context > div > div > div.orangehrm-header-container > h6")
	WebElement pageLoadStatus ;
	
		
	public boolean checkElementinTable(String element) throws InterruptedException  {
		
		kw.normalWait(1000);
		
	    // Refresh table rows to avoid stale element issue
	    List<WebElement> tableRows = kw.getDriver().findElements(By.cssSelector("div.oxd-table-body > div"));
	    if (tableRows != null && !tableRows.isEmpty()) {
	        kw.waitForAllElementAreVisible(tableRows);
	        WebElement temp = getTableRow(1);
	        kw.scrollToElement(temp);
	        String text = temp.getText();
	        LOG.info("Successfully Searched employee with status: " + text);
	        return text.contains(element);
	    } else {
	        return false;
	    }
	}
	
	
	

}

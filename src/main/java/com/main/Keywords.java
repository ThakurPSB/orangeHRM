package com.main;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


public class Keywords {

	// static instances of the remote web driver and fluentwait
	public static RemoteWebDriver driver = null;
	public static FluentWait<WebDriver> wait = null;
	
	//logger instance for the keyword class
	private static final Logger LOG = Logger.getLogger(Keywords.class);
	
	/**
	 * launch the given URK
	 * @param url link
	 */
	public void launchURL(String url) {
		driver.get(url);
	}
	
	/**
	 * launch the browser and set up the wait parameters
	 * @param browser name
	 * @return launch browser and initiate the waits
	 */
	public void launchBrowser(String browserName) {
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			LOG.info("Launched Chrome Browser");
		} else if(browserName.equalsIgnoreCase("Edge")) {
			driver= new EdgeDriver();
			LOG.info("Launched Edge Browser");
		} else if(browserName.equalsIgnoreCase("Firefox")) {
			driver= new FirefoxDriver();
			LOG.info("Launched firefox Browser");
		} else {
			LOG.error("Invalid browser name");
		}
		
		//setting fluent wait parameters
		wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(15));
		wait.pollingEvery(Duration.ofMillis(500));
		wait.ignoring(NoSuchElementException.class);
		
	}
	
	/**
	 * wait for element till its visible
	 * @param web element
	 * @return web element when visible
	 */
	public WebElement waitForElementToBeVisible(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * wait for the element till its click-able
	 * @param element
	 * @return web element when click-able
	 */
	public WebElement waitForElementToBeClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	
	
	/**
	 * clicks on the element
	 * @param element
	 */
	public void clickOn(WebElement element) {
		element.click();
	}
	
	/**
	 * enter the text in the text box
	 * @param element
	 * @param text
	 */
	public void enterText(WebElement element, String text) {
		element.sendKeys(text);
	}
	
}

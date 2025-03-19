package com.main;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


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
		LOG.info("Navigated to URL: " + url);
	}
	
	/**
	 * launch the browser and set up the wait parameters
	 * @param browser name
	 * @return launch browser and initiate the waits
	 */
	public void launchBrowser(String browserName) {
		
		if(browserName.equalsIgnoreCase("Chrome")) {
		
	        ChromeOptions options = new ChromeOptions();

	        driver = new ChromeDriver(options);

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
		
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
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
	public WebElement waitForElementToBeVisible(WebElement element)  {
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
	 * @param element
	 * @return true if webelement is invisible
	 */
	public boolean waitInvisibilityOfElement(WebElement element) {
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	/**
	 * @param li list of webelement 
	 * waits till all the elements of the list are visiboe
	 */
	public void waitForAllElementAreVisible(List<WebElement> li) {
		wait.until(ExpectedConditions.visibilityOfAllElements(li));
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
	
	/**
	 * @param miliseconds
	 * @throws InterruptedException
	 */
	public void normalWait(long milisec) throws InterruptedException {
		Thread.sleep(milisec);
	}
	
	/**
	 * @param css selector . 
	 * @return list of webelement
	 */
	public List<WebElement> presenceOfAllElement(String s) {
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(s)));
	}
	
	
	/**
	 * @param webelement
	 * @return webelement when its present
	 */
	public WebElement visibilityOfElementLocated (WebElement e) {
		return wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	/**
     * Waits for an element to be visible with a short timeout.
     * @param element WebElement to check visibility
     * @param timeoutSeconds Timeout duration in seconds
     * @return true if element is visible, false if not found within timeout
     */
    public boolean waitForElementToBeVisibleShort(WebElement element, int timeoutSeconds) throws TimeoutException{
        try {
        	WebDriverWait shortWait = new WebDriverWait (driver, Duration.ofSeconds(timeoutSeconds));
            shortWait.until(ExpectedConditions.visibilityOf(element));
            return true; // Element is visible
        } catch (TimeoutException e) {
            return false; // Element is not found within timeout
        }
    }
	
    /**
     * Scrolls the page to a specific WebElement
     * @param element The WebElement to scroll to
     */
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }
    
    public void scrollToElement(List<WebElement> elist) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", elist);
    }
    
    
    /**
     * This method check if the list is displayed or not, its empty or not
     * @param elements
     * @return
     */
    public boolean isElementListPresent(List<WebElement> elements) {
    	try {
            return elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false; 
        }
	}
    
 
    
    /**
     * @return driver instance to use in other classes
     */
    public WebDriver getDriver() {
        return driver;
    }

    public void waitForClipBoardText(String text) {
    	int attempts = 0;
        while (attempts < 10) {
            try {
                String clipboardText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                if (clipboardText.equals(text)) {
                    return;
                }
            } catch (Exception ignored) {}
            attempts++;
            try {
                Thread.onSpinWait(); // More efficient than sleep, avoids blocking
            } catch (Exception ignored) {}
        }
	}
    
}

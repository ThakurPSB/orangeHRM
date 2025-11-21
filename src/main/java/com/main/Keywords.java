package com.main;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;


public class Keywords {

	// static instances of the remote web driver and fluentwait
	public static RemoteWebDriver driver = null;
	public static WebDriverWait wait = null;
	
	//logger instance for the keyword class
	private static final Logger LOG = Logger.getLogger(Keywords.class);
	
	//private constructor to prevent external instantiation
	private Keywords() {}
	
	
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
	        //options.addArguments("--headless=new"); // Use newer headless mode
	        options.addArguments("--window-size=1920,1080"); // Set proper resolution

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
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
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
	
	//overloading application for By locator
	public void waitForElementToBeVisible(By locator) {
	    new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.visibilityOfElementLocated(locator));
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
     * Executes the script on given web element
     * @param script
     * @param element
     * @return 
     */
    public Object JSExecutor(String script , WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        @Nullable Object s = js.executeScript(script, element);
		return s;
        
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
    
    //method to take screenshot when test case fail or broke
    public void captureScreenShot(String testname) throws IOException {
    	
    	try{
    	
	    	LocalDateTime time = LocalDateTime.now();
	    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
	    	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
	    	
	    	String dateFolder = time.format(dateFormatter);
	    	String timeStamp = time.format(timeFormatter);
	    	
	    	Path path = Paths.get("screenshots/", dateFolder);
	    	Files.createDirectories(path);
	    	
	    	AShot ashot = new AShot();
	    	Screenshot sc = ashot.shootingStrategy(ShootingStrategies.viewportPasting(2000)).takeScreenshot(driver);
	
	        BufferedImage image = sc.getImage();
	        File outputFile = new File("screenshots/" + dateFolder + "/Screenshot_" + testname + "_" + timeStamp + ".png");
	        ImageIO.write(image, "png", outputFile);
	
	        LOG.info("Screenshot saved at: " + outputFile.getAbsolutePath());
	    }
    	catch(IOException e) {
    		
    		LOG.error("Error while taking screenshot: " + e.getMessage());
    		
    	}
    	
	}
    
    //return the running instance of the driver 
    private static Keywords instance;
    public static Keywords getInstance() {
    	if (instance == null) {
            instance = new Keywords();
        }
        return instance;
    }
    
    /**
     * This method wait for element text to be displayed using get text 
     * @param element
     * @param expectedText
     */
    public void waitForTextToBe(WebElement element, String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }
    
    /**
     * This method checks if the element is present on the page
     * @param element
     */
	public boolean isElementPresent(WebElement element) {
		try {
	        return element != null && element.isDisplayed();
	    } catch (NoSuchElementException | StaleElementReferenceException e) {
	        return false;
	    }
	}
	
	/**
	 * This method clear the text box
	 * 
	 */
	public void clearTextBox(WebElement element) {
		element.click();
		element.sendKeys(Keys.LEFT_CONTROL + "A");
		element.sendKeys(Keys.DELETE);
		LOG.info("Successfully cleared text box");
	}
	
}

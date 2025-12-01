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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class Keywords {
	
	// static instances of the remote web driver and wait
	//public static RemoteWebDriver driver = null;
	//public static WebDriverWait wait = null;
	
	private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    private static final ThreadLocal<FluentWait<RemoteWebDriver>> fluentWait = new ThreadLocal<>();
    private static final ThreadLocal<Keywords> instance = ThreadLocal.withInitial(Keywords::new);
    //in above line we have used Thread local to make sure each thread has its own instance of driver and wait.
    //
    /* instance simplification - 
     * ThreadLocal checks if this thread already has a value.
     * If NOT, it runs: Keywords::new
     * which is equivalent to: () -> new Keywords()
     * Stores that object only for this thread and return it.
     * second time getInstance is called from the same thread
     * it simply returns the stored value. does not create a new one.
     * 
     * */
    
    
    public static Keywords getInstance() {
        return instance.get();
    }
    
    //getter method for the driver
    public RemoteWebDriver getDriver() { 
    	return driver.get(); 
    }
    
    public static void reset() {
        driver.remove();
        wait.remove();
        fluentWait.remove();
        instance.remove();  
    }
    
    //getter method for the wait
    public WebDriverWait getWait() {
    	return wait.get(); 
    }
    
    //getter method for the fluent wait
    public Wait<RemoteWebDriver> getFluentWait() {
        return fluentWait.get();
    }

	
	//logger instance for the keyword class
	private static final Logger LOG = Logger.getLogger(Keywords.class);
	
	//public constructor
	public Keywords() {}
	
	
	
	/**
	 * launch the given URK
	 * @param url link
	 */
	public void launchURL(String url) {
		getDriver().get(url);
		LOG.info("Navigated to URL: " + url);
	}
	
	/**
	 * launch the browser and set up the wait parameters
	 * @param browser name
	 * @return launch browser and initiate the waits
	 */
	public void launchBrowser(String browserName) {
		
		RemoteWebDriver dr = null;
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");

	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized");
	        dr = new ChromeDriver(options);
			LOG.info("Launched Chrome Browser");
			
		} else if(browserName.equalsIgnoreCase("Edge")) {
			
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/msedgedriver.exe");
			
			EdgeOptions options = new EdgeOptions();
		    options.addArguments("--start-maximized");
			dr = new EdgeDriver(options);
			dr.manage().window().setSize(new Dimension(1920,1080));
			LOG.info("Launched Edge Browser");
			
		} else if(browserName.equalsIgnoreCase("Firefox")) {
			
			System.setProperty("webdriver.firefox.driver",System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver.exe");
			
			FirefoxOptions options = new FirefoxOptions();
		    options.addArguments("--start-maximized");
		    
			dr = new FirefoxDriver(options);
			dr.manage().window().setSize(new Dimension(1920,1080));
			LOG.info("Launched firefox Browser");
			
		} else {
			LOG.error("Invalid browser name");
			return;
		}
		
		//Assigning driver to Thread local and setting options
		driver.set(dr);
		// Apply driver-level settings
		dr.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		dr.manage().window().maximize();
		
		//Assigning explicit wait to Thread local
		wait.set(new WebDriverWait(dr, Duration.ofSeconds(30)));

		//Assigning wait to Thread local
		fluentWait.set(new FluentWait<>(dr)
		        .withTimeout(Duration.ofSeconds(30))
		        .pollingEvery(Duration.ofMillis(500))
		        .ignoring(NoSuchElementException.class));
		
	}
	
	public void quitBrowser() {
		try {
	        if (getDriver() != null) {
	            getDriver().quit();
	        }
	    } finally {
	        driver.remove();
	        wait.remove();
	        fluentWait.remove();
	    }
	}

	
	/**
	 * wait for element till its visible
	 * @param web element
	 * @return web element when visible
	 */
	public WebElement waitForElementToBeVisible(WebElement element)  {
		return getFluentWait().until(ExpectedConditions.visibilityOf(element));
	}
	
	//overloading application for By locator
	public void waitForElementToBeVisible(By locator) {
	    new WebDriverWait(getDriver(), Duration.ofSeconds(10))
	        .until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * wait for the element till its click-able
	 * @param element
	 * @return web element when click-able
	 */
	public WebElement waitForElementToBeClickable(WebElement element) {
		return getFluentWait().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	/**
	 * @param element
	 * @return true if webelement is invisible
	 */
	public boolean waitInvisibilityOfElement(WebElement element) {
		return getFluentWait().until(ExpectedConditions.invisibilityOf(element));
	}
	
	/**
	 * @param li list of webelement 
	 * waits till all the elements of the list are visiboe
	 */
	public void waitForAllElementAreVisible(List<WebElement> li) {
		getFluentWait().until(ExpectedConditions.visibilityOfAllElements(li));
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
		return getFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(s)));
	}
	
	
	/**
	 * @param webelement
	 * @return webelement when its present
	 */
	public WebElement visibilityOfElementLocated (WebElement e) {
		return getFluentWait().until(ExpectedConditions.visibilityOf(e));
	}
	
	/**
     * Waits for an element to be visible with a short timeout.
     * @param element WebElement to check visibility
     * @param timeoutSeconds Timeout duration in seconds
     * @return true if element is visible, false if not found within timeout
     */
    public boolean waitForElementToBeVisibleShort(WebElement element, int timeoutSeconds) throws TimeoutException{
        try {
        	WebDriverWait shortWait = new WebDriverWait (getDriver(), Duration.ofSeconds(timeoutSeconds));
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
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }
    
    
    public void scrollToElement(List<WebElement> elist) {
    	if (elist != null && !elist.isEmpty()) {
            scrollToElement(elist.get(0));
        }
    }
    
    /**
     * Executes the script on given web element
     * @param script
     * @param element
     * @return 
     */
    public Object JSExecutor(String script , WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        @Nullable Object s = js.executeScript(script, element);
		return s;
        
    }
    
    
    /**
     * This method check if the list is displayed or not, its empty or not
     * @param elements
     * @return boolean
     */
    public boolean isElementListPresent(List<WebElement> elements) {
    	try {
            return elements != null && !elements.isEmpty() && elements.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
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
	    	Screenshot sc = ashot.shootingStrategy(ShootingStrategies.viewportPasting(2000)).takeScreenshot(getDriver());
	
	        BufferedImage image = sc.getImage();
	        File outputFile = new File("screenshots/" + dateFolder + "/Screenshot_" + testname + "_" + timeStamp + ".png");
	        ImageIO.write(image, "png", outputFile);
	
	        LOG.info("Screenshot saved at: " + outputFile.getAbsolutePath());
	    }
    	catch(IOException e) {
    		
    		LOG.error("Error while taking screenshot: " + e.getMessage());
    		
    	}
    	
	}
    
   
    
    /**
     * This method wait for element text to be displayed using get text 
     * @param element
     * @param expectedText
     */
    public void waitForTextToBe(WebElement element, String expectedText) {
    	getFluentWait().until(ExpectedConditions.textToBePresentInElement(element, expectedText));
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
	 * This method wait for the page url to be changed
	 */
	public void waitForUrlToBeChanged(String oldUrl) {
		getFluentWait().until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
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
	
	/**
	 * This method get the text of the current url
	 * @return current url
	 */
	public String getCurrentURL() {
	        return getDriver().getCurrentUrl();
	}

	/**
	 * This method wait for the element to be invisible
	 * 
	 */
	public void waitForElementToBeInvisible(WebElement element) {
		getFluentWait().until(ExpectedConditions.invisibilityOf(element));
	}
	
}

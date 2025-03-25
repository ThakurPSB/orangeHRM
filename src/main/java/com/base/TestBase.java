package com.base;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.BeforeSuite;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.main.Keywords;

public class TestBase {
	
	private static final Logger LOG = Logger.getLogger(TestBase.class);
	
	/**
	 * delete the old allure reports before running the build
	 */
	@BeforeSuite
    public void cleanAllureResults() {
        try {
            File allureResults = new File("allure-results");
            File allureReport = new File("allure-report");
            FileUtils.deleteDirectory(allureResults);
            FileUtils.deleteDirectory(allureReport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	/**
	 * launch the browser before each test case
	 * @throws Exception
	 * ( String url, String browserName)
	 */
	@BeforeMethod
	@Parameters({"browserName", "url"})
	public void setUp(@Optional("Chrome") String browserName, @Optional("http://localhost/orangehrm") String url) throws Exception {
		kw = new Keywords();
		kw.launchBrowser(browserName);
		kw.launchURL(url);
		LOG.info("Successfully launched the web Application");
	}
	
	/**
	 * close the browser after each test case
	 */
	@AfterMethod
	public void closeBrowser() {
		Keywords.driver.quit();
		LOG.info("Successfully quite the browser");
	}

}

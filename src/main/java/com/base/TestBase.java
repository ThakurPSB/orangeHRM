package com.base;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.BeforeSuite;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.main.Keywords;
import com.util.PropertiesUtil;

public class TestBase {
	
	protected Keywords kw;
	
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
	public void setUp() throws IOException {
		kw = Keywords.getInstance();
		kw.launchBrowser(PropertiesUtil.getProperty("browser"));
		kw.launchURL(PropertiesUtil.getProperty("local.url"));
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

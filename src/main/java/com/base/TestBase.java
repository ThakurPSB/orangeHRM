package com.base;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.main.Keywords;
import com.util.PropertiesUtil;

public class TestBase {
	
	
	/**
	 * launch the browser before each test case
	 * @throws Exception
	 */
	@BeforeMethod
	@Parameters({"browserName", "url"})
	public void setUp(String browserName, String url) throws Exception {
		Keywords kw = new Keywords();
		kw.launchBrowser(browserName);
		kw.launchURL(url);
	}
	
	/**
	 * close the browser after each test case
	 */
	@AfterMethod
	public void closeBrowser() {
		Keywords.driver.quit();
	}

}

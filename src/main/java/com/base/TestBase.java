package com.base;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.main.Keywords;
import com.util.PropertiesUtil;

public class TestBase {
	
	
	/**
	 * launch the browser before each test case
	 * @throws Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		Keywords kw = new Keywords();
		kw.launchBrowser("Chrome");
		kw.launchURL(PropertiesUtil.getURL("local.url"));
	}
	
	/**
	 * close the browser after each test case
	 */
	@AfterMethod
	public void closeBrowser() {
		Keywords.driver.quit();
	}

}

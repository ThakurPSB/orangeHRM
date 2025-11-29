package com.hooks;

import org.apache.log4j.Logger;

import com.base.TestBase;
import com.main.Keywords;
import com.util.PropertiesUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	public static Keywords key;
	
	private static final Logger LOG = Logger.getLogger(TestBase.class);
	
	@Before
	public void setUp() throws Exception {
		key = new Keywords();      
		key.launchBrowser(PropertiesUtil.getProperty("browser"));
		key.launchURL(PropertiesUtil.getProperty("local.url"));
		LOG.info("Successfully launched the web Application");
	}
	
	@After
	public void tearDown() {
		key.quitBrowser();
	}
	

}

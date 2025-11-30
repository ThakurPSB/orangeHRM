package com.hooks;

import org.apache.log4j.Logger;

import com.base.TestBase;
import com.main.Keywords;
import com.pages.LoginPage;
import com.util.PropertiesUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	public static Keywords key;
	
	private static final Logger LOG = Logger.getLogger(TestBase.class);
	
	@Before(order = 0)
	public void setUp() throws Exception {
		key = new Keywords();      
		key.launchBrowser(PropertiesUtil.getProperty("browser"));
		key.launchURL(PropertiesUtil.getProperty("local.url"));
		LOG.info("Successfully launched the web Application");
	}
	
	/*
	 * This method will log in as user or admin 
	 * When @userLogin tag is present - login as user
	 * otherwise login as admin
	 */
	@Before(order = 1)
	public void setUpScenario(Scenario scenario) {
		LoginPage login = new LoginPage(Hooks.key);
	    if (scenario.getSourceTagNames().contains("@userLogin")) {
	        login.logMeInAsUser();
	    } else {
	        login.logMeIn();
	    }
	}
	
	@After
	public void tearDown() {
		key.quitBrowser();
	}
	

}

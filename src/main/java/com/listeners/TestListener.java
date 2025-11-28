package com.listeners;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.main.Keywords;

public class TestListener implements ITestListener{
	
	private static final Logger LOG = Logger.getLogger(TestListener.class);
    
    @Override
    public void onTestFailure(ITestResult result) {
        Keywords kw = Keywords.getInstance() ; // Get the existing instance of Keywords
        
        try {
            String testName = result.getName();
            kw.captureScreenShot(testName); // Capture screenshot with test name
            LOG.error("Test Failed: " + testName);
        } catch (IOException e) {
            LOG.error("Error capturing screenshot for: " + result.getName(), e);
        }
    }
}

package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.stepdefinition", "com.hooks"},
    plugin = {"pretty", 
    		"html:target/cucumber-report.html", 
    		"json:target/cucumber.json",
    		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    		 },
    monochrome = true,
    tags = "@performance",
    dryRun = false
        
)

public class CucumberTests extends AbstractTestNGCucumberTests {
	
	
	
}
package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.StepDefinition", "com.hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true,
        tags = "",
        monochrome = true, 
        strict = true,
        publish = true,
        failFast = true
        
)

public class CucumberTests extends AbstractTestNGCucumberTests {
	
	
	
}
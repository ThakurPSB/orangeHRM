package orangeHRM.testCases;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import orangeHRM.base.testBase;
import orangeHRM.pages.loginPage;

public class testSprint1 extends testBase{
	private static final Logger LOG = Logger.getLogger(testSprint1.class);
	
	@Test
	public void CheckIfLoginSuccessfullWithCorrectCredentials() {
		loginPage login = new loginPage();
		
		HashMap <String ,String> credentials = new HashMap<>();
		
		credentials.put("admin", "admin123");
		credentials.put("admin", "admin45");
		credentials.put("user1", "admin123");
		
		for(Map.Entry<String,String> entry: credentials.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("admin") && entry.getValue().equalsIgnoreCase("admin123")){
				login.enterCredentials(entry.getKey(), entry.getValue());
				login.ClickOnLoginButton();
				Assert.assertEquals(login.dashboardText(), "Dashboard");
				login.clickOnLogoutButton();
				LOG.info("Test Passed - Login succesfull with correct credentials");
			}
			else {
				login.enterCredentials(entry.getKey(), entry.getValue());
				login.ClickOnLoginButton();
				Assert.assertEquals(login.errorText(), "Invalid credentials");
				LOG.info("Test Passed - Login unsuccesfull with incorrect credentials");
			}
		}
	}

	

}

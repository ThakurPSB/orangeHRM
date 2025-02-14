package orangeHRM.base;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import orangeHRM.main.keywords;

public class testBase {
	
	
	/**
	 * launch the browser before each test case
	 * @throws Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		keywords kw = new keywords();
		kw.launchBrowser("Chrome");
		kw.launchURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	/**
	 * close the browser after each test case
	 */
	@AfterMethod
	public void closeBrowser() {
		keywords.driver.quit();
	}

}

package com.base;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.main.Keywords;
import com.pages.AddEmployeePage;
import com.pages.AdminJobTitles;
import com.pages.AdminMenu;
import com.pages.AdminOrganizationMenu;
import com.pages.AdminQualificationMenu;
import com.pages.DashboardMenu;
import com.pages.EmployeeListMenu;
import com.pages.LeaveApplyMenu;
import com.pages.LeaveEntitlementMenu;
import com.pages.LeaveMyLeaveMenu;
import com.pages.LoginPage;
import com.pages.PerformanceMenu;
import com.pages.PimCustomFields;
import com.pages.PimMenu;
import com.pages.RecruitmentMenu;
import com.pages.ReportingMethod;
import com.pages.TerminationReasons;
import com.pages.TimeMenu;
import com.pages.UserDropdownMenu;
import com.pages.UserProfile;
import com.util.PropertiesUtil;

public class TestBase {
	
	//declaring key variable of type Keywords class which is null until declared. 
	protected Keywords key;
	
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
	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser"})
	public void setUp(@Optional String browserFromXml) throws IOException {
		
		String finalBrowser;

		if (browserFromXml != null && !browserFromXml.isEmpty()) {
		    finalBrowser = browserFromXml;  // use XML
		} else {
		    finalBrowser = PropertiesUtil.getProperty("browser"); // fallback
		}
		
		//its now points to the shared instance of Keywords class.
		key = new Keywords();
		
		key.launchBrowser(finalBrowser);
		key.launchURL(PropertiesUtil.getProperty("local.url"));
		
		LOG.info("Successfully launched the web Application");
	}
	
	
	//Lazy factory design pattern
	protected LoginPage login() {
		return new LoginPage(key);
	}

	protected UserDropdownMenu profile() {
		return new UserDropdownMenu(key);
	}
	
	protected PimMenu pim() {
		return new PimMenu(key);
	}
	
	protected UserProfile user() {
		return new UserProfile(key);
	}
	
	protected PimCustomFields customField() {
		return new PimCustomFields(key);
	}
	
	protected ReportingMethod reportingMethod() {
		return new ReportingMethod(key);
	}
	
	protected TerminationReasons terminationReasons() {
		return new TerminationReasons(key);
	}

	protected EmployeeListMenu emplist() {
		return new EmployeeListMenu(key);
	}

	protected AddEmployeePage addemp() {
		return new AddEmployeePage(key);
	}
	
	protected AdminMenu admin() {
		return new AdminMenu(key);
	}
	
	protected AdminJobTitles jobs() {
		return new AdminJobTitles(key);
	}
	
	protected AdminOrganizationMenu orgMenu() {
		return new AdminOrganizationMenu(key);
	}
	
	protected AdminQualificationMenu qualification() {
		return new AdminQualificationMenu(key);
	}
	
	protected LeaveApplyMenu leaveApply() {
		return new LeaveApplyMenu(key);
	}
	
	protected LeaveMyLeaveMenu myLeave() {
		return new LeaveMyLeaveMenu(key);
	}
	
	protected LeaveEntitlementMenu entitlement() {
		return new LeaveEntitlementMenu(key);
	}
	
	protected RecruitmentMenu recruitment() {
		return new RecruitmentMenu(key);
	}
	
	protected PerformanceMenu performance() {
		return new PerformanceMenu(key);
	}
	
	protected TimeMenu timemenu() {
		return new TimeMenu(key);
	}
	
	protected DashboardMenu dashboard() {
		return new DashboardMenu(key);
	}
	
	
	
	/**
	 * close the browser after each test case for each thread.
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
	    key.quitBrowser();
	    LOG.info("Successfully quit the browser");
	}

	
	




}

package com.base;
import java.io.File;
import java.io.IOException;
import org.testng.annotations.BeforeSuite;
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
	public void setUp() throws IOException {
		
		//its now points to the shared instance of Keywords class.
		key = Keywords.getInstance();
		
		key.launchBrowser(PropertiesUtil.getProperty("browser"));
		key.launchURL(PropertiesUtil.getProperty("local.url"));
		LOG.info("Successfully launched the web Application");
	}
	
	
	//Lazy factory design pattern
	protected LoginPage login() {
		return new LoginPage();
	}

	protected UserDropdownMenu profile() {
		return new UserDropdownMenu();
	}
	
	protected PimMenu pim() {
		return new PimMenu();
	}
	
	protected UserProfile user() {
		return new UserProfile();
	}
	
	protected PimCustomFields customField() {
		return new PimCustomFields();
	}
	
	protected ReportingMethod reportingMethod() {
		return new ReportingMethod();
	}
	
	protected TerminationReasons terminationReasons() {
		return new TerminationReasons();
	}

	protected EmployeeListMenu emplist() {
		return new EmployeeListMenu();
	}

	protected AddEmployeePage addemp() {
		return new AddEmployeePage();
	}
	
	protected AdminMenu admin() {
		return new AdminMenu();
	}
	
	protected AdminJobTitles jobs() {
		return new AdminJobTitles();
	}
	
	protected AdminOrganizationMenu orgMenu() {
		return new AdminOrganizationMenu();
	}
	
	protected AdminQualificationMenu qualification() {
		return new AdminQualificationMenu();
	}
	
	protected LeaveApplyMenu leaveApply() {
		return new LeaveApplyMenu();
	}
	
	protected LeaveMyLeaveMenu myLeave() {
		return new LeaveMyLeaveMenu();
	}
	
	protected LeaveEntitlementMenu entitlement() {
		return new LeaveEntitlementMenu();
	}
	
	protected RecruitmentMenu recruitment() {
		return new RecruitmentMenu();
	}
	
	protected PerformanceMenu performance() {
		return new PerformanceMenu();
	}
	
	protected TimeMenu timemenu() {
		return new TimeMenu();
	}
	
	protected DashboardMenu dashboard() {
		return new DashboardMenu();
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

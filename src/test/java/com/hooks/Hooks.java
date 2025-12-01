package com.hooks;

import org.apache.log4j.Logger;

import com.base.TestBase;
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

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	public static Keywords key;
	private static final Logger LOG = Logger.getLogger(TestBase.class);
	
	@Before(order = 0)
	public void setUp() throws Exception {
		key = Keywords.getInstance();      
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
	    } else if (scenario.getSourceTagNames().contains("@noLogin")){
	        return ;
		} else {
			login.logMeIn();
		}
	}
	
	@After
	public void tearDown() {
		try {
	        if (key != null && key.getDriver() != null) {
	            key.quitBrowser();
	        }
	    } finally {
	        Keywords.reset();  // important
	    }
		LOG.info("Successfully quit the browser");
	}
	
	//Lazy factory design pattern
		public LoginPage login() {
			return new LoginPage(Hooks.key);
		}

		public UserDropdownMenu profile() {
			return new UserDropdownMenu(Hooks.key);
		}
		
		public PimMenu pim() {
			return new PimMenu(Hooks.key);
		}
		
		public UserProfile user() {
			return new UserProfile(Hooks.key);
		}
		
		public PimCustomFields customField() {
			return new PimCustomFields(Hooks.key);
		}
		
		public ReportingMethod reportingMethod() {
			return new ReportingMethod(Hooks.key);
		}
		
		public TerminationReasons terminationReasons() {
			return new TerminationReasons(Hooks.key);
		}

		public EmployeeListMenu emplist() {
			return new EmployeeListMenu(Hooks.key);
		}

		public AddEmployeePage addemp() {
			return new AddEmployeePage(Hooks.key);
		}
		
		public AdminMenu admin() {
			return new AdminMenu(Hooks.key);
		}
		
		public AdminJobTitles jobs() {
			return new AdminJobTitles(Hooks.key);
		}
		
		public AdminOrganizationMenu orgMenu() {
			return new AdminOrganizationMenu(Hooks.key);
		}
		
		public AdminQualificationMenu qualification() {
			return new AdminQualificationMenu(Hooks.key);
		}
		
		public LeaveApplyMenu leaveApply() {
			return new LeaveApplyMenu(Hooks.key);
		}
		
		public LeaveMyLeaveMenu myLeave() {
			return new LeaveMyLeaveMenu(Hooks.key);
		}
		
		public LeaveEntitlementMenu entitlement() {
			return new LeaveEntitlementMenu(Hooks.key);
		}
		
		public RecruitmentMenu recruitment() {
			return new RecruitmentMenu(Hooks.key);
		}
		
		public PerformanceMenu performance() {
			return new PerformanceMenu(Hooks.key);
		}
		
		public TimeMenu timemenu() {
			return new TimeMenu(Hooks.key);
		}
		
		public DashboardMenu dashboard() {
			return new DashboardMenu(Hooks.key);
		}
	

}

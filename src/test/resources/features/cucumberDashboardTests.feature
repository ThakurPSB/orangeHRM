@regression @dashboard
Feature: Dashboard Module

Background:

@sanity
Scenario: About link opens the About Information page
	When user clicks on the user profile
	And user clicks on about option
	Then about information should be displayed
	And user closes about information

@sanity
Scenario: Support link opens the Support Information page
	When user clicks on the user profile
	And user clicks on support option
	Then support information should be displayed

@smoke
Scenario: Change Password link opens the password change page
	When user clicks on the user profile
	And user clicks on change password option
	Then change password page should be displayed

@userLogin @smoke
Scenario: Punch In link navigates to the Time module Punch In page
	When user clicks on search in sidebar for menu and click "Dashboard"
	And user clicks on punch in button
	Then user should be navigated to url "http://localhost/orangehrm/web/index.php/attendance/punchIn"
	
@userLogin @smoke
Scenario: Timesheet link navigates to the Timesheet module page
	When user clicks on search in sidebar for menu and click "Dashboard"
	And user clicks on my timesheet shortcut on dashboard
	Then user should be navigated to url "http://localhost/orangehrm/web/index.php/time/viewMyTimesheet"
	
@userLogin @smoke
Scenario: Apply leave link navigates to the Leave module page
	When user clicks on search in sidebar for menu and click "Dashboard"
	And user clicks on apply leave shortcut on dashboard
	Then user should be navigated to url "http://localhost/orangehrm/web/index.php/leave/applyLeave"
	
	
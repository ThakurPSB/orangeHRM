@dashboard
Feature: Dashboard Module

Background:

Scenario: To verify when clicked on 'About' link 'About information' page opens
	When user clicks on the user profile
	And user clicks on about option
	Then about information should be displayed
	And user closes about information

Scenario: To verify when clicked on 'Support' link 'Support information' page opens
	When user clicks on the user profile
	And user clicks on support option
	Then support information should be displayed

@smoke
Scenario: To verify when clicked on 'Change password' link 'password change' page opens
	When user clicks on the user profile
	And user clicks on change password option
	Then change password page should be displayed

@userLogin @smoke
Scenario: To verify when clicked on puchIn link it directs to Time module punchIn page
	When user clicks on search in sidebar for menu and click "Dashboard"
	And user clicks on punch in button
	Then user should be navigated to url "http://localhost/orangehrm/web/index.php/attendance/punchIn"
	
@userLogin @smoke
Scenario: To verify when clicked on timesheet link it directs to Timesheet Module page
	When user clicks on search in sidebar for menu and click "Dashboard"
	And user clicks on my timesheet shortcut on dashboard
	Then user should be navigated to url "http://localhost/orangehrm/web/index.php/time/viewMyTimesheet"
	
@userLogin @smoke
Scenario: To verify when clicked on apply Leave link it directs to Leave Module page
	When user clicks on search in sidebar for menu and click "Dashboard"
	And user clicks on apply leave shortcut on dashboard
	Then user should be navigated to url "http://localhost/orangehrm/web/index.php/leave/applyLeave"
	
	
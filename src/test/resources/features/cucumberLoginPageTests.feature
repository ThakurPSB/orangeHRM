@regression @login
Feature: Login Functionality
  Verify that valid users can log into OrangeHRM successfully.
  
Background:

@noLogin @smoke
Scenario Outline: User logs in successfully with valid credentials
  When user enters username "<username>" and password "<password>"
  And user clicks on login button
  Then dashboard should be displayed
  Examples:
  | username 			| password  	|
  | Admin    			| Piyush@2050 |
  | DummyUser   	| Dumadm@12 	|

@noLogin @smoke
Scenario: Error message is displayed when invalid credentials are entered
	When user enters username "admin" and password "admin123"
	And user clicks on login button
	Then invalid credential error should be displayed
	
@noLogin @smoke
Scenario: Error message is displayed when credentials are left blank
	When user enters username "" and password ""
	And user clicks on login button
	Then username required error should be displayed
	


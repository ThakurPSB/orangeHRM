@smoke @login
Feature: Login Functionality
  Verify that valid users can log into OrangeHRM successfully.
  
Background:

Scenario Outline: To verify when valid creadentials entered user logi successfully
  When user enters username "<username>" and password "<password>"
  And user clicks on login button
  Then dashboard should be displayed
  Examples:
  | username 			| password  	|
  | Admin    			| Piyush@2050 |
  | DummyUser   	| DumAdm@12 	|
    
Scenario: To verfiy when invalid credentials entered 'Invalid Credentials' error is shown
	When user enters username "admin" and password "admin123"
	And user clicks on login button
	Then invalid credential error should be displayed
	
Scenario: To veify when credentials left blank invalid credentials error is shown
	When user enters username "" and password ""
	And user clicks on login button
	Then username required error should be displayed
	


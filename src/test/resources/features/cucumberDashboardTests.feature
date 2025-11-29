@smoke @dashboard
Feature: To verify all links in profile menu working

Background:
    Given admin logs in with default credentials

Scenario: To verify when clicked on 'About' link 'About information' page opens
	When user clicks on the user profile
	And user clicks on about option
	Then about information should be displayed
	And user closes about information

Scenario: To verify when clicked on 'Support' link 'Support information' page opens
	When user clicks on the user profile
	And user clicks on support option
	Then support information should be displayed

Scenario: To verify when clicked on 'Change password' link 'password change' page opens
	When user clicks on the user profile
	And user clicks on change password option
	Then change password page should be displayed

@employee
Feature: Employee Module

Background:

Scenario: To verify when employee searched with "Employee ID" shows correct result
	When user clicks on PIM menu
	And user opens employee list menu
	And user enters employee id "68166"
	And user clicks search button
	Then search result should match employee id "68166"
	
Scenario Outline: To verify when employee searched with "Employee first/last name" shows correct result
	When user clicks on PIM menu
	And user opens employee list menu
	And user enters employee name "Piyush"
	And user clicks search button
	Then search result should match first name "Piyush"
	And user clicks reset search button
	And user opens employee list menu
	And user enters employee name "Thakur"
	And user clicks search button
	Then search result should match last name "Thakur"
  
	
Scenario: To verify when employee searched with "Employment status" shows correct result
	When user clicks on PIM menu
	And user opens employee list menu
	And user selects employment status "Active"
	And user clicks search button
	Then search result should match employment status "Active"
	
Scenario: To verify when employee searched with "Supervisor Name" shows correct result
	When user clicks on PIM menu
	And user opens employee list menu
	And user enters supervisor name "Piyush"
	And user clicks search button
	Then search result should match supervisor "Piyush Thakur"
	
Scenario: To verify when employee searched with "Job Title" shows correct result
	When user clicks on PIM menu
	And user opens employee list menu
	And user selects job title "Executive"
	And user clicks search button
	Then search result should match job title "Executive"
	
Scenario: To verify when employee searched with "SubUnit" shows correct result
	When user clicks on PIM menu
	And user opens employee list menu
	And user selects sub unit "Mumbai"
	And user clicks search button
	Then search result should match sub unit "Mumbai"
	

@smoke
Scenario: To verify when adding users all options working correctly
	When user clicks on PIM menu
	And user opens employee list menu
	And user enters employee name "Wisley"
	And user clicks search button
	And user deletes selected employees
	And user clicks on PIM menu
	And user clicks on add new employee button
	And user selects profile picture
	And user enters employee first name "George" middle name "L" last name "Wisley"
	And user enters unique employee id
	And user enables create login details
	And user enters login username "George" password "User@2025" and status "false"
	And user clicks save employee button
	Then employee should be saved successfully
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
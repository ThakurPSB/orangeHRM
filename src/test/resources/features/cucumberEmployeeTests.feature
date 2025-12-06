@regression @employee
Feature: Employee Module

Background:


Scenario: Search results are displayed correctly when filtered by Employee ID
	When user clicks on PIM menu
	And user opens employee list menu
	And user enters employee id "68166"
	And user clicks search button
	Then search result should match employee id "68166"
	
Scenario Outline: Search results are displayed correctly when filtered by Employee first or last name
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
  
	
Scenario: Search results are displayed correctly when filtered by Employment Status
	When user clicks on PIM menu
	And user opens employee list menu
	And user selects employment status "Active"
	And user clicks search button
	Then search result should match employment status "Active"
	
Scenario: Search results are displayed correctly when filtered by Supervisor Name
	When user clicks on PIM menu
	And user opens employee list menu
	And user enters supervisor name "Piyush"
	And user clicks search button
	Then search result should match supervisor "Piyush Thakur"
	
Scenario: Search results are displayed correctly when filtered by Job Title
	When user clicks on PIM menu
	And user opens employee list menu
	And user selects job title "Executive"
	And user clicks search button
	Then search result should match job title "Executive"
	
Scenario: Search results are displayed correctly when filtered by Sub Unit
	When user clicks on PIM menu
	And user opens employee list menu
	And user selects sub unit "Mumbai"
	And user clicks search button
	Then search result should match sub unit "Mumbai"
	

@smoke
Scenario: All fields function correctly when adding a new user
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
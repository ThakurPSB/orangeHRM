@regression @admin
Feature: Admin Module

Background:

@smoke
Scenario: Search results are displayed correctly when searched by username
	When user clicks on admin menu
	And user enters admin Module username "admin"
	And user clicks on admin Module search button
	Then username search result should show username "admin"

@smoke
Scenario: Search results display only users with the selected role
	When user clicks on admin menu
	And user selects admin Module user role "ESS"
	And user clicks on admin Module search button
	Then admin search result should show role "ESS"
	
Scenario: Search results display only the selected employee
	When user clicks on admin menu
	And user enters employee name "Piyush" in search
	And user clicks on admin Module search button
	Then admin search result should show employee name "Piyush Thakur"
	
Scenario: Search results display only users with the selected login status
	When user clicks on admin menu
	And user selects admin Module login status "Enabled"
	And user clicks on admin Module search button
	Then admin search result should show status "Enabled"

@smoke
Scenario: Job submenu displays the correct detail options
	When user clicks on admin menu
	And user selects job submenu option "Job Titles"
	Then job table should contain entry "Assistant Manager"
	And user selects job submenu option "Pay Grades"
	Then job table should contain entry "Grade A"
	And user selects job submenu option "Employment Status"
	Then job table should contain entry "Active"
	
	
Scenario: Organization details are updated and displayed correctly
	When user clicks on admin menu
	And user clicks on organization main menu
	And user selects organization sub menu option "General Information"
	And user clicks on organization edit button
	And user updates registration number to "123456"
	And user updates organization name to "PsychoCorps"
	And user updates tax id to "PVT1419S8809"
	And user saves organization info
	Then Saved Successfully message should be displayed
	Then organization name should update to "PsychoCorps"
	Then tax id should update to "PVT1419S8809"
	Then registration number should update to "123456"
	
Scenario: Updated location details are displayed correctly
	When user clicks on admin menu
	And user clicks on organization main menu
	And user selects organization sub menu option "Locations"
	And user searches for location named "MumbaiCBO"
	And user deletes location named "MumbaiCBO"
	And user clicks on add new location button
	And user enters location name "MumbaiCBO"
	And user enters city name "Mumbai"
	And user enters state name "MH"
	And user enters zip code "400521"
	And user selects country "India"
	And user clicks on save location button
	Then location should be saved successfully
	
Scenario: Newly added skills are displayed correctly
	When user clicks on admin menu
	And user clicks on qualification main menu
	And user opens skills menu
	And user deletes skill named "java"
	And user clicks on add skill button
	And user enters skill name "java"
	And user saves the skill
	Then skill should be saved successfully
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
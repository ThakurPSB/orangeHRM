@admin
Feature: Admin Module

Background:

Scenario: To verify when searched with userName search result displayed correctly
	When user clicks on admin menu
	And user enters admin Module username "admin"
	And user clicks on admin Module search button
	Then username search result should show username "admin"
	
Scenario: To verify when seardched with user role search result display for that role only
	When user clicks on admin menu
	And user selects admin Module user role "ESS"
	And user clicks on admin Module search button
	Then admin search result should show role "ESS"
	
Scenario: To verify when seardched with employee name search result only show that employee
	When user clicks on admin menu
	And user enters employee name "Piyush" in search
	And user clicks on admin Module search button
	Then admin search result should show employee name "Piyush Thakur"
	
Scenario:To verify when seardched with login status search result display for login status
	When user clicks on admin menu
	And user selects admin Module login status "Enabled"
	And user clicks on admin Module search button
	Then admin search result should show status "Enabled"
	
Scenario:To verify Job submenu contains correct respective detail options
	When user clicks on admin menu
	And user selects job submenu option "Job Titles"
	Then job table should contain entry "Assistant Manager"
	And user selects job submenu option "Pay Grades"
	Then job table should contain entry "Grade A"
	And user selects job submenu option "Employment Status"
	Then job table should contain entry "Active"
	
	
Scenario: To veify organization details reflecting correctly after editing
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
	
Scenario: To verify location details updated are reflecting correctly
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
	
Scenario: To verify skills added reflecting in the field
	When user clicks on admin menu
	And user clicks on qualification main menu
	And user opens skills menu
	And user deletes skill named "java"
	And user clicks on add skill button
	And user enters skill name "java"
	And user saves the skill
	Then skill should be saved successfully
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
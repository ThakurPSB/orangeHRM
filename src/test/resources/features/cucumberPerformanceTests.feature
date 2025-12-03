@performance

Feature: Performance moduel

Background: 

Scenario: Add new KPI for the job title
	When user opens performance menu
	And user clicks performance configure
	And user selects KPIs option
	And user deletes KPI "Bug Detection Rate"
	And user clicks performance configure
	And user selects KPIs option
	And user clicks add KPI button
	And user enters KPI name "Bug Detection Rate"
	And user selects job title for KPI
	And user enters min and max ratings
	And user saves the KPI
	And user clicks performance configure
	Then "Bug Detection Rate" should be added successfully in list
	And user clicks KPI delete icon
	
	
	


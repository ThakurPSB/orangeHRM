@regression @time-entry
Feature: TimeEntry module

Background:

@noLogin @smoke
Scenario: Admin approves a timesheet after it is created, edited, and submitted
	# creating time sheet
	When normal user logs in with default credentials
	And user navigates to menu "Time"
	And user clicks create or edit timesheet button
	And user deletes existing timesheet entry
	And user enters project name "S"
	And user selects activity "c"
	And user enters hours for monday and tuesday
	And user clicks save timesheet button
	Then success toast should be displayed
	
	# editing time sheet
	When user navigates to menu "Time"
	And user notes current monday hours
	And user clicks edit timesheet button
	And user updates monday hours
	And user clicks save timesheet button
	Then monday hours should be updated
	And user clicks submit timesheet button
	And user logs out
	
	# admin approve or reject the timesheet
	When admin logs in with default credentials
	And user navigates to menu "Time"
	And user clicks view timesheet button
	And user clicks reject timesheet button
	Then success toast should be displayed
	


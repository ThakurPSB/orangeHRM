@regression @pim-menu

Feature: PIM Module

Background:

Scenario: Optional fields can be enabled in the PIM configuration
	When user clicks on PIM menu
	And user clicks on PIM configuration
	And user clicks on optional fields in configuration
	And user turns on show deprecated fields
	And user turns on SSN field
	And user turns on SIN field
	And user turns on US tax exemption menu field
	And user saves optional field preferences
	Then optional field preferences should be saved successfully
	
Scenario: Changes to optional fields in PIM configuration are reflected in the user profile
	
	#Fields off
	When user clicks on PIM menu
	And user clicks on PIM configuration
	And user clicks on optional fields in configuration
	And user turns off show deprecated fields
	And user turns off SSN field
	And user turns off SIN field
	And user turns off US tax exemption menu field
	And user saves optional field preferences
	And user clicks on PIM menu
	And user clicks on employee list
	And user clicks on first employee record
	Then SSN field should be hidden
	Then SIN field should be hidden
	Then nickname field should be hidden
	Then tax exemption field should be hidden
	
	#Fields on
	When user clicks on PIM menu
	And user clicks on PIM configuration
	And user clicks on optional fields in configuration
	And user turns on show deprecated fields
	And user turns on SSN field
	And user turns on SIN field
	And user turns on US tax exemption menu field
	And user saves optional field preferences
	And user clicks on PIM menu
	And user clicks on employee list
	And user clicks on first employee record
	Then SSN field should be visible
	Then SIN field should be visible
	Then nickname field should be visible
	Then tax exemption field should be visible


Scenario: Custom fields can be added with both input types
	
	#Deleting existing to add for testing purpose
	When user clicks on PIM menu
	And user clicks on PIM configuration
	And user clicks on custom fields in configuration
	And user deletes custom field named "InsuranceID"
	And user deletes custom field named "OfficeTransport"
	
	#Creating custom fields with text type input
	And user clicks on PIM menu
	And user clicks on PIM configuration
	#And user clicks on custom fields in configuration
	And user clicks on sub field in configuration "Custom Fields"
	And user clicks on add custom field button
	And user enters custom field name "InsuranceID"
	And user selects screen option
	And user selects input type text
	And user saves the custom field
	Then optional field preferences should be saved successfully
	
	#creating custom fields with dropdown type input
	When user clicks on add custom field button
	And user enters custom field name "OfficeTransport"
	And user selects screen option
	And user selects input type dropdown
	And user enters custom field dropdown options "YES,NO"
	And user saves the custom field
	Then optional field preferences should be saved successfully
	
	#checking custom fields added on user profile 
	When user clicks on PIM menu
	And user clicks on employee list
	And user clicks on first employee record
	And user opens membership section
	Then insurance ID field should be visible
	Then office transport field should be visible
	
	
	
	
	
	

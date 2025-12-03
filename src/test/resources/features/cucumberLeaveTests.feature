@leave
Feature: Leave Module

Background: 

@noLogin @smoke
Scenario: Apply leave with valid inputs
	When admin logs in with default credentials
  And user clicks on menu "Leave"
  And user cancels leave if already taken
  And user logs out
  And normal user logs in with default credentials
  And user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects leave type "e"
  And user selects from date
  And user selects to date
  And user applies for leave
  Then leave should be applied successfully
  
  
Scenario: Apply leave with overlapping dates
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects leave type "e"
  And user selects from date
  And user selects to date
  And user applies for leave
  And user navigates to apply leave page
  And user selects leave type "e"
  And user selects from date
  And user selects to date
  And user applies for leave
  Then system should show failed to apply leave toast
  When user clicks on menu "Leave"
  And user opens My Leave menu
  And user cancels all applied leaves
  Then all leaves should be cancelled if available
  
Scenario: Apply leave without selecting leave type
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects from date
  And user selects to date
  And user applies for leave
  Then warning for leave type should be displayed

Scenario: Apply leave with Non Working day dates
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects leave type "e"
  And user selects Saturday from date
  And user selects Sunday to date
  And user applies for leave
  Then Warning for non working days should be displayed
  When user clicks on menu "Leave"
  And user opens My Leave menu
  And user cancels all applied leaves
  Then all leaves should be cancelled if available 

Scenario: Apply leave with insufficient balance
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects leave type "f"
  And user selects from date
  And user selects to date
  And user applies for leave
  Then leave balance error should be shown

@smoke
Scenario: Supervisor approves the leave when applied by user
	When user clicks on menu "Leave"
	And supervisor approves the leave request
	Then Successful toast message should be Displayed


Scenario: Add leave entitlement for an employee
  When user clicks on menu "Leave"
  And user opens Leave Entitlement menu
  And user navigates to Employee Entitlements
  And user searches entitlement for employee "Adriana"
  And user selects leave type "Earned" in entitlement search
  And user clicks entitlement search
  And user balance before entitlement is noted
  And user opens Leave Entitlement menu
  And user navigates to Add Entitlement
  And user selects entitlement type "individual"
  And user enters employee name for entitlement "Adriana"
  And user selects leave type "Earned" for entitlement
  And user enters entitlement value "1"
  And user clicks on save entitlement
  And user confirms entitlement assignment
  Then entitlement should be added successfully
  When user opens Leave Entitlement menu
  And user navigates to Employee Entitlements
  And user searches entitlement for employee "Adriana"
  And user selects leave type "Earned" in entitlement search
  And user clicks entitlement search
  Then leave balance should be increased compared to previous


































@regression @leave
Feature: Leave Module

Background: 

@noLogin @smoke
Scenario: Leave is applied successfully and approved by the supervisor

  #admin cancel already approved leave from his account for the day
  When admin logs in with default credentials
  And user clicks on menu "Leave"
  #And user clicks on leave list menu
  And user filters leave by status "Taken"
  And user cancel leave if present
  And user logs out
  
  #user login and apply for leave
  When normal user logs in with default credentials
  And user clicks on menu "Leave"
  And user opens My Leave menu
  And user cancels all applied leaves
  And user navigates to apply leave page
  And user selects leave type "Earned Leave"
  And user selects from date
  And user selects to date
  And user applies for leave
  Then leave should be applied with success toast
  And user logs out
  
  #admin approving leave
  When admin logs in with default credentials
  And user clicks on menu "Leave"
  And supervisor approves the leave request
  Then Successful toast message should be Displayed
  And user logs out
  
Scenario: Leave application is rejected for overlapping dates
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects leave type "Earned Leave"
  And user selects from date
  And user selects to date
  And user applies for leave
  And user navigates to apply leave page
  And user selects leave type "Earned Leave"
  And user selects from date
  And user selects to date
  And user applies for leave
  Then system should show failed to apply leave toast
  When user clicks on menu "Leave"
  And user opens My Leave menu
  And user cancels all applied leaves
  Then all leaves should be cancelled if available
  
Scenario: Leave application is prevented without selecting a leave type
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects from date
  And user selects to date
  And user applies for leave
  Then warning for leave type should be displayed

Scenario: Leave application is prevented for non-working day dates
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects leave type "Earned Leave"
  And user selects Saturday from date
  And user selects Sunday to date
  And user applies for leave
  Then Warning for non working days should be displayed
  
Scenario: Leave application is prevented due to insufficient leave balance
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects leave type "floating leave"
  And user selects from date
  And user selects to date
  And user applies for leave
  Then leave balance error should be shown

@sanity
Scenario: Leave entitlement is added successfully for an employee
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


































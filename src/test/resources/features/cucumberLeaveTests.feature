@leave
Feature: Leave Module

Background: 

@noLogin
Scenario: Apply leave with valid inputs
	When admin logs in with default credentials
  When user clicks on menu "Leave"
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
  When user opens leave menu
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
  When user opens leave menu
  And user opens My Leave menu
  And user cancels all applied leaves
  Then all leaves should be cancelled if available
  
Scenario: Apply leave without selecting leave type
  When user opens leave menu
  And user navigates to apply leave page
  And user selects from date
  And user selects to date
  And user applies for leave
  Then warning for leave type should be displayed

@failed
Scenario: Apply leave with Non Working day dates
  When user clicks on menu "Leave"
  And user navigates to apply leave page
  And user selects leave type "e"
  And user selects Saturday from date
  And user selects Sunday to date
  And user applies for leave
  Then Warning for non working days should be displayed
  When user opens leave menu
  And user opens My Leave menu
  And user cancels all applied leaves
  Then all leaves should be cancelled if available 

Scenario: Apply leave with insufficient balance
  When user opens leave menu
  And user navigates to apply leave page
  And user selects leave type "f"
  And user selects from date
  And user selects to date
  And user applies for leave
  Then leave balance error should be shown

Scenario: Add leave entitlement for an employee

  # Checking existing entitlement balance
  When user opens leave menu
  And user opens Leave Entitlement menu
  And user navigates to Employee Entitlements
  And user searches entitlement for employee "Adriana Jones"
  And user selects leave type "Earned" in entitlement search
  And user clicks entitlement search
  Then leave balance should be more than 0.0
  # Adding 1 more entitlement
  When user opens Leave Entitlement menu
  And user navigates to Add Entitlement
  And user selects entitlement type "individual"
  And user enters employee name for entitlement "Adriana Jones"
  And user selects leave type "Earned" for entitlement
  And user enters entitlement value "1"
  And user clicks on save entitlement
  And user confirms entitlement assignment
  Then entitlement should be added successfully
  # Verifing balance increased
  When user opens Leave Entitlement menu
  And user navigates to Employee Entitlements
  And user searches entitlement for employee "Adriana Jones"
  And user selects leave type "Earned" in entitlement search
  And user clicks entitlement search
  Then leave balance should be more than 0.0


































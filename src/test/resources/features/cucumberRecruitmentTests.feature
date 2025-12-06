@regression @recruitment
Feature: Recruitment module

Background:

@sanity
Scenario: Vacancy added with complete details is visible in the candidate form
	When user opens recruitment menu
	And user opens vacancies menu
	And user clicks add vacancy
	And user enters vacancy name "Marketing Asst Manager"
	And user selects job title "Assistant Manager" for vacancy
	And user selects hiring manager "Piyush"
	And user enters number of vacancies "1"
	And user saves vacancy
	And user opens vacancies menu
	And user clicks vacancy search
	Then vacancy "Marketing Asst Manager" should be visible in vacancies list
	And user deletes vacancy "Marketing Asst Manager"

@smoke
Scenario: Candidate is successfully added to an existing vacancy
	When user opens recruitment menu
	And user opens candidates menu
	And user clicks add candidate
	And user enters candidate full name "Sam" "Ron" "Miles"
	And user selects candidate vacancy
	And user enters candidate email "samRMiles@gmail.com"
	And user enters candidate contact number "9558754562"
	And user uploads candidate resume
	And user agrees to data consent
	And user saves candidate
	Then candidate should be added successfully


Scenario: Candidate is added to an existing vacancy without providing an email
	When user opens recruitment menu
	And user opens candidates menu
	And user clicks add candidate
	And user enters candidate full name "Sam" "Ron" "Miles"
	And user selects candidate vacancy
	And user enters candidate contact number "9558754562"
	And user uploads candidate resume
	And user agrees to data consent
	And user saves candidate
	Then email required error should be displayed
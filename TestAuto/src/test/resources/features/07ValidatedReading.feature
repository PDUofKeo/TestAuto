@MAT7 
Feature: Validated Reading form

@QCLogin 
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page 
	When User enters UserName: "REA1" and Password: "keosys" 
	Then User is connected 
	
@SelectReader
Scenario: Select the Reader tab 
	Given Reader tab is displayed
	When User clicks on the reader tab 
	Then Reader screen is displayed


 
@SelectPatient 
Scenario: Select a patient 
	Given Patient is displayed in the tree 
		| patient |
		| PATIENT03 |
	When User selects a patient: "PATIENT03"
	Then Visit is displayed in the tree 
		| visit |
		| VISIT01 |
		
@SelectVisit 
Scenario: Select a visit 
	Given Visit is displayed in the tree 
		| visit |
		| VISIT01 |
	When User selects a visit: "VISIT01"
	Then Basic Reading form is displayed
	
	
@ValidatedReaderForm
Scenario: validate the reader form
	Given Basic Reading form is displayed
	When User validates the Reading form
	Then "PATIENT03" and "VISIT01" are not in the tree

@CheckReadingForm
Scenario: Check reading form is validated
	Given  "PATIENT03" and "VISIT01" are not in the tree
	When User sets filter to all
	And User selects a patient: "PATIENT03" 
	And User selects a visit: "VISIT01"
	Then Reading form is validated for patient: "PATIENT03" and visit: "VISIT01"

	
	
@UserLogout 
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application 
	Then User is disconnected 
	
@SupervisorLogin
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page
	When User enters UserName and Password 
		| username  | password |
		| SUP1	| keosys |
	Then User is connected
	
	
@SelectSupervisor
Scenario: Select the Supervisor
	Given Supervisor tab is displayed
	When User clicks on the Supervisor tab
	Then Supervisor screen is displayed	
	
	
	
@SupervisionSearch
Scenario: Select Patient and visit in the Supervisor table
	Given Supervisor screen is displayed
	When Supervisors enters search parameter 
		| patient 	| Visit 	|	From 	|	To	| Status		| 
		| PATIENT03 | VISIT01	|			|		| Wait adjudication |
	And User clicks on the Filter button
	And  User Clicks on the first details button
	Then Resume page is displayed 
		| patient | Visit |
		| PATIENT03 | VISIT01|
	
		
@SupervisionResult
Scenario: Display Results page
	Given Resume page is displayed 
		| patient | Visit |
		| PATIENT03 | VISIT01|
	When Click on Results button
	Then Results page is displayed
		| patient | Visit |
		| PATIENT03 | VISIT01|		
		
		
@SupervisionReader
Scenario: Display Reading result page
	Given Results page is displayed 
		| patient | Visit |
		| PATIENT03 | VISIT01|
	When Click on Reading tab 
	Then Reading form is displayed for "PATIENT03" and "VISIT01"			
	
@DeleteReading
Scenario: check Reading result is deleted
	Given Reading form is displayed for "PATIENT03" and "VISIT01"		
	When Click on delete Reading button
	And Enter the reason comment "Deletion of the Reading form" 
	And User validates the Form deletion 
	Then Second Reading form results is deleted
	
@UserLogout	
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application
	Then User is disconnected  
		
		
	
	

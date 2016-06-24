@MAT8 
Feature: Validated Adjudication form

@QCLogin 
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page 
	When User enters UserName: "ADJ1" and Password: "keosys" 
	Then User is connected 
	
@SelectReader
Scenario: Select the Adjudication tab 
	Given Adjudicator tab is displayed
	When User clicks on the Adjudicator tab 
	Then Adjudicator screen is displayed



@SelectPatient 
Scenario: Select a patient 
	Given Patient is displayed in the tree 
		| patient |
		| PATIENT04 |
	When User selects a patient: "PATIENT04"
	Then Visit is displayed in the tree 
		| visit |
		| VISIT01 |
		
@SelectVisit 
Scenario: Select a visit 
	Given Visit is displayed in the tree 
		| visit |
		| VISIT01 |
	When User selects a visit: "VISIT01"
	Then Basic Adjudication form is displayed
	
	
@ValidatedReaderForm
Scenario: validate the adjudication form
	Given Basic Adjudication form is displayed 
	When User validates the Adjudication form
	Then "PATIENT04" and "VISIT01" are not in the tree

@CheckReadingForm
Scenario: Check adjudication form is validated
	Given  "PATIENT04" and "VISIT01" are not in the tree
	When User sets filter to all
	And User selects a patient: "PATIENT04" 
	And User selects a visit: "VISIT01"
	Then Adjudication form is validated

	
	
@UserLogout 
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application 
	Then User is disconnected 
	
@SupervisorLogin
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page
	When User enters UserName: "ADJ1" and Password: "keosys" 
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
		| PATIENT04 | VISIT01	|			|		| Adjudicated 	|
	And User clicks on the Filter button
	And  User Clicks on the first details button
	Then Resume page is displayed 
		| patient | Visit |
		| PATIENT04 | VISIT01|
	
		
@SupervisionResult
Scenario: Display Results page
	Given Resume page is displayed 
		| patient | Visit |
		| PATIENT04 | VISIT01|
	When Click on Results button
	Then Results page is displayed
		| patient | Visit |
		| PATIENT04 | VISIT01|		
		
		
@SupervisionReader
Scenario: Display Adjudication result page
	Given Results page is displayed 
		| patient | Visit |
		| PATIENT04 | VISIT01|
	When Click on Adjudication tab 
	Then Adjudication form is displayed for "PATIENT04" and "VISIT01"			
	
@DeleteReading
Scenario: check Adjudication result is deleted
	Given Adjudication form is displayed for "PATIENT04" and "VISIT01"				
	When Click on delete Adjudication button
	And Enter the reason comment "Deletion of the Adjudication form" 
	And User validates the Form deletion  
	Then Adjudication form results is deleted
	
@UserLogout	
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application
	Then User is disconnected  
		
		
	
	

@MAT6 
Feature: Validated Investigation form

@QCLogin 
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page 
	When User enters UserName: "INV1" and Password: "keosys"
	Then User is connected 
	

@SelectPatient 
Scenario: Select a patient 
	Given Patient is displayed in the tree 
		| patient |
		| PATIENT02 |
	When User selects a patient: "PATIENT02"
	Then Visit is displayed in the tree 
		| visit |
		| VISIT01 |
		
@SelectVisit 
Scenario: Select a visit 
	Given Visit is displayed in the tree 
		| visit |
		| VISIT01 |
	When User selects a visit: "VISIT01"
	Then Basic Upload form is displayed
	
	
@ValidatedINVForm
Scenario: validate the INV form
	Given Basic Upload form is displayed
	When User validates the INV form
	Then INV form is validated  
	And "PATIENT02" and "VISIT01" are still displayed in the tree
	
	
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
		| PATIENT02 | VISIT01	|			|		| Wait quality control |
	And User clicks on the Filter button
	And  User Clicks on the first details button
	Then Resume page is displayed 
		| patient | Visit |
		| PATIENT02 | VISIT01|
	
		
@SupervisionResult
Scenario: Display Results page
	Given Resume page is displayed 
		| patient | Visit |
		| PATIENT02 | VISIT01|
	When Click on Results button
	Then Results page is displayed
		| patient | Visit |
		| PATIENT02 | VISIT01|		
		
		
@SupervisionINV
Scenario: Display INV result page
	Given Results page is displayed 
		| patient | Visit |
		| PATIENT02 | VISIT01|
	When Click on Investigation tab
	Then INV form is displayed for "PATIENT02" and "VISIT01"			
	
@DeleteQC
Scenario: check INV result is deleted
	Given INV form is displayed for "PATIENT02" and "VISIT01"
	When Click on delete INV button
	And Enter the reason comment "Deletion of the Upload form" 
	And User validates the Form deletion
	Then INV results page is empty 
	
@UserLogout	
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application
	Then User is disconnected  
		
		
	
	

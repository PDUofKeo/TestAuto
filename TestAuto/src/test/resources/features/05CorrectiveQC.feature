@MAT5 
Feature: Check QC is corrective

@QCLogin 
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page 
	When User enters UserName: "QC1" and Password: "keosys"  
	Then User is connected 
	
@SelectController 
Scenario: Select the controller 
	Given Controller tab is displayed
	When User clicks on the controller tab
	Then Controller screen is displayed
	
	
@SelectPatient 
Scenario: Select a patient 
	Given Patient is displayed in the tree 
		| patient |
		| PATIENT01 |
	When User selects a patient: "PATIENT01"
	Then Visit is displayed in the tree 
		| visit |
		| VISIT01 |
		
@SelectVisit 
Scenario: Select a visit 
	Given Visit is displayed in the tree 
		| visit |
		| VISIT01 |
	When User selects a visit: "VISIT01"
	Then Basic QC form is displayed 
	
	
@RefusedQC 
Scenario: Refused a QC form 
	Given Basic QC form is displayed 
	When User set comments in the the QC form "Corrective action for the QC"
	And User asks a Corrective action for the QC 
	Then QC form is validated 
	
@QCIsValidated 
Scenario: QC was asked corrective action
	Given User sets filter to all 
	And  User selects a patient: "PATIENT01"
	And  User selects a visit: "VISIT01"
	Then QC was asked a corrective action for patient: "PATIENT01" and visit: "VISIT01"
		
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
	
	
@SelectController 
Scenario: Select the Supervisor
	Given Supervisor tab is displayed
	When User clicks on the Supervisor tab
	Then Supervisor screen is displayed	
	
	
	
@SupervisionSearch
Scenario: Select Patient and visit in the Supervisor table
	Given Supervisor screen is displayed
	When Supervisors enters search parameter 
		| patient | Visit | From 		| To	| Status	| 
		| PATIENT01 | VISIT01|			|		| Wait corrective action	|
	And User clicks on the Filter button
	And  User Clicks on the first details button
	Then Resume page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT01|
	
		
@SupervisionResult
Scenario: Display Results page
	Given Resume page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT01|
	When Click on Results button
	Then Results page is displayed
		| patient | Visit |
		| PATIENT01 | VISIT01|		
		
		
@SupervisionQC
Scenario: Display QC result page
	Given Results page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT01|
	When Click on Quality Control tab
	Then Asked corrective action QC page is displayed
		| patient | Visit |
		| PATIENT01 | VISIT01|				
	
@DeleteQC
Scenario: check QC result is deleted
	Given Asked corrective action QC page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT01|			
	When Click on delete QC button
	And Enter the reason comment "Deletion of the asking corrective action" 
	And User validates the Form deletion
	Then QC results page is empty 
	
@UserLogout	
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application
	Then User is disconnected  
		
#Validate the Investigation to set the previous state	
@QCLogin 
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page 
	When User enters UserName and Password 
		| username  | password |
		| INV1	| keosys |
	Then User is connected 
	

@SelectPatient 
Scenario: Select a patient 
	Given Patient is displayed in the tree 
		| patient |
		| PATIENT01 |
	When User selects a patient: "PATIENT01"
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
	And "PATIENT01" and "VISIT01" are still displayed in the tree
	
	
@UserLogout 
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application 
	Then User is disconnected 
	
	
	

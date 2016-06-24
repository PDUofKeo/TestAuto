@MAT1 
Feature: Check QC is accepted 

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
		| VISIT03 |
		
@SelectVisit 
Scenario: Select a visit 
	Given Visit is displayed in the tree 
		| visit |
		| VISIT03 |
	When User selects a visit: "VISIT03"
	Then Basic QC form is displayed 
	
	
@ValidateQC 
Scenario: Validate a QC form 
	Given Basic QC form is displayed 
	When User set comments in the the QC form "Accepted QC"
	And User accepts the QC form 
	Then QC form is validated 
	
@QCIsValidated 
Scenario: QC was accepted 
	Given User sets filter to all 
	And  User selects a patient: "PATIENT01"
	And  User selects a visit: "VISIT03"
	Then QC was accepted for patient: "PATIENT01" and visit: "VISIT03"
	
@UserLogout 
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application 
	Then User is disconnected 
	
@SupervisorLogin
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page
	When User enters UserName: "SUP1" and Password: "keosys" 
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
		| PATIENT01 | VISIT03|			|		| Wait reading	|
	And User clicks on the Filter button
	And  User Clicks on the first details button
	Then Resume page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT03|
	
		
@SupervisionResult
Scenario: Display Results page
	Given Resume page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT03|
	When Click on Results button
	Then Results page is displayed
		| patient | Visit |
		| PATIENT01 | VISIT03|		
		
		
@SupervisionQC
Scenario: Display QC result page
	Given Results page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT03|
	When Click on Quality Control tab
	Then Accepted Quality control page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT03|				
	
@DeleteQC
Scenario: check QC result is deleted
	Given Accepted Quality control page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT03|			
	When Click on delete QC button
	And Enter the reason comment "Deletion of the accepted QC"
	And User validates the Form deletion
	Then QC results page is empty 
	
@UserLogout	
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application
	Then User is disconnected  
		
		
	
	

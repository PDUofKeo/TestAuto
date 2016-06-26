@MAT3 
Feature: Check QC is refused

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
		| VISIT02 |
		
@SelectVisit 
Scenario: Select a visit 
	Given Visit is displayed in the tree 
		| visit |
		| VISIT02 |
	When User selects a visit: "VISIT02"
	Then Basic QC form is displayed 
	
	
@RefusedQC 
Scenario: Refused a QC form 
	Given Basic QC form is displayed 
	When User set comments in the the QC form "Refused the QC"
	And User refused the QC form 
	Then QC form is validated 
	
@QCIsValidated 
Scenario: QC was refused
	Given User sets filter to all 
	And  User selects a patient: "PATIENT01"
	And  User selects a visit: "VISIT02"
	Then QC was refused for patient: "PATIENT01" and visit: "VISIT02"
		
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
		| PATIENT01 | VISIT02|			|		| Quality control refused	|
	And User clicks on the Filter button
	And  User Clicks on the first details button
	Then Resume page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT02|
	
		
@SupervisionResult
Scenario: Display Results page
	Given Resume page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT02|
	When Click on Results button
	Then Results page is displayed
		| patient | Visit |
		| PATIENT01 | VISIT02|		
		
		
@SupervisionQC
Scenario: Display QC result page
	Given Results page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT02|
	When Click on Quality Control tab
	Then Refused Quality control page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT02|				
	
@DeleteQC
Scenario: check QC result is deleted
	Given Refused Quality control page is displayed 
		| patient | Visit |
		| PATIENT01 | VISIT02|			
	When Click on delete QC button
	And Enter the reason comment "deletion of the refused QC"
	And User validates the Form deletion
	Then QC results page is empty 
	
@UserLogout	
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application
	Then User is disconnected  
		
		
	
	

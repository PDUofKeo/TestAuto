@IM-317
Feature: logout
# TEST JIRA : IM-317


@QCLogin 
Scenario: Successful Login with Valid Credentials 
	Given User is on Home Page 
	When User enters UserName: "sysadmin" and Password: "keosys"  
	Then User is connected 

@UserLogout	
Scenario: Successful Logout 
	Given User is connected 
	When User LogOut from the Application
	Then User is disconnected  
	
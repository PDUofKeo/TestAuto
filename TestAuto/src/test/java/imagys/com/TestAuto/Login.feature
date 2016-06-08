Feature: login
Scenario: Successful Login with Valid Credentials as sysadmin 
	Given User is on Home Page
	When User enters UserName and Password 
	Then IC is displayed 

Feature: gotowork

  Scenario: Successful Login with Valid Credentials as sysadmin
    Given 1_User is on Home Page
    When 1_User enters UserName and Password
    Then 1_IC is displayed 

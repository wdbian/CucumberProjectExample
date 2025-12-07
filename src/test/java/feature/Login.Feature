@regression
Feature: Login Feature

  Background: 	
    Given user opens landing page

  Scenario Outline: Successful login with correct credential
  	When user navigates to login page
    When user enters "<email>" in Email input
    And user enters "<password>" in Password input
    And user clicks SignIn button
    Then validate user product size is correct
    And validate user name is displayed correctly

    Examples: 
      | 				email         | 		password 		|
      | wdbian@hotmail.com 		| 		Test     		|

@regression
Feature: Order Card
	
	Background: 	
    Given user opens landing page

  Scenario Outline: Successful login with correct credential
  	When user navigates to login page
    When user enters "<email>" in Email input
    And user enters "<password>" in Password input
    And user clicks SignIn button
    And user clicks order physical card button from dashboard page
    Then validate add to cart button is displayed
    
     Examples: 
      | 				email         | 		password 			|
      | wdbian@hotmail.com 		| 		Bwd@750829  	|
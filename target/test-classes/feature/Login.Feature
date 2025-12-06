Feature: Login Feature

	Background:
		Given user launch chrom browser
		When user opens url "http://admin-demo.nopcommerce.com/login"
		
		
  Scenario: Successful login with valid credential
    When user enters "admin@yourstore.com" in Email input 
    And user enters "admin" in Password input
    And click Log In
    Then page title is "Dashboard / nopCommerce administration"
    When user click Log out link
    Then page title should be "Your store. Login"
    And close browser
    
  Scenario Outline: Successful login with different credential
  	When user enters <email> in Email input
  	And user enters <password> in Password input
  	And click Log In
  	
  	Examples:
  	|email 						|password 		|
  	|s@hotmail.com  	|Test    			|
  	

 

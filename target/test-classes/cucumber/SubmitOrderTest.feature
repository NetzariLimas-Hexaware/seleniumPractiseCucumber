#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    When Add a product <productName> to Cart
    And Go to checkout and add code <codeString> to discount
    Then I choose the country <countryName> agree terms and place the order
    Then "Thank you, your order has been placed successfully" message is displayed on confirmation page.

    Examples: 
      | productName  | countryName | codeString  				|
      | Cucumber 		 | Mexico 		 | rahulshettyacademy |
      | Brocolli     | India  	   | rahulshettyacademy |

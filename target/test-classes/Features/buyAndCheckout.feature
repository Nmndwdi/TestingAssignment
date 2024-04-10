Feature: feature to test buy and checkout functionality
	
	@acessing_cart
  Scenario: Acessing cart
    Given user in on demoblaze website
    When user press the Cart button present on navigation header
    Then user should reach the Cart page

  @checkout_and_buy_product_without_adding_credentials
  Scenario: user try to buy product without entering credentials
    Given user is on the cart page
    When user press the Place Order button then a page should appear to enter details
    And user does not enter the detail
    And user clicks on Purchase button without adding credentials
    Then a popup should appear asking to enter credentials

  @checkout_and_buy_product_with_entering_credentials
  Scenario: user try to buy product after entering credentials
    Given user is on the cart page
    When user press the Place Order button then a page should appear to enter details
    And user enters the detail
    And user clicks on Purchase button with adding credentials
    Then user should see the transaction page
Feature: feature to test add to cart functionality on the demoblaze.com

  @view_product_landing_page
  Scenario: View product landing page
    Given browser is open
    When user enter the correct url of demoblaze
    Then user will reach to the product landing page

  @view_product_details_page
  Scenario: View product details page
    Given user have viewed the product landing page
    When user click on a product
    Then user should see its product detail page

  @add_to_cart
  Scenario: Add the product to cart
    Given user should be one the product details page
    When user press Add to Cart button
    Then product should be added and a popup should be displayed

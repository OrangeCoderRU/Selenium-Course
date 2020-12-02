Feature: Work with basket

  Scenario: Add and remove items
    When start application
    Then we add '3' items in the basket
    And go to basket
    Then we remove all items from the basket
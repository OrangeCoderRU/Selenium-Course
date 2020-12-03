Feature: Work with basket

  Scenario: Add and remove items
    Then we add '3' random items to the basket
    And go to basket page
    Then we remove all items from the basket
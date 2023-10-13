Feature: Favorites Feature
  As an User
  I want to interact with "my favorites"
  So I can get easier my favorite hotels

  Scenario: I want to check my favorite hotels
    Given The application is already running
    When I tap the favorites tab
      And I tap on the start searching button
      And I search "Paris"
      And I tap on the favorites logo at the items:
        | Hotel Duo         |
        | Hotel De la Paix  |
      And I tap the green notification regarding to add the new favorite
    Then I can see the records that I marked as favorite
      | Hotel Duo         |
      | Hotel De la Paix  |
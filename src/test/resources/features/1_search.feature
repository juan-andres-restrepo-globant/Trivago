Feature: Search Feature
  As an user
  I want to search some hotels
  So I can check the hotels availability

  Scenario: I want to search hotels in a range of dates
    Given The privacy data has already been accepted
    When I search "Cartagena" on the text box
      And I select the range of dates between "2024-10-01", "2024-10-05"
      And I tap the search button
    Then I can see the text box that includes the word "Cartagena"
      And I can see the range of dates concatenated like "2024-10-01" - "2024-10-05"
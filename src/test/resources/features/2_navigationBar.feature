Feature: Navigation bar feature
  As an user
  I want to ensure that the navigation bar is displaying correctly
  So I can switch between tabs

  Scenario: I want to check the navigation bar
    Given The application is already running
    When I check the navigation bar located at the bottom of the screen
    Then I validate that the navigation bar contains:
      | Alojamientos  |
      | Favoritos     |
      | Configuración |
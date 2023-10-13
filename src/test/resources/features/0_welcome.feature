Feature: Welcome feature
  As a new user
  I want to set up the application
  So that I can interact with my preferences

  Scenario: I want to select language and agree privacy data terms as a new user
    Given The application is in a fresh install
    When I want to select Colombia
      And I want to tap the confirm button
        | header  | ¡Te damos la bienvenida a trivago!                                                                                                                                                                                                                           |
        | body    | Primero, hablemos de las cookies. Usamos cookies y otras tecnologías similares para el funcionamiento de la página web y con fines de análisis y de marketing. Para que podamos usar estas cookies y mejorar tu experiencia con la app, presiona "Permitir". |
        | choose  | También puedes obtener más información sobre las cookies que usamos y ajustar tu configuración aquí.                                                                                                                                                         |
      And I want to tap the agree all button
    Then I want to validate that the privacy data screen is not visible anymore


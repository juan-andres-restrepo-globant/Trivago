Feature: Settings Feature
  As an user
  I want to interact with the settings
  So I can modify the settings

  Scenario: I want to ensure that data privacy settings are displayed properly
    Given The application is already running
    When I tap the settings tab
      And I tap the data privacy settings option
    Then I can see the tile "Configurar privacidad de datos"
      And I can validate the body text is "Nosotros y nuestros socios de confianza usamos las cookies en trivago para mejorar tu experiencia de muchas maneras. Por ejemplo, recopilamos, almacenamos, compartimos y rastreamos datos mediante las cookies para recordar tu actividad reciente, como tu última búsqueda, o para personalizar los anuncios que ves y facilitarte el uso de nuestro sitio. Nunca usamos cookies para cambiar los precios que mostramos en trivago. Más información"
      And I can validate the mandatory cookies text is "Estas son cookies básicas que se necesitan para que nuestro sitio funcione de forma correcta, y puedas usar las funciones principales y navegar en él sin problemas."
      And I can validate the optional cookies are:
        | functional  | Cookies funcionales     |
        | performance | Cookies de rendimiento  |
        | marketing   | Cookies comerciales     |
      And I can validate that each optional cookie has a switch
      And I validate that each optional cookie has the right text below
        | functional  | Estas cookies permiten mejorar la funcionalidad y la personalización de nuestro sitio y, si se deshabilitan, es posible que nuestros servicios no funcionen correctamente. Nosotros u otros proveedores externos podemos instalarlas. |
        | performance | Estas cookies nos permiten medir y mejorar el funcionamiento de nuestro sitio. Si las deshabilitas, no podremos optimizar nuestro sitio según tus preferencias.                                                                       |
        | marketing   | Usamos estas cookies para mostrarte anuncios relevantes, analizar los resultados de nuestras campañas de marketing y enviarte notificaciones push.                                                                                    |

    Scenario: I want to ensure that data privacy settings work properly
      Given The application is already running
      When I tap the settings tab
        And I tap the data privacy settings option
        And I disable each optional cookie
        And I tap the save privacy button
        And I tap the data privacy settings option
      Then I validate that each optional cookie is unchecked
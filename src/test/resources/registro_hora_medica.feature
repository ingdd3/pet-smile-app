Feature: Registro de hora medica veterinaria

  Background:
    Given que existe una mascota con nombre "mascota_prueba" en la base de datos

  Scenario: El usuario registra una hora medica para la mascota "mascota_prueba" con fecha "2023-07-31" y hora "09:00"
    When el usuario registra una hora medica para la mascota "mascota_prueba" con fecha "2023-07-31" y hora "09:00"
    Then el sistema muestra un mensaje de éxito en el registro de la hora medica

  Scenario: El usuario intenta registrar una hora medica para una mascota inexistente
    When el usuario registra una hora medica para una mascota inexistente con fecha "2023-07-31" y hora "09:00"
    Then el sistema muestra un mensaje de error indicando que la mascota no existe

  Scenario: El usuario intenta registrar una hora medica con fecha y hora inválidas
    When el usuario intenta registrar una hora medica para la mascota "mascota_prueba" con fecha inválida y hora inválida
    Then el sistema muestra un mensaje de error indicando que la fecha y hora son inválidas

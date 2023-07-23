Feature: Ingreso de nombre de usuario con datos almacenados en BD

  Scenario: Usuario existente
    Given que existe un usuario con nombre "amelia@pet.com" en la base de datos
    When el usuario ingresa el nombre "amelia@pet.com"
    Then el sistema muestra una interfaz con la lista de mascotas

  Scenario: Usuario no existente
    Given que no existe un usuario con nombre "usuario456" en la base de datos
    When el usuario ingresa el nombre "usuario456"
    Then el sistema muestra un mensaje de usuario no encontrado

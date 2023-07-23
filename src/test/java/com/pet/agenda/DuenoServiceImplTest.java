package com.pet.agenda;

import com.pet.model.Dueno;
import com.pet.repository.IDuenoRepository;
import com.pet.service.DuenoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Map;

class DuenoServiceImplTest {

    private DuenoServiceImpl duenoService;
    @Mock
    private IDuenoRepository duenoRepository;
    @BeforeEach
    public void setUp() {
        duenoService = new DuenoServiceImpl(duenoRepository);
    }
    @Test
    void testVerifyField_AllFieldsValid_NoErrors() {
        Dueno dueno = new Dueno();
        dueno.setRut(123L);
        dueno.setNombre("Juan");
        dueno.setApellido("Pérez");
        dueno.setDireccion("Calle 123");
        dueno.setCorreo("juan@example.com");
        dueno.setTelefono("123456789");
        dueno.setNombreMascota("Firulais");
        Map<String, String> result = duenoService.verifyField(dueno);
        Assertions.assertTrue(result.isEmpty());
    }
    @Test
    void testVerifyField_InvalidRut_ErrorAdded() {
        // Crear un dueño con RUT inválido (null)
        Dueno dueno = new Dueno();
        dueno.setRut(null);
        dueno.setNombre("Juan");
        dueno.setApellido("Pérez");
        dueno.setDireccion("Calle 123");
        dueno.setCorreo("juan@example.com");
        dueno.setTelefono("123456789");
        dueno.setNombreMascota("Firulais");
        Map<String, String> result = duenoService.verifyField(dueno);
        Assertions.assertTrue(result.containsKey("RUT vacío"));
    }

}



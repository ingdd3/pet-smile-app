package com.pet.service;

import com.pet.model.Dueno;
import com.pet.repository.IDuenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DuenoServiceImpl {

    @Autowired
    private final IDuenoRepository duenoRepository;

    public DuenoServiceImpl(IDuenoRepository duenoRepository) {
        this.duenoRepository = duenoRepository;
    }

    public Dueno guardarDueno(Dueno dueno) {
        return duenoRepository.save(dueno);
    }

    public List<Dueno> obtenerTodosLosDuenos() {
        return duenoRepository.findAll();
    }

    public Dueno obtenerDuenoPorRut(Long rut) {
        return duenoRepository.findById(rut).orElse(null);
    }

    public void eliminarDuenoPorRut(Long rut) {
        duenoRepository.deleteById(rut);
    }
    public Map<String, String> verifyField(Dueno dueno) {
        Map<String, String> result = new HashMap<>();

        if (dueno.getRut() == null || dueno.getRut()<0) {
            result.put("RUT vacío", "Debe especificar el RUT del dueño.");
        }

        if (dueno.getNombre() == null || dueno.getNombre().isEmpty()) {
            result.put("Nombre vacío", "Debe especificar el nombre del dueño.");
        }

        if (dueno.getApellido() == null || dueno.getApellido().isEmpty()) {
            result.put("Apellido vacío", "Debe especificar el apellido del dueño.");
        }

        if (dueno.getDireccion() == null || dueno.getDireccion().isEmpty()) {
            result.put("Dirección vacía", "Debe especificar la dirección del dueño.");
        }

        if (dueno.getCorreo() == null || dueno.getCorreo().isEmpty()) {
            result.put("Correo vacío", "Debe especificar el correo del dueño.");
        }

        if (dueno.getTelefono() == null || dueno.getTelefono().isEmpty()) {
            result.put("Teléfono vacío", "Debe especificar el teléfono del dueño.");
        }

        if (dueno.getNombreMascota() == null || dueno.getNombreMascota().isEmpty()) {
            result.put("Nombre de mascota vacío", "Debe especificar el nombre de la mascota del dueño.");
        }

        return result;
    }
}

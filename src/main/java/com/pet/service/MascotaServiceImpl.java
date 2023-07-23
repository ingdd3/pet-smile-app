package com.pet.service;

import com.pet.model.Mascota;
import com.pet.repository.IMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MascotaServiceImpl {

    @Autowired
    private final IMascotaRepository mascotaRepository;

    public MascotaServiceImpl(IMascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }

    public Mascota obtenerMascotaPorId(Long idMascota) {
        return mascotaRepository.findById(idMascota).orElse(null);
    }

    public void eliminarMascotaPorId(Long idMascota) {
        mascotaRepository.deleteById(idMascota);
    }

    public Map<String, String> verifyField(Mascota mascota) {
        Map<String, String> result = new HashMap<>();

        if (mascota.getDueno() == null) {
            result.put("Dueño no especificado", "La mascota debe tener un dueño asociado.");
        }

        if (mascota.getTipoMascota().isEmpty()) {
            result.put("Tipo de mascota vacío", "Debe especificar el tipo de mascota.");
        }

        if (mascota.getEdad() <= 0) {
            result.put("Edad inválida", "La edad de la mascota debe ser mayor que cero.");
        }

        if (mascota.getNombreMascota().isEmpty()) {
            result.put("Nombre de mascota vacío", "Debe especificar el nombre de la mascota.");
        }

        return result;
    }

}

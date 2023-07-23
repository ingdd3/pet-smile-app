package com.pet.service;

import com.pet.model.Agenda;
import com.pet.repository.IAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgendaServiceImpl {

    @Autowired
    private final IAgendaRepository agendaRepository;


    public Map<String, String> verifyField(Agenda agenda) {
        Map<String, String> result = new HashMap<>();

        if (agenda.getMascota() == null) {
            result.put("Mascota no especificada", "La agenda debe estar asociada a una mascota.");
        }

        if (agenda.getHora() == null || agenda.getHora().isEmpty()) {
            result.put("Hora vacía", "Debe especificar la hora de la agenda.");
        }

        if (agenda.getFecha() == null || agenda.getFecha().isEmpty()) {
            result.put("Fecha vacía", "Debe especificar la fecha de la agenda.");
        }

        return result;
    }

    public AgendaServiceImpl(IAgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda guardarAgenda(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public List<Agenda> obtenerTodasLasAgendas() {
        return agendaRepository.findAll();
    }

    public Agenda obtenerAgendaPorId(Long idAgenda) {
        return agendaRepository.findById(idAgenda).orElse(null);
    }

    public void eliminarAgendaPorId(Long idAgenda) {
        agendaRepository.deleteById(idAgenda);
    }

}

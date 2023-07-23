package com.pet.controller;

import com.pet.model.Agenda;
import com.pet.model.Dueno;
import com.pet.model.Mascota;
import com.pet.service.AgendaServiceImpl;
import com.pet.service.DuenoServiceImpl;
import com.pet.service.MascotaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class AgendaController {

    private final AgendaServiceImpl agendaService;
    private final MascotaServiceImpl mascotaService;

    private final DuenoServiceImpl duenoService;

    public AgendaController(AgendaServiceImpl agendaService, MascotaServiceImpl mascotaService, DuenoServiceImpl duenoService) {
        this.agendaService = agendaService;
        this.mascotaService = mascotaService;
        this.duenoService = duenoService;
    }

    @GetMapping("/agendas")
    public ModelAndView createAgenda() {
        ModelAndView model = new ModelAndView();
        List<Mascota> mascotaList = this.mascotaService.obtenerTodasLasMascotas();
        if (mascotaList.isEmpty()) {
            model.addObject("noMascotasMsg", "No hay mascotas registradas. Por favor agréguelas primero.");
        }
        List<Dueno> duenoList = this.duenoService.obtenerTodosLosDuenos();
        if (duenoList.isEmpty()) {
            model.addObject("noDuenosMsg", "No hay dueños registrados. Por favor agréguelos primero.");
        }
        model.addObject("mascotas", mascotaList);
        model.addObject("duenos", duenoList);

        model.setViewName("agenda/create");
        return model;
    }

    @PostMapping("/agendas")
    public ModelAndView addAgenda(@Valid Agenda agenda, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Map<String, String> validations = this.agendaService.verifyField(agenda);
        if (!validations.isEmpty()) {
            List<Mascota> mascotaList = this.mascotaService.obtenerTodasLasMascotas();
            if (mascotaList.isEmpty()) {
                model.addObject("noMascotasMsg", "No hay mascotas registradas. Por favor agréguelas primero.");
            }
            model.addObject("mascotas", mascotaList);
            model.addObject("errors", validations);
            model.setViewName("agenda/create");
        } else {
            model.addObject("agenda", this.agendaService.guardarAgenda(agenda));
            model.addObject("successMsg", "Agenda creada exitosamente");
            RedirectView redirectView = new RedirectView("/agendas/listar", true);
            redirectView.setExposeModelAttributes(false);
            model.setView(redirectView);
        }
        return model;
    }
    @GetMapping("/agendas/listar")
    public ModelAndView listarAgendas() {
        ModelAndView model = new ModelAndView();
        List<Agenda> agendaList = this.agendaService.obtenerTodasLasAgendas();
        if (agendaList.isEmpty()) {
            model.addObject("noAgendasMsg", "No hay agendas registradas.");
        }
        model.addObject("agendas", agendaList);
        model.setViewName("agenda/listar.html");
        return model;
    }

}


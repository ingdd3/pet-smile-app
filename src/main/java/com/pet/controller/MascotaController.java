package com.pet.controller;

import com.pet.model.Dueno;
import com.pet.model.Mascota;
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
public class MascotaController {

    private final MascotaServiceImpl mascotaService;
    private final DuenoServiceImpl duenoService;

    public MascotaController(MascotaServiceImpl mascotaService, DuenoServiceImpl duenoService) {
        this.mascotaService = mascotaService;
        this.duenoService = duenoService;
    }

    @GetMapping("/mascotas")
    public ModelAndView createMascota() {
        ModelAndView model = new ModelAndView();
        List<Dueno> duenoList = this.duenoService.obtenerTodosLosDuenos();
        if (duenoList.isEmpty()) {
            model.addObject("noDuenosMsg", "No hay dueños registrados. Por favor agréguelos primero.");
        }
        model.addObject("duenos", duenoList);
        model.setViewName("mascota/create.html");
        return model;
    }

    @PostMapping("/mascotas")
    public ModelAndView addMascota(@Valid Mascota mascota, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Map<String, String> validations = this.mascotaService.verifyField(mascota);
        if (!validations.isEmpty()) {
            List<Dueno> duenoList = this.duenoService.obtenerTodosLosDuenos();
            if (duenoList.isEmpty()) {
                model.addObject("noDuenosMsg", "No hay dueños registrados. Por favor agréguelos primero.");
            }
            model.addObject("duenos", duenoList);
            model.addObject("errors", validations);
            model.setViewName("mascota/create.html");
        } else {
            model.addObject("mascota", this.mascotaService.guardarMascota(mascota));
            model.addObject("successMsg", "Mascota agregada exitosamente");
            RedirectView redirectView = new RedirectView("/mascotas/listar", true);
            redirectView.setExposeModelAttributes(false);
            model.setView(redirectView);        }
        return model;
    }
    @GetMapping("/mascotas/listar")
    public ModelAndView listarMascotas() {
        ModelAndView model = new ModelAndView();
        List<Mascota> mascotaList = this.mascotaService.obtenerTodasLasMascotas();
        if (mascotaList.isEmpty()) {
            model.addObject("noMascotasMsg", "No hay mascotas registradas.");
        }
        model.addObject("mascotas", mascotaList);
        model.setViewName("mascota/listar.html");
        return model;
    }
}

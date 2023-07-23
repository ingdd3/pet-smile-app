package com.pet.controller;

import com.pet.model.Dueno;
import com.pet.service.DuenoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class DuenoController {

    private final DuenoServiceImpl duenoService;

    public DuenoController(DuenoServiceImpl duenoService) {
        this.duenoService = duenoService;
    }

    @GetMapping("/duenos")
    public ModelAndView createDueno() {
        ModelAndView model = new ModelAndView();
        model.setViewName("dueno/create.html");
        return model;
    }

    @PostMapping("/duenos")
    public ModelAndView addDueno(@Valid Dueno dueno, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Map<String, String> validations = this.duenoService.verifyField(dueno);
        if (!validations.isEmpty()) {
            model.addObject("errors", validations);
            model.setViewName("dueno/create.html");
        } else {
            model.addObject("dueno", this.duenoService.guardarDueno(dueno));
            model.addObject("successMsg", "Dueño agregado exitosamente");
            model.setViewName("dueno/create.html");
        }
        return model;
    }
}
package com.example.gestion.controller.vehicule.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehicules")
public class VehiculeController {

    // @GetMapping("/create")
    // public String creation(Model model) {
    //     return "trajet/trajetCreation";
    // }

    @GetMapping("/list")
    public String list(Model model) {
        return "vehicule/listVehicule";
    }

    @GetMapping("/{id}")
public String detailVehicule(@PathVariable Long id, Model model) {
    model.addAttribute("vehiculeId", id);
    return "vehicule/vehiculeDetail";
}

}

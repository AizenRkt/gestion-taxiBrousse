package com.example.gestion.controller.trajet.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trajets")
public class TrajetController {

    @GetMapping("/create")
    public String creation(Model model) {
        return "trajet/trajetCreation";
    }

    @GetMapping("/list")
    public String list(Model model) {
        return "trajet/trajetList";
    }
}

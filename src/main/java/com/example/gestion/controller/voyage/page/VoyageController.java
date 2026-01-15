package com.example.gestion.controller.voyage.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voyages")
public class VoyageController {

    @GetMapping("/create")
    public String creation(Model model) {
        return "voyage/voyageCreation";
    }

    @GetMapping("/list")
    public String list(Model model) {
        return "voyage/voyageList";
    }
}


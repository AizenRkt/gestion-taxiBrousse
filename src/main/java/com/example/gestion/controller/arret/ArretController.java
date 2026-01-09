package com.example.gestion.controller.arret;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/arrets")
public class ArretController {

    @GetMapping("/listing")
    public String listing(Model model) {
        return "arret/arret";
    }
}

package com.example.gestion.controller.arret;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voyages")
public class VoyageController {

    @GetMapping("/billets")
    public String listing(Model model) {
        return "billet/billet";
    }

     @GetMapping("/confirmationAchat")
    public String confirmationAchat(Model model) {
        return "billet/confirmationachat";
    }
      
    @GetMapping("/voyagesReserves")
    public String voyagesReserves(Model model) {
        return "billet/listeVoyageEnregistre";
    }
    
}

package com.example.gestion.controller.reservation.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    
    @GetMapping("/voyages")
    public String listVoyage(Model model) {
        return "reservation/voyageDispo";
    }

    @GetMapping("/voyage/confirmation") 
    public String confirmationAchat(Model model) {
        return "reservation/confirmationAchat";
    }

    @GetMapping("/voyage/reserver") 
    public String listReservation(Model model) {
        return "reservation/listeVoyageReserver";
    }
}

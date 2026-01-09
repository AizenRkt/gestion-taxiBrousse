package com.example.gestion.controller.visuals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String dashboard(Model model) {
        String dbStatus = "Non testé";
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            if (result != null && result == 1) {
                dbStatus = "Connectée";
            }
        } catch (Exception e) {
            dbStatus = "Erreur: " + e.getMessage();
        }

        model.addAttribute("dbStatus", dbStatus);
        return "visuals/dashboard";
    }
}

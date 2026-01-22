package com.example.gestion.controller.societe.api;

import com.example.gestion.model.societe.Societe;
import com.example.gestion.repository.societe.SocieteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/societes")
public class SocieteApiController {

    private final SocieteRepository societeRepository;

    public SocieteApiController(SocieteRepository societeRepository) {
        this.societeRepository = societeRepository;
    }

    /**
     * Retourne toutes les sociétés
     */
    @GetMapping
    public List<Societe> getAllSocietes() {
        return societeRepository.findAll();
    }
}

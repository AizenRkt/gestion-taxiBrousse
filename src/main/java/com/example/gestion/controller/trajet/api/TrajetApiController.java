package com.example.gestion.controller.trajet.api;

import com.example.gestion.dto.trajet.TrajetRequest;
import com.example.gestion.dto.trajet.TrajetResponse;
import com.example.gestion.model.trajet.Arret;
import com.example.gestion.model.trajet.Trajet;
import com.example.gestion.model.trajet.TrajetArret;
import com.example.gestion.repository.trajet.ArretRepository;
import com.example.gestion.repository.trajet.TrajetRepository;
import com.example.gestion.service.trajet.TrajetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trajets")
public class TrajetApiController {

    private final TrajetService trajetService;
    private final ArretRepository arretRepository;
    private final TrajetRepository trajetRepository;

    public TrajetApiController(TrajetService trajetService, ArretRepository arretRepository, TrajetRepository trajetRepository) {
        this.trajetService = trajetService;
        this.arretRepository = arretRepository;
        this.trajetRepository = trajetRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTrajet(@RequestBody TrajetRequest request) {

        if (request.getCodeTrajet() == null || request.getArretsOrdre() == null || request.getArretsOrdre().isEmpty()) {
            return ResponseEntity.badRequest().body("Code trajet et arrêts obligatoires");
        }

        Trajet trajet = new Trajet();
        trajet.setCodeTrajet(request.getCodeTrajet());
        trajet.setDescription(request.getDescription());

        // Création des TrajetArret
        List<TrajetArret> arretsList = new ArrayList<>();
        int ordre = 1;
        for(Long id : request.getArretsOrdre()) {
            Arret arret = arretRepository.findById(id).orElse(null);
            if(arret != null) {
                TrajetArret ta = new TrajetArret();
                ta.setTrajet(trajet);
                ta.setArret(arret);
                ta.setOrdre(ordre++);
                arretsList.add(ta);
            }
        }
        trajet.setArrets(arretsList);
        trajetService.save(trajet);

        return ResponseEntity.ok("Trajet créé avec succès !");
    }

    @GetMapping
    public List<TrajetResponse> getTrajets(@RequestParam(required = false) List<String> arret) {
        List<Trajet> trajets = trajetRepository.findAllWithFilter(arret);
        return trajets.stream().map(t -> {
            TrajetResponse dto = new TrajetResponse();
            dto.setId(t.getIdTrajet());
            dto.setCodeTrajet(t.getCodeTrajet());
            dto.setDescription(t.getDescription());
            dto.setArrets(t.getArrets().stream()
                            .map(ta -> ta.getArret().getNom())
                            .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

}

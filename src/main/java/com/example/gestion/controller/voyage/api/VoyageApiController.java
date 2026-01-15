package com.example.gestion.controller.voyage.api;

import com.example.gestion.model.chauffeur.Chauffeur;
import com.example.gestion.model.trajet.Trajet;
import com.example.gestion.model.vehicule.Vehicule;
import com.example.gestion.model.voyage.Voyage;
import com.example.gestion.repository.vehicule.VehiculeRepository;
import com.example.gestion.repository.chauffeur.ChauffeurRepository;
import com.example.gestion.repository.trajet.TrajetRepository;
import com.example.gestion.service.voyage.VoyageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/voyages")
public class VoyageApiController {

    private final VoyageService voyageService;
    private final VehiculeRepository vehiculeRepository;
    private final ChauffeurRepository chauffeurRepository;
    private final TrajetRepository trajetRepository;

    public VoyageApiController(VoyageService voyageService, VehiculeRepository vehiculeRepository,
                              ChauffeurRepository chauffeurRepository,
                              TrajetRepository trajetRepository) {
        this.voyageService = voyageService;
        this.vehiculeRepository = vehiculeRepository;
        this.chauffeurRepository = chauffeurRepository;
        this.trajetRepository = trajetRepository;
    }

    @GetMapping
    public List<Voyage> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voyage> getVoyageById(@PathVariable Long id) {
        return voyageService.getVoyageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Voyage> createVoyage(@RequestBody Map<String, Object> body) {

        Long idTrajet = Long.valueOf(body.get("idTrajet").toString());
        Long idVehicule = Long.valueOf(body.get("idVehicule").toString());
        Long idChauffeur = Long.valueOf(body.get("idChauffeur").toString());

        LocalDateTime dateDepart = LocalDateTime.parse(body.get("dateDepart").toString());
        LocalDateTime dateArrivee = LocalDateTime.parse(body.get("dateArrivee").toString());

        Trajet trajet = trajetRepository.findById(idTrajet)
                .orElseThrow(() -> new RuntimeException("Trajet introuvable"));

        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        Chauffeur chauffeur = chauffeurRepository.findById(idChauffeur)
                .orElseThrow(() -> new RuntimeException("Chauffeur introuvable"));

        Voyage voyage = new Voyage();
        voyage.setTrajet(trajet);
        voyage.setVehicule(vehicule);
        voyage.setChauffeur(chauffeur);
        voyage.setDateDepart(dateDepart);
        voyage.setDateArrivee(dateArrivee);

        Voyage saved = voyageService.createVoyage(voyage);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voyage> updateVoyage(@PathVariable Long id, @RequestBody Voyage voyageDetails) {
        Voyage updated = voyageService.updateVoyage(id, voyageDetails);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyage(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.gestion.controller.trajet;
import com.example.gestion.dto.TrajetDTO;
import com.example.gestion.model.Trajet;
import com.example.gestion.service.trajet.TrajetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trajets")
public class TrajetApiController {

    private final TrajetService trajetService;

    public TrajetApiController(TrajetService trajetService) {
        this.trajetService = trajetService;
    }

    // GET /api/trajets
    @GetMapping
    public List<Trajet> getAllTrajets() {
        return trajetService.getAllTrajets();
    }

     @GetMapping("/depart-arrivee")
    public List<TrajetDTO> getDepartArrivee() {
        return trajetService.getDepartArrivee();
    }

    // GET /api/trajets/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Trajet> getTrajetById(@PathVariable Long id) {
        return trajetService.getTrajetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/trajets
    @PostMapping
    public Trajet createTrajet(@RequestBody Trajet trajet) {
        return trajetService.createTrajet(trajet);
    }

    // PUT /api/trajets/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Trajet> updateTrajet(@PathVariable Long id, @RequestBody Trajet trajetDetails) {
        try {
            Trajet updatedTrajet = trajetService.updateTrajet(id, trajetDetails);
            return ResponseEntity.ok(updatedTrajet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/trajets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id) {
        trajetService.deleteTrajet(id);
        return ResponseEntity.noContent().build();
    }
}

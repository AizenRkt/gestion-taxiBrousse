package com.example.gestion.controller.voyage;

import com.example.gestion.model.Voyage;
import com.example.gestion.service.voyage.VoyageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voyages")
public class VoyageApiController {

    private final VoyageService voyageService;

    public VoyageApiController(VoyageService voyageService) {
        this.voyageService = voyageService;
    }

    // GET /api/voyages
    @GetMapping
    public List<Voyage> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    // GET /api/voyages/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Voyage> getVoyageById(@PathVariable Long id) {
        return voyageService.getVoyageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/voyages
    @PostMapping
    public Voyage createVoyage(@RequestBody Voyage voyage) {
        return voyageService.createVoyage(voyage);
    }

    // PUT /api/voyages/{id}
    // @PutMapping("/{id}")
    // public Voyage updateVoyage(@PathVariable Long id, @RequestBody Voyage voyage) {
    //     return voyageService.updateVoyage(id, voyage);
    // }

    // DELETE /api/voyages/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyage(id);
        return ResponseEntity.noContent().build();
    }
}

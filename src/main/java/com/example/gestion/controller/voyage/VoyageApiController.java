package com.example.gestion.controller.voyage;
import com.example.gestion.dto.VoyageDTO;
import com.example.gestion.dto.TrajetDTO;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
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
    public List<VoyageDTO> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    // GET /api/voyages/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Voyage> getVoyageById(@PathVariable Long id) {
        return voyageService.getVoyageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  @GetMapping("/voyagesDisponibles")
public ResponseEntity<List<VoyageDTO>> rechercherVoyages(
        @RequestParam String depart,
        @RequestParam String arrivee,
        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime date
) {
    TrajetDTO dto = new TrajetDTO();
    dto.setDepart(depart);
    dto.setArrivee(arrivee);
    dto.setDateDepart(date);

    List<VoyageDTO> resultats = voyageService.getVoyagesDisponiblesParTrajet(dto);

    if (resultats.isEmpty()) {
        return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(resultats);
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

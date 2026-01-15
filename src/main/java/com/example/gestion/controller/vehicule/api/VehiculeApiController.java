package com.example.gestion.controller.vehicule.api;

import com.example.gestion.model.vehicule.Vehicule;
import com.example.gestion.model.vehicule.VehiculeStatus;
import com.example.gestion.repository.place.PlaceRepository;
import com.example.gestion.dto.place.VehiculePlaceSummary;
import com.example.gestion.enums.vehicule.VehiculeStatusEnum;
import com.example.gestion.service.vehicule.VehiculeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeApiController {

    private final VehiculeService service;
    private final PlaceRepository placeRepository;

    public VehiculeApiController(VehiculeService service, PlaceRepository placeRepository) {
        this.service = service;
        this.placeRepository = placeRepository;
    }

    // ===== GET ALL =====
    @GetMapping
    public List<Vehicule> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}/places")
    public List<VehiculePlaceSummary> getVehiculePlaceSummaries(@PathVariable int id) {
    // Récupère la liste brute d'Object[] depuis le repository
    List<Object[]> results = placeRepository.findByIdVehicule(id);

    // Map chaque Object[] vers un DTO
    List<VehiculePlaceSummary> summaries = results.stream().map(row -> {
        VehiculePlaceSummary vps = new VehiculePlaceSummary();
        vps.setIdVehicule(((Number) row[0]).intValue());         // id_vehicule
        vps.setImmatriculation((String) row[1]);                 // immatriculation
        vps.setMarque((String) row[2]);                          // marque
        vps.setPlaceType((String) row[3]);                       // place_type
        vps.setNombrePlaces(((Number) row[4]).intValue());       // nombre_places
        vps.setMontantMax((BigDecimal) row[5]);                  // montant_max
        return vps;
    }).toList();  // Java 16+, sinon utiliser collect(Collectors.toList())

    return summaries;
}

    // ===== GET ONE =====
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getOne(@PathVariable Long id) {
        Vehicule v = service.findById(id);
        return v != null ? ResponseEntity.ok(v) : ResponseEntity.notFound().build();
    }

    // ===== CREATE =====
    @PostMapping
    public Vehicule create(@RequestBody Vehicule v) {
        return service.create(v);
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> update(@PathVariable Long id, @RequestBody Vehicule v) {
        Vehicule updated = service.update(id, v);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ===== UPDATE STATUS =====
    @PutMapping("/{id}/status")
    public VehiculeStatus updateStatus(@PathVariable Long id, @RequestParam VehiculeStatusEnum status) {
        return service.updateStatus(id, status);
    }
}

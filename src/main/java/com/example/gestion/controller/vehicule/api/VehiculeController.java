package com.example.gestion.controller.vehicule.api;

import com.example.gestion.model.vehicule.Vehicule;
import com.example.gestion.model.vehicule.VehiculeStatus;
import com.example.gestion.enums.vehicule.VehiculeStatusEnum;
import com.example.gestion.service.vehicule.VehiculeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final VehiculeService service;

    public VehiculeController(VehiculeService service) {
        this.service = service;
    }

    // ===== GET ALL =====
    @GetMapping
    public List<Vehicule> getAll() {
        return service.findAll();
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

package com.example.gestion.controller.chauffeur.api;

import com.example.gestion.enums.chauffeur.ChauffeurStatusEnum;
import com.example.gestion.model.chauffeur.Chauffeur;
import com.example.gestion.model.chauffeur.ChauffeurStatus;
import com.example.gestion.enums.chauffeur.ChauffeurStatusEnum;
import com.example.gestion.service.chauffeur.ChauffeurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chauffeurs")
public class ChauffeurController {

    private final ChauffeurService service;

    public ChauffeurController(ChauffeurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Chauffeur> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chauffeur> getOne(@PathVariable Long id) {
        Chauffeur c = service.findById(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Chauffeur create(@RequestBody Chauffeur c) {
        return service.create(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chauffeur> update(@PathVariable Long id, @RequestBody Chauffeur c) {
        Chauffeur updated = service.update(id, c);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ChauffeurStatus updateStatus(@PathVariable Long id, @RequestParam ChauffeurStatusEnum status) {
        return service.updateStatus(id, status);
    }
}
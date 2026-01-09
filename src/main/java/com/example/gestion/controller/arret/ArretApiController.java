package com.example.gestion.controller.arret;

import com.example.gestion.model.Arret;
import com.example.gestion.service.arret.ArretService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arrets")
public class ArretApiController {

    private final ArretService arretService;

    public ArretApiController(ArretService arretService) {
        this.arretService = arretService;
    }

    // GET /api/arrets
    @GetMapping
    public List<Arret> getAllArrets() {
        return arretService.getAllArrets();
    }

    // GET /api/arrets/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Arret> getArretById(@PathVariable Long id) {
        return arretService.getArretById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/arrets
    @PostMapping
    public Arret createArret(@RequestBody Arret arret) {
        return arretService.createArret(arret);
    }

    // PUT /api/arrets/{id}
    @PutMapping("/{id}")
    public Arret updateArret(@PathVariable Long id, @RequestBody Arret arretDetails) {
        return arretService.updateArret(id, arretDetails);
    }

    // DELETE /api/arrets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArret(@PathVariable Long id) {
        arretService.deleteArret(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.gestion.service.trajet;

import com.example.gestion.model.trajet.Arret;
import com.example.gestion.repository.trajet.ArretRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArretService {

    private final ArretRepository arretRepository;

    public ArretService(ArretRepository arretRepository) {
        this.arretRepository = arretRepository;
    }

    public List<Arret> findAll() {
        return arretRepository.findAll();
    }

    public Optional<Arret> findById(Long id) {
        return arretRepository.findById(id);
    }

    public Arret save(Arret arret) {
        return arretRepository.save(arret);
    }

    public void deleteById(Long id) {
        arretRepository.deleteById(id);
    }

    public Arret updateArret(Long id, Arret arretDetails) {
        return arretRepository.findById(id).map(arret -> {
            arret.setNom(arretDetails.getNom());
            return arretRepository.save(arret);
        }).orElseThrow(() -> new RuntimeException("Arret non trouvé avec l'id " + id));
    }
}
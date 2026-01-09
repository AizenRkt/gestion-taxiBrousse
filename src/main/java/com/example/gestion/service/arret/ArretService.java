package com.example.gestion.service.arret;

import com.example.gestion.model.Arret;
import com.example.gestion.repository.arret.ArretRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArretService {

    private final ArretRepository arretRepository;

    public ArretService(ArretRepository arretRepository) {
        this.arretRepository = arretRepository;
    }

    public List<Arret> getAllArrets() {
        return arretRepository.findAll();
    }

    public Optional<Arret> getArretById(Long id) {
        return arretRepository.findById(id);
    }

    public Arret createArret(Arret arret) {
        return arretRepository.save(arret);
    }

    public Arret updateArret(Long id, Arret arretDetails) {
        return arretRepository.findById(id).map(arret -> {
            arret.setNom(arretDetails.getNom());
            return arretRepository.save(arret);
        }).orElseThrow(() -> new RuntimeException("Arret non trouvé avec l'id " + id));
    }

    public void deleteArret(Long id) {
        arretRepository.deleteById(id);
    }
}

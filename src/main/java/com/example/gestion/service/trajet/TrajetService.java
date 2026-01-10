package com.example.gestion.service.trajet;

import com.example.gestion.model.trajet.Trajet;
import com.example.gestion.repository.trajet.TrajetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetService {

    private final TrajetRepository trajetRepository;

    public TrajetService(TrajetRepository trajetRepository) {
        this.trajetRepository = trajetRepository;
    }

    public List<Trajet> findAll() {
        return trajetRepository.findAll();
    }

    public Optional<Trajet> findById(Long id) {
        return trajetRepository.findById(id);
    }

    public Trajet save(Trajet trajet) {
        return trajetRepository.save(trajet);
    }

    public void deleteById(Long id) {
        trajetRepository.deleteById(id);
    }
}
package com.example.gestion.service.trajet;

import com.example.gestion.model.trajet.TrajetArret;
import com.example.gestion.repository.trajet.TrajetArretRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetArretService {

    private final TrajetArretRepository trajetArretRepository;

    public TrajetArretService(TrajetArretRepository trajetArretRepository) {
        this.trajetArretRepository = trajetArretRepository;
    }

    public List<TrajetArret> findAll() {
        return trajetArretRepository.findAll();
    }

    public Optional<TrajetArret> findById(Long id) {
        return trajetArretRepository.findById(id);
    }

    public TrajetArret save(TrajetArret trajetArret) {
        return trajetArretRepository.save(trajetArret);
    }

    public void deleteById(Long id) {
        trajetArretRepository.deleteById(id);
    }
}
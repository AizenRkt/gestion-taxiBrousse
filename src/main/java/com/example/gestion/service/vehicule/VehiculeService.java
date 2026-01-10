package com.example.gestion.service.vehicule;

import com.example.gestion.model.vehicule.Vehicule;
import com.example.gestion.repository.vehicule.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    public Optional<Vehicule> findById(Long id) {
        return vehiculeRepository.findById(id);
    }

    public Vehicule save(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public void deleteById(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
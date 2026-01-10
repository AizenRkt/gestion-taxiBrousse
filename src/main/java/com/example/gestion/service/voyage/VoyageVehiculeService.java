package com.example.gestion.service;

import com.example.gestion.model.voyage.VoyageVehicule;
import com.example.gestion.repository.voyage.VoyageVehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageVehiculeService {

    private final VoyageVehiculeRepository voyageVehiculeRepository;

    public VoyageVehiculeService(VoyageVehiculeRepository voyageVehiculeRepository) {
        this.voyageVehiculeRepository = voyageVehiculeRepository;
    }

    public List<VoyageVehicule> findAll() {
        return voyageVehiculeRepository.findAll();
    }

    public Optional<VoyageVehicule> findById(Long id) {
        return voyageVehiculeRepository.findById(id);
    }

    public VoyageVehicule save(VoyageVehicule voyageVehicule) {
        return voyageVehiculeRepository.save(voyageVehicule);
    }

    public void deleteById(Long id) {
        voyageVehiculeRepository.deleteById(id);
    }
}
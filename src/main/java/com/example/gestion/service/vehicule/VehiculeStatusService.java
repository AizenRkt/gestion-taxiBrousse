package com.example.gestion.service.vehicule;

import com.example.gestion.model.vehicule.VehiculeStatus;
import com.example.gestion.repository.vehicule.VehiculeStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeStatusService {

    private final VehiculeStatusRepository vehiculeStatusRepository;

    public VehiculeStatusService(VehiculeStatusRepository vehiculeStatusRepository) {
        this.vehiculeStatusRepository = vehiculeStatusRepository;
    }

    public List<VehiculeStatus> findAll() {
        return vehiculeStatusRepository.findAll();
    }

    public Optional<VehiculeStatus> findById(Long id) {
        return vehiculeStatusRepository.findById(id);
    }

    public VehiculeStatus save(VehiculeStatus vehiculeStatus) {
        return vehiculeStatusRepository.save(vehiculeStatus);
    }

    public void deleteById(Long id) {
        vehiculeStatusRepository.deleteById(id);
    }
}
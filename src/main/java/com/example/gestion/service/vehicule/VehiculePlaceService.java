package com.example.gestion.service.vehicule;

import com.example.gestion.model.vehicule.VehiculePlace;
import com.example.gestion.repository.vehicule.VehiculePlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculePlaceService {

    private final VehiculePlaceRepository repo;

    public VehiculePlaceService(VehiculePlaceRepository repo) {
        this.repo = repo;
    }

    public List<VehiculePlace> findAll() {
        return repo.findAll();
    }

    public Optional<VehiculePlace> findById(Long id) {
        return repo.findById(id);
    }

    public VehiculePlace save(VehiculePlace vp) {
        return repo.save(vp);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

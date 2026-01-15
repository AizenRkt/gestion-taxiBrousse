package com.example.gestion.service.vehicule;

import com.example.gestion.enums.vehicule.VehiculeStatusEnum;
import com.example.gestion.model.vehicule.Vehicule;
import com.example.gestion.model.vehicule.VehiculeStatus;
import com.example.gestion.repository.vehicule.VehiculeRepository;
import com.example.gestion.repository.vehicule.VehiculeStatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeStatusRepository statusRepository;

    public VehiculeService(VehiculeRepository vehiculeRepository, VehiculeStatusRepository statusRepository) {
        this.vehiculeRepository = vehiculeRepository;
        this.statusRepository = statusRepository;
    }

    // ===== CRUD Vehicule =====
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    public Vehicule create(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public Vehicule update(Long id, Vehicule updated) {
        return vehiculeRepository.findById(id).map(v -> {
            v.setImmatriculation(updated.getImmatriculation());
            v.setMarque(updated.getMarque());
            v.setCapacitePassager(updated.getCapacitePassager());
            v.setDateMiseEnService(updated.getDateMiseEnService());
            return vehiculeRepository.save(v);
        }).orElse(null);
    }

    public void delete(Long id) {
        vehiculeRepository.deleteById(id);
    }

    // ===== Mettre à jour le statut =====
    public VehiculeStatus updateStatus(Long vehiculeId, VehiculeStatusEnum status) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId).orElseThrow();
        VehiculeStatus s = new VehiculeStatus();
        s.setVehicule(vehicule);
        s.setStatus(status);
        s.setDateStatus(LocalDateTime.now());
        return statusRepository.save(s);
    }
}

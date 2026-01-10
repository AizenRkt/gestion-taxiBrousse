package com.example.gestion.service.vehicule;

import com.example.gestion.model.Vehicule;
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

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public Optional<Vehicule> getVehiculeById(Long id) {
        return vehiculeRepository.findById(id);
    }

    public Vehicule createVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    public Vehicule updateVehicule(Long id, Vehicule vehiculeDetails) {
        return vehiculeRepository.findById(id).map(vehicule -> {
            vehicule.setImmatriculation(vehiculeDetails.getImmatriculation());
            vehicule.setMarque(vehiculeDetails.getMarque());
            vehicule.setCapacitePassager(vehiculeDetails.getCapacitePassager());
            vehicule.setDateMiseEnService(vehiculeDetails.getDateMiseEnService());
            return vehiculeRepository.save(vehicule);
        }).orElseThrow(() -> new RuntimeException("Vehicule non trouvé avec l'id " + id));
    }

    public void deleteVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}

package com.example.gestion.repository.voyage;

import com.example.gestion.model.voyage.VoyageVehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoyageVehiculeRepository extends JpaRepository<VoyageVehicule, Long> {
}
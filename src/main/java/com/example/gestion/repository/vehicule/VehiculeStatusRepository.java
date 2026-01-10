package com.example.gestion.repository.vehicule;

import com.example.gestion.model.vehicule.VehiculeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeStatusRepository extends JpaRepository<VehiculeStatus, Long> {
}
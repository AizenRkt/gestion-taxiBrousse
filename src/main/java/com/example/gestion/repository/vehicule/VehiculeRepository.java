package com.example.gestion.repository.vehicule;

import com.example.gestion.model.vehicule.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
}
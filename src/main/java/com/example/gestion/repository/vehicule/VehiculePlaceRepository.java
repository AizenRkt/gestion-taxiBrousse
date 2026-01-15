package com.example.gestion.repository.vehicule;

import com.example.gestion.model.vehicule.VehiculePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculePlaceRepository extends JpaRepository<VehiculePlace, Long> {
}

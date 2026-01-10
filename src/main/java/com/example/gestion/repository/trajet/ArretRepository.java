package com.example.gestion.repository.trajet;

import com.example.gestion.model.trajet.Arret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArretRepository extends JpaRepository<Arret, Long> {
}
package com.example.gestion.repository.trajet;

import com.example.gestion.model.trajet.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {
}
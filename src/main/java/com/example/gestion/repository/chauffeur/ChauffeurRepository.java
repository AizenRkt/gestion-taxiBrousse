package com.example.gestion.repository.chauffeur;

import com.example.gestion.model.chauffeur.Chauffeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {
}
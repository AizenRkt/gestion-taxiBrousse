package com.example.gestion.repository.chauffeur;

import com.example.gestion.model.chauffeur.ChauffeurStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChauffeurStatusRepository extends JpaRepository<ChauffeurStatus, Long> {
}
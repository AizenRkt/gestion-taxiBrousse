package com.example.gestion.repository.voyage;

import com.example.gestion.model.voyage.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
}
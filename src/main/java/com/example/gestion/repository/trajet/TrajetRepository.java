package com.example.gestion.repository.trajet;

import com.example.gestion.model.trajet.Trajet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface TrajetRepository extends JpaRepository<Trajet, Long> {

    @Query("SELECT DISTINCT t FROM Trajet t " +
        "JOIN t.arrets ta " +
        "JOIN ta.arret a " +
        "WHERE a.nom IN :nomsArrets")
    List<Trajet> findAllWithFilter(@Param("nomsArrets") List<String> nomsArrets);
}
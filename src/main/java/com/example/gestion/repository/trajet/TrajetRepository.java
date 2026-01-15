package com.example.gestion.repository.trajet;

import com.example.gestion.model.trajet.Trajet;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;



public interface TrajetRepository extends JpaRepository<Trajet, Long> {


    @Query("SELECT DISTINCT t FROM Trajet t " +
           "LEFT JOIN FETCH t.arrets ta " +
           "LEFT JOIN FETCH ta.arret " +
           "ORDER BY t.idTrajet")
    List<Trajet> findAllWithArrets();

    @Query(value = "SELECT DISTINCT chiffre_affaire_total FROM chiffre_affaire_trajet WHERE id_trajet = :idTrajet", nativeQuery = true)
    BigDecimal findChiffreAffaireByIdTrajet(@Param("idTrajet") Long idTrajet);
}
package com.example.gestion.repository.societe;

import com.example.gestion.model.societe.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocieteRepository extends JpaRepository<Societe, Integer> {

    // Trouver une société par son nom
    Societe findByNom(String nom);

    // Vérifier si une société existe déjà avec ce nom
    boolean existsByNom(String nom);
}

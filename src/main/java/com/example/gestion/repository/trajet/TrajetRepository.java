package com.example.gestion.repository.trajet;

import com.example.gestion.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    // Tu peux ajouter des méthodes personnalisées si besoin
}

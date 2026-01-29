package com.example.gestion.repository.produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.gestion.model.produit.VenteProduit;
import java.util.List;

public interface VenteProduitRepository extends JpaRepository<VenteProduit, Long> {

    List<VenteProduit> findByReservationIdReservation(Long idReservation);

    @Query("""
        SELECT vp FROM VenteProduit vp
        JOIN vp.reservation r
        JOIN r.voyage v
        WHERE v.idVoyage = :idVoyage
    """)
    List<VenteProduit> findByVoyage(@Param("idVoyage") Long idVoyage);
}

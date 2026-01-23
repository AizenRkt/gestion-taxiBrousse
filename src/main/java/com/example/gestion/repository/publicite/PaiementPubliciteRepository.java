package com.example.gestion.repository.publicite;

import com.example.gestion.model.publicite.PaiementPublicite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiementPubliciteRepository extends JpaRepository<PaiementPublicite, Long> {

    @Query("""
        SELECT pp
        FROM PaiementPublicite pp
        JOIN pp.societe s
        JOIN DiffusionPubliciteVoyage dpv ON dpv.publicite.societe.idSociete = s.idSociete
        JOIN dpv.voyage v
        WHERE v.idVoyage = :idVoyage
    """)
    List<PaiementPublicite> findPaiementsByVoyage(@Param("idVoyage") Long idVoyage);

}

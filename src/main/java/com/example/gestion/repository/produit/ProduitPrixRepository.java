package com.example.gestion.repository.produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.gestion.model.produit.ProduitPrix;
import java.time.LocalDate;
import java.util.Optional;

public interface ProduitPrixRepository extends JpaRepository<ProduitPrix, Long> {

    @Query("""
        SELECT pp FROM ProduitPrix pp
        WHERE pp.produit.idProduit = :idProduit
          AND pp.dateModif <= :date
        ORDER BY pp.dateModif DESC
    """)
    Optional<ProduitPrix> findPrixAtDate(
        @Param("idProduit") Long idProduit,
        @Param("date") LocalDate date
    );
}

package com.example.gestion.repository.publicite;

import com.example.gestion.model.tarif.TarifPublicite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.gestion.model.tarif.TarifPublicite;
import java.time.LocalDate;
import java.util.List;

public interface TarifPubliciteRepository extends JpaRepository<TarifPublicite, Integer> {

    @Query("""
        SELECT t FROM TarifPublicite t
        WHERE t.dateModif <= :date
        ORDER BY t.dateModif DESC
    """)
    List<TarifPublicite> findTarifsApplicables(@Param("date") LocalDate date);

    default TarifPublicite findTarifApplicable(LocalDate date) {
        List<TarifPublicite> list = findTarifsApplicables(date);
        return list.isEmpty() ? null : list.get(0);
    }
}

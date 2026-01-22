package com.example.gestion.repository.publicite;
import com.example.gestion.model.publicite.DiffusionPubliciteVoyage;
import com.example.gestion.model.publicite.Publicite;
import com.example.gestion.model.societe.Societe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PubliciteRepository extends JpaRepository<Publicite, Integer> {

   @Query("SELECT d FROM DiffusionPubliciteVoyage d JOIN d.publicite p JOIN p.societe s")
List<DiffusionPubliciteVoyage> findAllDiffusions();

}





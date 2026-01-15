package com.example.gestion.repository.tarif;

import com.example.gestion.model.tarif.TarifPlaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifPlaceTypeRepository extends JpaRepository<TarifPlaceType, Long> {
}

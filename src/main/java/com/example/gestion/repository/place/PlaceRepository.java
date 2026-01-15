package com.example.gestion.repository.place;
import java.util.List;
import com.example.gestion.dto.place.VehiculePlaceSummary;
import com.example.gestion.model.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
     // Requête native pour filtrer par id_vehicule
    @Query(value = "SELECT * FROM vehicule_place_summary WHERE id_vehicule = :idVehicule", nativeQuery = true)
   List<Object[]> findByIdVehicule(@Param("idVehicule") Integer idVehicule);
}

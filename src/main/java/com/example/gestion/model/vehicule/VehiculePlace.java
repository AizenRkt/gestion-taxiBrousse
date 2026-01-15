package com.example.gestion.model.vehicule;
import com.example.gestion.model.place.Place;
import com.example.gestion.model.place.PlaceType;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicule_place")
public class VehiculePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicule_place")
    private Long idVehiculePlace;

    @ManyToOne
    @JoinColumn(name = "id_vehicule", nullable = false)
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "id_place", nullable = false)
    private Place place;

    @ManyToOne
    @JoinColumn(name = "id_place_type", nullable = false)
    private PlaceType placeType;

    // Getters & Setters
    public Long getIdVehiculePlace() { return idVehiculePlace; }
    public void setIdVehiculePlace(Long idVehiculePlace) { this.idVehiculePlace = idVehiculePlace; }

    public Vehicule getVehicule() { return vehicule; }
    public void setVehicule(Vehicule vehicule) { this.vehicule = vehicule; }

    public Place getPlace() { return place; }
    public void setPlace(Place place) { this.place = place; }

    public PlaceType getPlaceType() { return placeType; }
    public void setPlaceType(PlaceType placeType) { this.placeType = placeType; }
}

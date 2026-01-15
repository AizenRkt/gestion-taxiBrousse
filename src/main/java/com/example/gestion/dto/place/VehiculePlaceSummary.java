package com.example.gestion.dto.place;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicule_place_summary") // Nom exact de la vue
public class VehiculePlaceSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // On a besoin d'un @Id, sinon JPA refuse

    @Column(name = "id_vehicule")
    private Integer idVehicule;

    @Column(name = "immatriculation")
    private String immatriculation;

    @Column(name = "marque")
    private String marque;

    @Column(name = "place_type")
    private String placeType;

    @Column(name = "nombre_places")
    private Integer nombrePlaces;

    @Column(name = "montant_max")
    private BigDecimal montantMax;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getIdVehicule() { return idVehicule; }
    public void setIdVehicule(Integer idVehicule) { this.idVehicule = idVehicule; }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getPlaceType() { return placeType; }
    public void setPlaceType(String placeType) { this.placeType = placeType; }

    public Integer getNombrePlaces() { return nombrePlaces; }
    public void setNombrePlaces(Integer nombrePlaces) { this.nombrePlaces = nombrePlaces; }

    public BigDecimal getMontantMax() { return montantMax; }
    public void setMontantMax(BigDecimal montantMax) { this.montantMax = montantMax; }
}

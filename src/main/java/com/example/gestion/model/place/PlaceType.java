package com.example.gestion.model.place;

import jakarta.persistence.*;

@Entity
@Table(name = "place_type")
public class PlaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place_type")
    private Long idPlaceType;

    @Column(name = "libelle", nullable = false, length = 50)
    private String libelle;

    // Getters & Setters
    public Long getIdPlaceType() { return idPlaceType; }
    public void setIdPlaceType(Long idPlaceType) { this.idPlaceType = idPlaceType; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}

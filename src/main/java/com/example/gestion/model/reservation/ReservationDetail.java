package com.example.gestion.model.reservation;

import jakarta.persistence.*;

import java.math.BigDecimal;

import com.example.gestion.model.place.PlaceType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.gestion.model.client.PassagerType;

@Entity
@Table(name = "reservation_detail")
public class ReservationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetail;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    @JsonBackReference
    private Reservation reservation;


    @ManyToOne
    @JoinColumn(name = "id_place_type", nullable = false)
    private PlaceType placeType;

    @ManyToOne
    @JoinColumn(name = "id_passager_type", nullable = false)
    private PassagerType passagerType;

    private int nombrePlaces;

    @Column(nullable = false)
    private BigDecimal prixApplique;

    public Long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public PassagerType getPassagerType() {
        return passagerType;
    }

    public void setPassagerType(PassagerType passagerType) {
        this.passagerType = passagerType;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public BigDecimal getPrixApplique() {
        return prixApplique;
    }

    public void setPrixApplique(BigDecimal prixApplique) {
        this.prixApplique = prixApplique;
    }

}

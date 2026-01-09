package com.example.gestion.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @Column(name = "id_voyage", nullable = false)
    private Long idVoyage;

    @Column(name = "nb_place_reserve", nullable = false)
    private Integer nbPlaceReserve;

    @Column(name = "date_reservation", nullable = false)
    private LocalDateTime dateReservation;

    @Column(name = "total_montant", nullable = false)
    private BigDecimal totalMontant;

    // ===== Getters & Setters =====

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }

    public Integer getNbPlaceReserve() {
        return nbPlaceReserve;
    }

    public void setNbPlaceReserve(Integer nbPlaceReserve) {
        this.nbPlaceReserve = nbPlaceReserve;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public BigDecimal getTotalMontant() {
        return totalMontant;
    }

    public void setTotalMontant(BigDecimal totalMontant) {
        this.totalMontant = totalMontant;
    }
}

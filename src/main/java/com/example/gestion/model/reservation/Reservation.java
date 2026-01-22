package com.example.gestion.model.reservation;

import com.example.gestion.model.voyage.Voyage;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @Column(name = "client_nom", nullable = false, length = 100)
    private String clientNom;

    @Column(name = "client_tel", length = 20)
    private String clientTel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_voyage", nullable = false)
    private Voyage voyage;

    @Column(name = "nombre_places", nullable = false)
    private Integer nombrePlaces;

    @Column(name = "total_payer", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPayer;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReservationDetail> details;

    // ===== Lifecycle =====
    @PrePersist
    public void prePersist() {
        if (dateReservation == null) {
            dateReservation = LocalDateTime.now();
        }
    }

    // ===== Getters & Setters =====

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getClientTel() {
        return clientTel;
    }

    public void setClientTel(String clientTel) {
        this.clientTel = clientTel;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public Integer getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(Integer nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public BigDecimal getTotalPayer() {
        return totalPayer;
    }

    public void setTotalPayer(BigDecimal totalPayer) {
        this.totalPayer = totalPayer;
    }

    public List<ReservationDetail> getDetails() {
        return details;
    }
    
    public void setDetails(List<ReservationDetail> details) {
        this.details = details;
    }
}

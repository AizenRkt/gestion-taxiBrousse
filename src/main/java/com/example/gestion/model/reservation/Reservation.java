package com.example.gestion.model.reservation;

import com.example.gestion.model.client.Client;
import com.example.gestion.model.voyage.VoyageVehicule;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_voyage_vehicule", nullable = false)
    private VoyageVehicule voyageVehicule;

    @Column(name = "nombre_places", nullable = false)
    private Integer nombrePlaces;

    @Column(name = "date_reservation", nullable = false)
    private LocalDateTime dateReservation;

    // ===== Getters & Setters =====

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public VoyageVehicule getVoyageVehicule() {
        return voyageVehicule;
    }

    public void setVoyageVehicule(VoyageVehicule voyageVehicule) {
        this.voyageVehicule = voyageVehicule;
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
}

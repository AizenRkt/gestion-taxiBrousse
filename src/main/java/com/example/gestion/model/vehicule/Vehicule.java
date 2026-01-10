package com.example.gestion.model.vehicule;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.example.gestion.model.vehicule.VehiculeStatus;

@Entity
@Table(name = "vehicule")
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(nullable = false, length = 20)
    private String immatriculation;

    @Column(length = 100)
    private String marque;

    @Column
    private Integer capacitePassager;

    @Column
    private LocalDate dateMiseEnService;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dateStatus DESC")
    private List<VehiculeStatus> status;

    // Getters and Setters
    public Long getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Long idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Integer getCapacitePassager() {
        return capacitePassager;
    }

    public void setCapacitePassager(Integer capacitePassager) {
        this.capacitePassager = capacitePassager;
    }

    public LocalDate getDateMiseEnService() {
        return dateMiseEnService;
    }

    public void setDateMiseEnService(LocalDate dateMiseEnService) {
        this.dateMiseEnService = dateMiseEnService;
    }

    public List<VehiculeStatus> getStatus() {
        return status;
    }

    public void setStatuses(List<VehiculeStatus> status) {
        this.status = status;
    }

    public void addStatus(VehiculeStatus status) {
        this.status.add(status);
        status.setVehicule(this);
    }
}
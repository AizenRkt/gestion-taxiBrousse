package com.example.gestion.dto;

import java.time.LocalDateTime;

public class VoyageDTO {
    private Long idVoyage;
    private String depart;
    private String arrivee;
    private LocalDateTime dateDepart;
    private LocalDateTime dateArrivee;
    private String vehicule; // immatriculation + marque
    private double prix; // si tu veux calculer le prix depuis reservations

    // Getters and Setters
    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public LocalDateTime getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDateTime dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalDateTime getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDateTime dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
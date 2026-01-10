package com.example.gestion.model.voyage;

import com.example.gestion.model.vehicule.Vehicule;
import com.example.gestion.model.chauffeur.Chauffeur;
import jakarta.persistence.*;

@Entity
@Table(name = "voyage_vehicule")
public class VoyageVehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voyage_vehicule")
    private Long idVoyageVehicule;

    @ManyToOne
    @JoinColumn(name = "id_voyage", nullable = false)
    private Voyage voyage;

    @ManyToOne
    @JoinColumn(name = "id_vehicule", nullable = false)
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "id_chauffeur", nullable = false)
    private Chauffeur chauffeur;

    // ===== Getters & Setters =====

    public Long getIdVoyageVehicule() {
        return idVoyageVehicule;
    }

    public void setIdVoyageVehicule(Long idVoyageVehicule) {
        this.idVoyageVehicule = idVoyageVehicule;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }
}

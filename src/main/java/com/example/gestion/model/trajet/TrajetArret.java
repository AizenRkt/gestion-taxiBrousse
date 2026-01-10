package com.example.gestion.model.trajet;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "trajet_arret")
public class TrajetArret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrajetArret;

    @ManyToOne
    @JoinColumn(name = "id_trajet", nullable = false)
    private Trajet trajet;

    @ManyToOne
    @JoinColumn(name = "id_arret", nullable = false)
    private Arret arret;

    @Column(nullable = false)
    private Integer ordre;

    public Long getIdTrajetArret() {
        return idTrajetArret;
    }

    public void setIdTrajetArret(Long idTrajetArret) {
        this.idTrajetArret = idTrajetArret;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Arret getArret() {
        return arret;
    }

    public void setArret(Arret arret) {
        this.arret = arret;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }
}
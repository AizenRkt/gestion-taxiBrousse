package com.example.gestion.model.trajet;

import jakarta.persistence.*;

@Entity
@Table(name = "arret")
public class Arret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArret;

    @Column(nullable = false, length = 100)
    private String nom;

    public Long getIdArret() {
        return idArret;
    }

    public void setIdArret(Long idArret) {
        this.idArret = idArret;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
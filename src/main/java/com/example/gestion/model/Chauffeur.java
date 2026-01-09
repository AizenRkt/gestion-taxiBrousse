package com.example.gestion.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chauffeur")
public class Chauffeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChauffeur;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column
    private LocalDate dateDeNaissance;

    @OneToMany(mappedBy = "chauffeur", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dateStatus DESC")
    private List<ChauffeurStatus> status;
    
    // Getters and Setters
    public Long getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(Long idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public List<ChauffeurStatus> getStatus() {
        return status;
    }

    public void setStatuses(List<ChauffeurStatus> status) {
        this.status = status;
    }

    public void addStatus(ChauffeurStatus status) {
        this.status.add(status);
        status.setChauffeur(this);
    }
}
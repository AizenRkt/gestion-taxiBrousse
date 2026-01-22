package com.example.gestion.model.client;

import jakarta.persistence.*;

@Entity
@Table(name = "passager_type")
public class PassagerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_passager_type") 
    private Long idPassagerType;

    @Column(nullable = false, unique = true)
    private String libelle;

    public Long getIdPassagerType() {
        return idPassagerType;
    }

    public void setIdPassagerType(Long idPassagerType) {
        this.idPassagerType = idPassagerType;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

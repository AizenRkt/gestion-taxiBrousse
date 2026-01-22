package com.example.gestion.model.client;

import jakarta.persistence.*;

@Entity
@Table(name = "type_passager")
public class PassagerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypePassager;

    @Column(nullable = false, unique = true)
    private String libelle;

    public Long getIdTypePassager() {
        return idTypePassager;
    }
    
    public void setIdTypePassager(Long idTypePassager) {
        this.idTypePassager = idTypePassager;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

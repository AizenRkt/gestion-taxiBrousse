package com.example.gestion.model.remise;

import jakarta.persistence.*;

@Entity
@Table(name = "remise_type")
public class RemiseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_remise_type")
    private Long IdRemiseType;

    @Column(nullable = false, unique = true)
    private String libelle; 

    public Long getIdRemiseType() {
        return IdRemiseType;
    }

    public void setIdRemiseType(Long IdRemiseType) {
        this.IdRemiseType = IdRemiseType;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
}

package com.example.gestion.model.remise;

import jakarta.persistence.*;

@Entity
@Table(name = "type_remise")
public class RemiseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeRemise;

    @Column(nullable = false, unique = true)
    private String libelle; 

    public Long getIdTypeRemise() {
        return idTypeRemise;
    }

    public void setIdTypeRemise(Long idTypeRemise) {
        this.idTypeRemise = idTypeRemise;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
}

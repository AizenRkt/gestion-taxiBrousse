package com.example.gestion.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "trajet")
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrajet;

    @Column(nullable = false, length = 50)
    private String codeTrajet;

    @Column
    private String description;

    @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL)
    @OrderBy("ordre ASC")
    private List<TrajetArret> arrets;

    public Long getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(Long idTrajet) {
        this.idTrajet = idTrajet;
    }

    public String getCodeTrajet() {
        return codeTrajet;
    }

    public void setCodeTrajet(String codeTrajet) {
        this.codeTrajet = codeTrajet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
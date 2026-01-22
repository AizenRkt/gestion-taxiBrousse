package com.example.gestion.model.remise;

import jakarta.persistence.*;

@Entity
@Table(name = "remise_condition")
public class RemiseCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRemiseCondition;

    @ManyToOne
    @JoinColumn(name = "id_remise")
    private Remise remise;

    @Column(nullable = false)
    private String champ;

    @Column(nullable = false)
    private String valeur;

    public Long getIdRemiseCondition() {
        return idRemiseCondition;
    }

    public void setIdRemiseCondition(Long idRemiseCondition) {
        this.idRemiseCondition = idRemiseCondition;
    }

    public Remise getRemise() {
        return remise;
    }

    public void setRemise(Remise remise) {
        this.remise = remise;
    }

    public String getChamp() {
        return champ;
    }

    public void setChamp(String champ) {
        this.champ = champ;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

}

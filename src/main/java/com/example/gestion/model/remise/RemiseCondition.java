package com.example.gestion.model.remise;

import jakarta.persistence.*;

@Entity
@Table(name = "remise_condition")
public class RemiseCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCondition;

    @ManyToOne
    @JoinColumn(name = "id_remise")
    private Remise remise;

    @Column(nullable = false)
    private String champ;

    @Column(nullable = false)
    private String valeur;

    public Long getIdCondition() {
        return idCondition;
    }

    public void setIdCondition(Long idCondition) {
        this.idCondition = idCondition;
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

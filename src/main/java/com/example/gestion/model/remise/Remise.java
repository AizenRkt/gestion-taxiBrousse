package com.example.gestion.model.remise;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "remise")
public class Remise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRemise;

    @ManyToOne
    @JoinColumn(name = "id_type_remise")
    private RemiseType type;

    @Column(nullable = false)
    private BigDecimal valeur; 

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @OneToMany(mappedBy = "remise")
    private List<RemiseCondition> conditions;

    public Long getIdRemise() {
        return idRemise;
    }

    public void setIdRemise(Long idRemise) {
        this.idRemise = idRemise;
    }

    public RemiseType getType() {
        return type;
    }

    public void setType(RemiseType type) {
        this.type = type;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<RemiseCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<RemiseCondition> conditions) {
        this.conditions = conditions;
    }
}

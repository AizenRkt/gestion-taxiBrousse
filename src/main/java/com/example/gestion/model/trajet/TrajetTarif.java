package com.example.gestion.model.trajet;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "trajet_tarif")
public class TrajetTarif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trajet_tarif")
    private Long idTrajetTarif;

    @ManyToOne
    @JoinColumn(name = "id_trajet", nullable = false)
    @JsonBackReference
    private Trajet trajet;

    @Column(name = "montant", nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "date_tarif", nullable = false)
    private LocalDate dateTarif;

    // ===== Getters & Setters =====

    public Long getIdTrajetTarif() {
        return idTrajetTarif;
    }

    public void setIdTrajetTarif(Long idTrajetTarif) {
        this.idTrajetTarif = idTrajetTarif;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDate getDateTarif() {
        return dateTarif;
    }

    public void setDateTarif(LocalDate dateTarif) {
        this.dateTarif = dateTarif;
    }
}

package com.example.gestion.model.publicite;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.gestion.model.societe.Societe;

@Entity
@Table(name = "paiement_publicite")
public class PaiementPublicite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement_publicite")
    private Long idPaiementPublicite;

    @ManyToOne
    @JoinColumn(name = "id_societe", nullable = false)
    private Societe societe;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "date_paiement", nullable = false)
    private LocalDate datePaiement;

    // =====================
    // GETTERS & SETTERS
    // =====================

    public Long getIdPaiementPublicite() {
        return idPaiementPublicite;
    }

    public void setIdPaiementPublicite(Long idPaiementPublicite) {
        this.idPaiementPublicite = idPaiementPublicite;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }
}


package com.example.gestion.model.tarif;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tarif_publicite")
public class TarifPublicite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarif_publicite")
    private Integer idTarifPublicite;

    @Column(name = "montant", nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "date_modif", nullable = false)
    private LocalDate dateModif;

    // Getters & Setters
    public Integer getIdTarifPublicite() {
        return idTarifPublicite;
    }

    public void setIdTarifPublicite(Integer idTarifPublicite) {
        this.idTarifPublicite = idTarifPublicite;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDate getDateModif() {
        return dateModif;
    }

    public void setDateModif(LocalDate dateModif) {
        this.dateModif = dateModif;
    }
}

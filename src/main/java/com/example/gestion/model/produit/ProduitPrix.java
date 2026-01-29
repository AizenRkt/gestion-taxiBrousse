package com.example.gestion.model.produit;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produit_prix")
public class ProduitPrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit_prix")
    private Long idProduitPrix;

    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produit produit;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(name = "date_modif", nullable = false)
    private LocalDate dateModif;

    // Getters & Setters
    public Long getIdProduitPrix() {
        return idProduitPrix;
    }

    public void setIdProduitPrix(Long idProduitPrix) {
        this.idProduitPrix = idProduitPrix;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public LocalDate getDateModif() {
        return dateModif;
    }

    public void setDateModif(LocalDate dateModif) {
        this.dateModif = dateModif;
    }
}

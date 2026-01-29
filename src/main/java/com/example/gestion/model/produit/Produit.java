package com.example.gestion.model.produit;
import jakarta.persistence.*;
import java.util.List;
import com.example.gestion.model.produit.VenteProduit;
import com.example.gestion.model.produit.ProduitPrix;
@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit")
    private Long idProduit;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 50)
    private String code;

    // Relations
    @OneToMany(mappedBy = "produit")
    private List<VenteProduit> ventes;

    @OneToMany(mappedBy = "produit")
    private List<ProduitPrix> historiquesPrix;

    // Getters & Setters
    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<VenteProduit> getVentes() {
        return ventes;
    }

    public void setVentes(List<VenteProduit> ventes) {
        this.ventes = ventes;
    }

    public List<ProduitPrix> getHistoriquesPrix() {
        return historiquesPrix;
    }

    public void setHistoriquesPrix(List<ProduitPrix> historiquesPrix) {
        this.historiquesPrix = historiquesPrix;
    }
}

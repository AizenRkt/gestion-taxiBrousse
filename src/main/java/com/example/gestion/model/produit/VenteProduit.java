package com.example.gestion.model.produit;

import jakarta.persistence.*;
import com.example.gestion.model.reservation.Reservation;

@Entity
@Table(name = "vente_produit")
public class VenteProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vente_produit")
    private Long idVenteProduit;

    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    private Produit produit;

    @Column(name = "nbr_produit_vendu", nullable = false)
    private Integer nbrProduitVendu;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    public VenteProduit() {}

    public Long getIdVenteProduit() {
        return idVenteProduit;
    }

    public void setIdVenteProduit(Long idVenteProduit) {
        this.idVenteProduit = idVenteProduit;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Integer getNbrProduitVendu() {
        return nbrProduitVendu;
    }

    public void setNbrProduitVendu(Integer nbrProduitVendu) {
        this.nbrProduitVendu = nbrProduitVendu;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}

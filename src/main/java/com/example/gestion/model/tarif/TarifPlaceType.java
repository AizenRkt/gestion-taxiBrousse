package com.example.gestion.model.tarif;
import com.example.gestion.model.place.PlaceType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tarif_place_type")
public class TarifPlaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarif_place_type")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_place_type", nullable = false)
    private PlaceType placeType;

    @Column(name = "montant", nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "date_tarif", nullable = false)
    private LocalDate dateTarif;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PlaceType getPlaceType() { return placeType; }
    public void setPlaceType(PlaceType placeType) { this.placeType = placeType; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public LocalDate getDateTarif() { return dateTarif; }
    public void setDateTarif(LocalDate dateTarif) { this.dateTarif = dateTarif; }
}

package com.example.gestion.model.publicite;
import com.example.gestion.model.voyage.Voyage; 
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
@Entity
@Table(name = "diffusion_publicite_voyage")
public class DiffusionPubliciteVoyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicite_diffusion")
    private Integer idPubliciteDiffusion;

    @ManyToOne
    @JoinColumn(name = "id_publicite")
    private Publicite publicite;

    @ManyToOne
    @JoinColumn(name = "id_voyage")
    private Voyage voyage;

    @Column(name = "date_diffusion", nullable = false)
    private LocalDateTime dateDiffusion;

    // Champ temporaire pour JSON
    @Transient
    private BigDecimal tarifMontant;

    // Getters & Setters
    public Integer getIdPubliciteDiffusion() { return idPubliciteDiffusion; }
    public void setIdPubliciteDiffusion(Integer idPubliciteDiffusion) { this.idPubliciteDiffusion = idPubliciteDiffusion; }

    public Publicite getPublicite() { return publicite; }
    public void setPublicite(Publicite publicite) { this.publicite = publicite; }

    public Voyage getVoyage() { return voyage; }
    public void setVoyage(Voyage voyage) { this.voyage = voyage; }

    public LocalDateTime getDateDiffusion() { return dateDiffusion; }
    public void setDateDiffusion(LocalDateTime dateDiffusion) { this.dateDiffusion = dateDiffusion; }

    public BigDecimal getTarifMontant() { return tarifMontant; }
    public void setTarifMontant(BigDecimal tarifMontant) { this.tarifMontant = tarifMontant; }
}


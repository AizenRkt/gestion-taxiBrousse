package com.example.gestion.model.publicite;
import jakarta.persistence.*;
import com.example.gestion.model.societe.Societe;
@Entity
@Table(name = "publicite")
public class Publicite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicite")
    private Integer idPublicite;

    @ManyToOne
    @JoinColumn(name = "id_societe")
    private Societe societe;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "duree", nullable = false)
    private Integer duree;

    // Getters & Setters
    public Integer getIdPublicite() {
        return idPublicite;
    }

    public void setIdPublicite(Integer idPublicite) {
        this.idPublicite = idPublicite;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }
}

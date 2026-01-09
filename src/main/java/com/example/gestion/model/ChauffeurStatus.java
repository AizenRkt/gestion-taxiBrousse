package com.example.gestion.model;

import com.example.gestion.enums.ChauffeurStatusEnum;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chauffeur_status")
public class ChauffeurStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chauffeur_status")
    private Long idChauffeurStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_chauffeur", nullable = false)
    private Chauffeur chauffeur;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChauffeurStatusEnum status;

    @Column(name = "date_status", nullable = false)
    private LocalDateTime dateStatus;

    public Long getIdChauffeurStatus() {
        return idChauffeurStatus;
    }

    public void setIdChauffeurStatus(Long idChauffeurStatus) {
        this.idChauffeurStatus = idChauffeurStatus;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public ChauffeurStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ChauffeurStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(LocalDateTime dateStatus) {
        this.dateStatus = dateStatus;
    }
}

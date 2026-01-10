package com.example.gestion.model.vehicule;

import com.example.gestion.enums.vehicule.VehiculeStatusEnum;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicule_status")
public class VehiculeStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicule_status")
    private Long idVehiculeStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_vehicule", nullable = false)
    private Vehicule vehicule;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehiculeStatusEnum status;

    @Column(name = "date_status", nullable = false)
    private LocalDateTime dateStatus;

    /* =======================
       GETTERS & SETTERS
       ======================= */

    public Long getIdVehiculeStatus() {
        return idVehiculeStatus;
    }

    public void setIdVehiculeStatus(Long idVehiculeStatus) {
        this.idVehiculeStatus = idVehiculeStatus;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public VehiculeStatusEnum getStatus() {
        return status;
    }

    public void setStatus(VehiculeStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(LocalDateTime dateStatus) {
        this.dateStatus = dateStatus;
    }
}

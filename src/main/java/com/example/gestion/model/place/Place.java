package com.example.gestion.model.place;

import jakarta.persistence.*;

@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place")
    private Long idPlace;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    // Getters & Setters
    public Long getIdPlace() { return idPlace; }
    public void setIdPlace(Long idPlace) { this.idPlace = idPlace; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}

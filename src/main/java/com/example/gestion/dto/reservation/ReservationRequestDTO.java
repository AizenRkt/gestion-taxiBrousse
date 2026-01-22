package com.example.gestion.dto.reservation;

import java.util.List;

public class ReservationRequestDTO {

    private Long idVoyage;
    private String clientNom;
    private String clientTel;
    private List<ReservationDetailDTO> details;

    // Getters & Setters
    public Long getIdVoyage() { return idVoyage; }
    public void setIdVoyage(Long idVoyage) { this.idVoyage = idVoyage; }

    public String getClientNom() { return clientNom; }
    public void setClientNom(String clientNom) { this.clientNom = clientNom; }

    public String getClientTel() { return clientTel; }
    public void setClientTel(String clientTel) { this.clientTel = clientTel; }

    public List<ReservationDetailDTO> getDetails() { return details; }
    public void setDetails(List<ReservationDetailDTO> details) { this.details = details; }

    // DTO interne pour le détail
    public static class ReservationDetailDTO {
        private Long idPlaceType;
        private Long idPassagerType;
        private int nombrePlaces;

        public Long getIdPlaceType() { return idPlaceType; }
        public void setIdPlaceType(Long idPlaceType) { this.idPlaceType = idPlaceType; }

        public Long getIdPassagerType() { return idPassagerType; }
        public void setIdPassagerType(Long idPassagerType) { this.idPassagerType = idPassagerType; }

        public int getNombrePlaces() { return nombrePlaces; }
        public void setNombrePlaces(int nombrePlaces) { this.nombrePlaces = nombrePlaces; }
    }
}

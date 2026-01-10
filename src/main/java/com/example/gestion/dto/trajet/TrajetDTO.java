package com.example.gestion.dto;

import java.time.LocalDateTime;

public class TrajetDTO {

    private String depart;
    private String arrivee;
    private Long idTrajet;
    private LocalDateTime dateDepart;

    public LocalDateTime getDateDepart() { 
        return dateDepart; 
    }

    public void setDateDepart(LocalDateTime dateDepart) { 
        this.dateDepart = dateDepart; 
    }

    public Long getIdTrajet() { 
        return idTrajet; 
    }

    public void setIdTrajet(Long idTrajet) { 
        this.idTrajet = idTrajet; 
    }

    public String getDepart() { 
        return depart; 
    }

    public void setDepart(String depart) { 
        this.depart = depart; 
    }

    public String getArrivee() { 
        return arrivee; 
    }

    public void setArrivee(String arrivee) { 
        this.arrivee = arrivee; 
    }
}

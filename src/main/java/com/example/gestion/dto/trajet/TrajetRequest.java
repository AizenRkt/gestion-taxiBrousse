package com.example.gestion.dto.trajet;

import java.util.List;

public class TrajetRequest {

    private String codeTrajet;
    private String description;
    private List<Long> arretsOrdre; 

    public String getCodeTrajet() { return codeTrajet; }
    public void setCodeTrajet(String codeTrajet) { this.codeTrajet = codeTrajet; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Long> getArretsOrdre() { return arretsOrdre; }
    public void setArretsOrdre(List<Long> arretsOrdre) { this.arretsOrdre = arretsOrdre; }
}

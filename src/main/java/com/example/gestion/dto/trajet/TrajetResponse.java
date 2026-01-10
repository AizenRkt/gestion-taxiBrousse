package com.example.gestion.dto.trajet;

import java.util.List;

public class TrajetResponse {
    private Long id;
    private String codeTrajet;
    private String description;
    private List<String> arrets;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodeTrajet() { return codeTrajet; }
    public void setCodeTrajet(String codeTrajet) { this.codeTrajet = codeTrajet; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getArrets() { return arrets; }
    public void setArrets(List<String> arrets) { this.arrets = arrets; }
}

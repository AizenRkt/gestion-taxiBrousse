package com.example.gestion.service.chauffeur;

import com.example.gestion.model.Chauffeur;
import com.example.gestion.repository.chauffeur.ChauffeurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChauffeurService {

    private final ChauffeurRepository chauffeurRepository;

    public ChauffeurService(ChauffeurRepository chauffeurRepository) {
        this.chauffeurRepository = chauffeurRepository;
    }

    public List<Chauffeur> getAllChauffeurs() {
        return chauffeurRepository.findAll();
    }

    public Optional<Chauffeur> getChauffeurById(Long id) {
        return chauffeurRepository.findById(id);
    }

    public Chauffeur createChauffeur(Chauffeur chauffeur) {
        return chauffeurRepository.save(chauffeur);
    }

    public Chauffeur updateChauffeur(Long id, Chauffeur chauffeurDetails) {
        return chauffeurRepository.findById(id).map(chauffeur -> {
            chauffeur.setNom(chauffeurDetails.getNom());
            chauffeur.setPrenom(chauffeurDetails.getPrenom());
            chauffeur.setDateDeNaissance(chauffeurDetails.getDateDeNaissance());
            return chauffeurRepository.save(chauffeur);
        }).orElseThrow(() -> new RuntimeException("Chauffeur non trouvé avec l'id " + id));
    }

    public void deleteChauffeur(Long id) {
        chauffeurRepository.deleteById(id);
    }
}

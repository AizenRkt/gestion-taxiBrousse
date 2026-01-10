package com.example.gestion.service.chauffeur;

import com.example.gestion.model.chauffeur.Chauffeur;
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

    public List<Chauffeur> findAll() {
        return chauffeurRepository.findAll();
    }

    public Optional<Chauffeur> findById(Long id) {
        return chauffeurRepository.findById(id);
    }

    public Chauffeur save(Chauffeur chauffeur) {
        return chauffeurRepository.save(chauffeur);
    }

    public void deleteById(Long id) {
        chauffeurRepository.deleteById(id);
    }
}
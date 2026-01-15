package com.example.gestion.service.chauffeur;

import com.example.gestion.model.chauffeur.Chauffeur;
import com.example.gestion.model.chauffeur.ChauffeurStatus;
import com.example.gestion.enums.chauffeur.ChauffeurStatusEnum;
import com.example.gestion.repository.chauffeur.ChauffeurRepository;
import com.example.gestion.repository.chauffeur.ChauffeurStatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChauffeurService {

    private final ChauffeurRepository chauffeurRepository;
    private final ChauffeurStatusRepository statusRepository;

    public ChauffeurService(ChauffeurRepository chauffeurRepository, ChauffeurStatusRepository statusRepository) {
        this.chauffeurRepository = chauffeurRepository;
        this.statusRepository = statusRepository;
    }

    public List<Chauffeur> findAll() {
        return chauffeurRepository.findAll();
    }

    public Chauffeur findById(Long id) {
        return chauffeurRepository.findById(id).orElse(null);
    }

    public Chauffeur create(Chauffeur chauffeur) {
        return chauffeurRepository.save(chauffeur);
    }

    public Chauffeur update(Long id, Chauffeur updated) {
        return chauffeurRepository.findById(id).map(c -> {
            c.setNom(updated.getNom());
            c.setPrenom(updated.getPrenom());
            c.setDateDeNaissance(updated.getDateDeNaissance());
            return chauffeurRepository.save(c);
        }).orElse(null);
    }

    public void delete(Long id) {
        chauffeurRepository.deleteById(id);
    }

    public ChauffeurStatus updateStatus(Long chauffeurId, ChauffeurStatusEnum status) {
        Chauffeur chauffeur = chauffeurRepository.findById(chauffeurId).orElseThrow();
        ChauffeurStatus s = new ChauffeurStatus();
        s.setChauffeur(chauffeur);
        s.setStatus(status);
        s.setDateStatus(LocalDateTime.now());
        return statusRepository.save(s);
    }
}

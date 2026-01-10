package com.example.gestion.service.chauffeur;

import com.example.gestion.model.chauffeur.ChauffeurStatus;
import com.example.gestion.repository.chauffeur.ChauffeurStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChauffeurStatusService {

    private final ChauffeurStatusRepository chauffeurStatusRepository;

    public ChauffeurStatusService(ChauffeurStatusRepository chauffeurStatusRepository) {
        this.chauffeurStatusRepository = chauffeurStatusRepository;
    }

    public List<ChauffeurStatus> findAll() {
        return chauffeurStatusRepository.findAll();
    }

    public Optional<ChauffeurStatus> findById(Long id) {
        return chauffeurStatusRepository.findById(id);
    }

    public ChauffeurStatus save(ChauffeurStatus chauffeurStatus) {
        return chauffeurStatusRepository.save(chauffeurStatus);
    }

    public void deleteById(Long id) {
        chauffeurStatusRepository.deleteById(id);
    }
}
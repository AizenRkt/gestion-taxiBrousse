package com.example.gestion.service.voyage;

import com.example.gestion.model.voyage.Voyage;
import com.example.gestion.repository.voyage.VoyageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageService {

    private final VoyageRepository voyageRepository;

    public VoyageService(VoyageRepository voyageRepository) {
        this.voyageRepository = voyageRepository;
    }

    public List<Voyage> findAll() {
        return voyageRepository.findAll();
    }

    public Optional<Voyage> findById(Long id) {
        return voyageRepository.findById(id);
    }

    public Voyage save(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    public void deleteById(Long id) {
        voyageRepository.deleteById(id);
    }
}
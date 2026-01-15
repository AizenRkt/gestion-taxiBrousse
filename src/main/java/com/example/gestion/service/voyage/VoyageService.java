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

    // Récupérer tous les voyages
    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    // Récupérer un voyage par son id
    public Optional<Voyage> getVoyageById(Long id) {
        return voyageRepository.findById(id);
    }

    // Créer un voyage
    public Voyage createVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    // Mettre à jour un voyage
    public Voyage updateVoyage(Long id, Voyage voyageDetails) {
        Voyage voyage = voyageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voyage non trouvé avec id: " + id));

        voyage.setTrajet(voyageDetails.getTrajet());
        voyage.setVehicule(voyageDetails.getVehicule());
        voyage.setChauffeur(voyageDetails.getChauffeur());
        voyage.setDateDepart(voyageDetails.getDateDepart());
        voyage.setDateArrivee(voyageDetails.getDateArrivee());

        return voyageRepository.save(voyage);
    }

    // Supprimer un voyage
    public void deleteVoyage(Long id) {
        Voyage voyage = voyageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voyage non trouvé avec id: " + id));
        voyageRepository.delete(voyage);
    }
}

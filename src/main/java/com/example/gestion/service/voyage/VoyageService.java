package com.example.gestion.service.voyage;

import com.example.gestion.model.Voyage;
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

    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    public Optional<Voyage> getVoyageById(Long id) {
        return voyageRepository.findById(id);
    }

    public Voyage createVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    // public Voyage updateVoyage(Long id, Voyage voyageDetails) {
    //     Voyage voyage = voyageRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Voyage introuvable"));

    //     voyage.setIdTrajet(voyageDetails.getIdTrajet());
    //     voyage.setIdVehicule(voyageDetails.getIdVehicule());
    //     voyage.setIdChauffeur(voyageDetails.getIdChauffeur());
    //     voyage.setDateDepart(voyageDetails.getDateDepart());
    //     voyage.setDateArrivee(voyageDetails.getDateArrivee());

    //     return voyageRepository.save(voyage);
    // }

    public void deleteVoyage(Long id) {
        voyageRepository.deleteById(id);
    }

    // public List<Voyage> getVoyagesPossibles(Datetime dateDepart, int idArretArrivee, int idArretDepart) {
    //     // Implémentation de la logique pour récupérer les voyages possibles
    //     // en fonction de la date de départ et de l'ID du trajet
    //     return voyageRepository.findAll().stream()
    //             .filter(voyage -> voyage.getDateDepart().toLocalDate().equals(dateDepart.toLocalDate())
    //                     && voyage.getIdTrajet().equals(idTrajet))
    //             .toList();
        
    // }


}

package com.example.gestion.service.voyage;
import com.example.gestion.dto.VoyageDTO;
import java.util.stream.Collectors;
import com.example.gestion.dto.TrajetDTO;
import com.example.gestion.model.TrajetArret;
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

    // public List<Voyage> getAllVoyages() {
    //     return voyageRepository.findAll();
    // }

public List<VoyageDTO> getVoyagesDisponiblesParTrajet(TrajetDTO trajetDTO) {

    return voyageRepository.findAll().stream()

        // 1️⃣ Filtre trajet
        .filter(voyage -> {

            if (trajetDTO.getIdTrajet() != null) {
                return voyage.getTrajet().getIdTrajet().equals(trajetDTO.getIdTrajet());
            }

            String depart = voyage.getTrajet().getArrets().stream()
                    .filter(ta -> ta.getOrdre() == 1)
                    .findFirst()
                    .map(ta -> ta.getArret().getNom())
                    .orElse(null);

            String arrivee = voyage.getTrajet().getArrets().stream()
                    .filter(ta -> ta.getOrdre() == 2)
                    .findFirst()
                    .map(ta -> ta.getArret().getNom())
                    .orElse(null);

            if (depart == null || arrivee == null) return false;

            if (!depart.equalsIgnoreCase(trajetDTO.getDepart())) return false;
            if (!arrivee.equalsIgnoreCase(trajetDTO.getArrivee())) return false;

            return true;
        })

        // 2️⃣ Filtre date + heure (>= date choisie)
        .filter(voyage -> {
            if (trajetDTO.getDateDepart() == null) return true;

            return !voyage.getDateDepart()
                    .isBefore(trajetDTO.getDateDepart());
        })

        // 3️⃣ Mapping DTO
        .map(voyage -> {

            String depart = voyage.getTrajet().getArrets().stream()
                    .filter(ta -> ta.getOrdre() == 1)
                    .findFirst()
                    .map(ta -> ta.getArret().getNom())
                    .orElse("N/A");

            String arrivee = voyage.getTrajet().getArrets().stream()
                    .filter(ta -> ta.getOrdre() == 2)
                    .findFirst()
                    .map(ta -> ta.getArret().getNom())
                    .orElse("N/A");

            String vehicule = voyage.getVehicule().getImmatriculation() + " (" +
                              voyage.getVehicule().getMarque() + ")";

            VoyageDTO dto = new VoyageDTO();
            dto.setIdVoyage(voyage.getIdVoyage());
            dto.setDepart(depart);
            dto.setArrivee(arrivee);
            dto.setDateDepart(voyage.getDateDepart());
            dto.setDateArrivee(voyage.getDateArrivee());
            dto.setVehicule(vehicule);
            dto.setPrix(0.0);

            return dto;
        })

        .collect(Collectors.toList());
}
   public List<VoyageDTO> getAllVoyages() {
        return voyageRepository.findAll().stream().map(voyage -> {

            String depart = voyage.getTrajet().getArrets().stream()
                                  .filter(ta -> ta.getOrdre() == 1)
                                  .findFirst()
                                  .map(ta -> ta.getArret().getNom())
                                  .orElse("N/A");

            String arrivee = voyage.getTrajet().getArrets().stream()
                                   .filter(ta -> ta.getOrdre() == 2)
                                   .findFirst()
                                   .map(ta -> ta.getArret().getNom())
                                   .orElse("N/A");

            String vehicule = voyage.getVehicule().getImmatriculation() + " (" +
                              voyage.getVehicule().getMarque() + ")";

            VoyageDTO dto = new VoyageDTO();
            dto.setIdVoyage(voyage.getIdVoyage());
            dto.setDepart(depart);
            dto.setArrivee(arrivee);
            dto.setDateDepart(voyage.getDateDepart());
            dto.setDateArrivee(voyage.getDateArrivee());
            dto.setVehicule(vehicule);
            dto.setPrix(0.0);

            return dto;
        }).collect(Collectors.toList());
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

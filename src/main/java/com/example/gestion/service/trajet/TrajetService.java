package com.example.gestion.service.trajet;
import java.util.stream.Collectors;
import com.example.gestion.dto.TrajetDTO;
import com.example.gestion.model.Trajet;
import com.example.gestion.repository.trajet.TrajetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajetService {

    private final TrajetRepository trajetRepository;

    public TrajetService(TrajetRepository trajetRepository) {
        this.trajetRepository = trajetRepository;
    }

    // Récupérer tous les trajets
    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }

     public List<TrajetDTO> getDepartArrivee() {
        return trajetRepository.findAll().stream().map(trajet -> {
            String depart = trajet.getArrets().stream()
                                  .filter(ta -> ta.getOrdre() == 1)
                                  .findFirst()
                                  .map(ta -> ta.getArret().getNom())
                                  .orElse("N/A");

            String arrivee = trajet.getArrets().stream()
                                   .filter(ta -> ta.getOrdre() == 2)
                                   .findFirst()
                                   .map(ta -> ta.getArret().getNom())
                                   .orElse("N/A");
            TrajetDTO dto = new TrajetDTO();
            dto.setIdTrajet(trajet.getIdTrajet());
            dto.setDepart(depart);
            dto.setArrivee(arrivee);    
            return dto;
        }).collect(Collectors.toList());
    }

    // Récupérer un trajet par id
    public Optional<Trajet> getTrajetById(Long id) {
        return trajetRepository.findById(id);
    }

    // Créer un trajet
    public Trajet createTrajet(Trajet trajet) {
        return trajetRepository.save(trajet);
    }

    // Mettre à jour un trajet
    public Trajet updateTrajet(Long id, Trajet trajetDetails) {
        Trajet trajet = trajetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé avec id " + id));

        trajet.setCodeTrajet(trajetDetails.getCodeTrajet());
        trajet.setDescription(trajetDetails.getDescription());
        // Si tu veux mettre à jour les arrets, il faudra gérer la liste séparément

        return trajetRepository.save(trajet);
    }

    // Supprimer un trajet
    public void deleteTrajet(Long id) {
        trajetRepository.deleteById(id);
    }
}

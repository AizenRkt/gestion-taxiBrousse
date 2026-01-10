package com.example.gestion.service.trajet;

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

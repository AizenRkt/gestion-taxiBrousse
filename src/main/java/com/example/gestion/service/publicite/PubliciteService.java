package com.example.gestion.service.publicite;

import com.example.gestion.model.publicite.DiffusionPubliciteVoyage;
import com.example.gestion.model.societe.Societe;
import com.example.gestion.model.tarif.TarifPublicite;
import com.example.gestion.repository.publicite.PubliciteRepository;
import com.example.gestion.repository.publicite.TarifPubliciteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PubliciteService {

    private final TarifPubliciteRepository tarifPubliciteRepository;
    private final PubliciteRepository diffusionRepository;

    public PubliciteService(TarifPubliciteRepository tarifPubliciteRepository,
                        PubliciteRepository diffusionRepository) {
        this.tarifPubliciteRepository = tarifPubliciteRepository;
        this.diffusionRepository = diffusionRepository;
    }

    /**
     * Calcule le chiffre d'affaire total généré par une liste de diffusions de publicités
     */
    public BigDecimal getCAPub(List<DiffusionPubliciteVoyage> diffusions) {
        BigDecimal total = BigDecimal.ZERO;

        for (DiffusionPubliciteVoyage d : diffusions) {
            TarifPublicite tarif = tarifPubliciteRepository
                    .findTarifApplicable(d.getDateDiffusion().toLocalDate());

            if (tarif != null) {
                total = total.add(tarif.getMontant());
            }
        }

        return total;
    }

    /**
     * Récupère les diffusions filtrées par société et plage de dates
     */
    public List<DiffusionPubliciteVoyage> getDiffusions(Societe societe, LocalDateTime start, LocalDateTime end) {
        List<DiffusionPubliciteVoyage> all = diffusionRepository.findAllDiffusions();

        return all.stream()
                .filter(d -> societe == null || d.getPublicite().getSociete().equals(societe))
                .filter(d -> start == null || (d.getDateDiffusion().compareTo(start) >= 0
                        && d.getDateDiffusion().compareTo(end) < 0))
                .toList();
    }
}

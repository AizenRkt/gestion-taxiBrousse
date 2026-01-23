package com.example.gestion.service.publicite;

import com.example.gestion.model.publicite.DiffusionPubliciteVoyage;
import com.example.gestion.model.societe.Societe;
import com.example.gestion.model.tarif.TarifPublicite;
import com.example.gestion.repository.publicite.PubliciteRepository;
import com.example.gestion.repository.publicite.TarifPubliciteRepository;
import org.springframework.stereotype.Service;
import com.example.gestion.model.voyage.Voyage;
import com.example.gestion.repository.publicite.PaiementPubliciteRepository;
import com.example.gestion.model.publicite.PaiementPublicite;
import com.example.gestion.model.publicite.Publicite;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
@Service
public class PubliciteService {

    private final TarifPubliciteRepository tarifPubliciteRepository;
    private final PubliciteRepository diffusionRepository;
    private final PaiementPubliciteRepository paiementPubliciteRepository;

    public PubliciteService(TarifPubliciteRepository tarifPubliciteRepository,
                        PubliciteRepository diffusionRepository, PaiementPubliciteRepository paiementPubliciteRepository) {
        this.tarifPubliciteRepository = tarifPubliciteRepository;
        this.diffusionRepository = diffusionRepository;
        this.paiementPubliciteRepository = paiementPubliciteRepository;
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

    public BigDecimal getPaiementMensuel(Societe societe, int annee, int mois) {

        LocalDate debut = LocalDate.of(annee, mois, 1);
        LocalDate fin = debut.plusMonths(1);

        return paiementPubliciteRepository.findAll().stream()
                .filter(p -> p.getSociete().equals(societe))
                .filter(p -> !p.getDatePaiement().isBefore(debut)
                        && p.getDatePaiement().isBefore(fin))
                .map(PaiementPublicite::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getResteAPayerMensuel(Societe societe, int annee, int mois) {

        LocalDate debut = LocalDate.of(annee, mois, 1);
        LocalDate fin = debut.plusMonths(1);

        LocalDateTime start = debut.atStartOfDay();
        LocalDateTime end = fin.atStartOfDay();

        List<DiffusionPubliciteVoyage> diffusions =
                getDiffusions(societe, start, end);

        BigDecimal caMensuel = getCAPub(diffusions);

        BigDecimal totalPaiements = getPaiementMensuel(societe, annee, mois);

        return caMensuel.subtract(totalPaiements);
    }


//   public List<DiffusionPubliciteVoyage> getAllDiffusionsByVoyage(int annee, int mois, Integer idVoyage) {
//     List<DiffusionPubliciteVoyage> all = diffusionRepository.findAllDiffusions();

//     return all.stream()
//         // Filtre par voyage si idVoyage fourni
//         .filter(d -> idVoyage == null || (d.getVoyage() != null && d.getVoyage().getIdVoyage().equals(idVoyage)))
        
//         // Filtre par année et mois
//         .filter(d -> {
//             LocalDateTime diffDate = d.getDateDiffusion();
//             return diffDate.getYear() == annee && diffDate.getMonthValue() == mois;
//         })
//         .toList();
// }

     
    public List<DiffusionPubliciteVoyage> getDiffusionsByVoyage(Voyage voyage, LocalDateTime start, LocalDateTime end) {
        List<DiffusionPubliciteVoyage> all = diffusionRepository.findAllDiffusions();

        return all.stream()
                .filter(d -> d.getVoyage().equals(voyage))
                .filter(d -> start == null || (d.getDateDiffusion().compareTo(start) >= 0
                        && d.getDateDiffusion().compareTo(end) < 0))
                .toList();
    }

   public List<Societe> getSocietesByDiffusionVoyage(List<DiffusionPubliciteVoyage> diffusions) {
    if (diffusions == null || diffusions.isEmpty()) {
        return List.of(); // retourne une liste vide si aucune diffusion
    }

    // Utilisation d'un Set pour éviter les doublons
    Set<Societe> societes = diffusions.stream()
            .map(DiffusionPubliciteVoyage::getPublicite) // récupérer la publicité
            .filter(Objects::nonNull)
            .map(Publicite::getSociete) // récupérer la société de chaque pub
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

    // Retourner en List
    return new ArrayList<>(societes);
}

            public BigDecimal MontantPayeSocietes(List<DiffusionPubliciteVoyage> diffusions) {
        // Récupérer toutes les sociétés uniques ayant diffusé des pubs
        List<Societe> societes = getSocietesByDiffusionVoyage(diffusions);

        // Calculer la somme des paiements pour chaque société
        BigDecimal total = societes.stream()
                .map(s -> getPaiementMensuel(s, diffusions.get(0).getDateDiffusion().getYear(), diffusions.get(0).getDateDiffusion().getMonthValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total;
    }

}


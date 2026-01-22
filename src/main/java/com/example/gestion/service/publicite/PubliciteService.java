package com.example.gestion.service.publicite;

import com.example.gestion.model.publicite.DiffusionPubliciteVoyage;
import com.example.gestion.model.societe.Societe;
import com.example.gestion.model.tarif.TarifPublicite;
import com.example.gestion.repository.publicite.PubliciteRepository;
import com.example.gestion.repository.publicite.TarifPubliciteRepository;
import org.springframework.stereotype.Service;

import com.example.gestion.repository.publicite.PaiementPubliciteRepository;
import com.example.gestion.model.publicite.PaiementPublicite;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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


}

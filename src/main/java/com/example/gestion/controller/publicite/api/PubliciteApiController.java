package com.example.gestion.controller.publicite.api;

import com.example.gestion.model.publicite.DiffusionPubliciteVoyage;
import com.example.gestion.model.societe.Societe;
import com.example.gestion.model.tarif.TarifPublicite;
import com.example.gestion.repository.publicite.PubliciteRepository;
import com.example.gestion.repository.publicite.TarifPubliciteRepository;
import com.example.gestion.repository.societe.SocieteRepository;
import com.example.gestion.service.publicite.PubliciteService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/publicites")
public class PubliciteApiController {

    private final PubliciteRepository publiciteRepository;
    private final SocieteRepository societeRepository;
    private final PubliciteService publiciteService;
    private final TarifPubliciteRepository tarifPubliciteRepository;

    public PubliciteApiController(PubliciteRepository publiciteRepository,
                                  SocieteRepository societeRepository,
                                  PubliciteService publiciteService,
                                  TarifPubliciteRepository tarifPubliciteRepository) {
        this.publiciteRepository = publiciteRepository;
        this.societeRepository = societeRepository;
        this.publiciteService = publiciteService;
        this.tarifPubliciteRepository = tarifPubliciteRepository;
    }

    /**
     * Liste brute des diffusions avec tarif
     */
    @GetMapping("/allDiffusions")
    public List<DiffusionPubliciteVoyage> getAllDiffusions() {

        List<DiffusionPubliciteVoyage> diffusions = publiciteRepository.findAllDiffusions();

        // Calcul du tarif pour chaque diffusion
        diffusions.forEach(d -> {
            TarifPublicite tarif = tarifPubliciteRepository.findTarifApplicable(d.getDateDiffusion().toLocalDate());
            if (tarif != null) {
                d.setTarifMontant(tarif.getMontant());
            }
        });

        return diffusions;
    }

    /**
     * Liste des diffusions filtrées par société et date, avec tarif
     */
    @GetMapping("/diffusions")
    public List<DiffusionPubliciteVoyage> getDiffusions(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Integer idSociete) {

        LocalDateTime start = null;
        LocalDateTime end = null;

        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date);
            start = localDate.atStartOfDay();
            end = localDate.plusDays(1).atStartOfDay(); // plage pour la journée entière
        }

        Societe societe = null;
        if (idSociete != null) {
            societe = societeRepository.findById(idSociete).orElse(null);
        }

        List<DiffusionPubliciteVoyage> diffusions =
                publiciteService.getDiffusions(societe, start, end);

        // Calcul du tarif pour chaque diffusion filtrée
        diffusions.forEach(d -> {
            TarifPublicite tarif = tarifPubliciteRepository.findTarifApplicable(d.getDateDiffusion().toLocalDate());
            if (tarif != null) {
                d.setTarifMontant(tarif.getMontant());
            }
        });

        return diffusions;
    }

    /**
     * Chiffre d'affaires des publicités filtré
     */
    @GetMapping("/ca")
    public BigDecimal getCAPublicite(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Integer idSociete) {

        LocalDateTime start = null;
        LocalDateTime end = null;

        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date);
            start = localDate.atStartOfDay();
            end = localDate.plusDays(1).atStartOfDay();
        }

        Societe societe = null;
        if (idSociete != null) {
            societe = societeRepository.findById(idSociete).orElse(null);
        }

        List<DiffusionPubliciteVoyage> diffusions =
                publiciteService.getDiffusions(societe, start, end);

        return publiciteService.getCAPub(diffusions);
    }

    @GetMapping("/reste")
    public BigDecimal getResteAPayer(
            @RequestParam int annee,
            @RequestParam int mois,
            @RequestParam int idSociete) {

        Societe societe = societeRepository
                .findById(idSociete)
                .orElseThrow(() -> new RuntimeException("Société introuvable"));

        return publiciteService.getResteAPayerMensuel(societe, annee, mois);
    }

}

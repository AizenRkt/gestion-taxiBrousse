package com.example.gestion.controller.publicite.api;

import com.example.gestion.model.publicite.DiffusionPubliciteVoyage;
import com.example.gestion.model.societe.Societe;
import com.example.gestion.model.tarif.TarifPublicite;
import com.example.gestion.repository.publicite.PubliciteRepository;
import com.example.gestion.repository.publicite.TarifPubliciteRepository;
import com.example.gestion.repository.societe.SocieteRepository;
import com.example.gestion.service.publicite.PubliciteService;
import org.springframework.web.bind.annotation.*;
import com.example.gestion.model.voyage.Voyage;
import com.example.gestion.repository.voyage.VoyageRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/publicites")
public class PubliciteApiController {

    private final PubliciteRepository publiciteRepository;
    private final SocieteRepository societeRepository;
    private final VoyageRepository voyageRepository;
    private final PubliciteService publiciteService;
    private final TarifPubliciteRepository tarifPubliciteRepository;

    public PubliciteApiController(PubliciteRepository publiciteRepository,
                                  SocieteRepository societeRepository,
                                  VoyageRepository voyageRepository,
                                  PubliciteService publiciteService,
                                  TarifPubliciteRepository tarifPubliciteRepository) {
        this.publiciteRepository = publiciteRepository;
        this.societeRepository = societeRepository;
        this.voyageRepository = voyageRepository;
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

     @GetMapping("/caVoyage")
    public BigDecimal getCAPubliciteByIdVoyage(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Integer idVoyage) {

        LocalDateTime start = null;
        LocalDateTime end = null;

        if (date != null && !date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date);
            start = localDate.atStartOfDay();
            end = localDate.plusDays(30).atStartOfDay();
        }

        Voyage voyage = null;
        if (idVoyage != null) {
            voyage = voyageRepository.findById(idVoyage.longValue()).orElse(null);
        }

        List<DiffusionPubliciteVoyage> diffusions =
                publiciteService.getDiffusionsByVoyage(voyage, start, end);
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

            /**
         * Diffusions par voyage et par mois (avec tarif)
         * Exemple:
         *   /api/publicites/diffusionsByVoyage?date=2026-01-01&idVoyage=1
         */
        @GetMapping("/diffusionsByVoyage")
public List<DiffusionPubliciteVoyage> getDiffusionsByVoyage(
        @RequestParam(required = false) String date,
        @RequestParam(required = false) Integer idVoyage) {

    LocalDateTime start = null;
    LocalDateTime end = null;

    // Si une date est fournie, on crée la plage de la journée entière
    if (date != null && !date.isEmpty()) {

        LocalDate localDate = LocalDate.parse(date); // format attendu : yyyy-MM-dd
        start = localDate.atStartOfDay();
        end = localDate.plusDays(30).atStartOfDay();
        System.out.println("Start: " + start + ", End: " + end);
    }

    Voyage voyage = null;
    System.out.println("idVoyage: " + idVoyage);
    if (idVoyage != null) {
        // Recherche du voyage dans la base
        voyage = voyageRepository.findById(idVoyage.longValue()).orElse(null);
    }

    // Appel du service pour récupérer les diffusions filtrées par voyage et date
    List<DiffusionPubliciteVoyage> diffusions =
            publiciteService.getDiffusionsByVoyage(voyage, start, end);

    // Calcul du tarif pour chaque diffusion filtrée
    diffusions.forEach(d -> {
        TarifPublicite tarif = tarifPubliciteRepository.findTarifApplicable(d.getDateDiffusion().toLocalDate());
        if (tarif != null) {
            d.setTarifMontant(tarif.getMontant());
        }
    });

    return diffusions;
}

@GetMapping("/montantSocietes")
public BigDecimal getMontantTotalSocietes(
        @RequestParam Integer idVoyage,
        @RequestParam String date // format attendu : yyyy-MM-dd ou yyyy-MM-ddTHH:mm:ss
) {
    LocalDateTime dateTime;

    // Essaye de parser le datetime complet (yyyy-MM-ddTHH:mm:ss)
    try {
        dateTime = LocalDateTime.parse(date);
    } catch (DateTimeParseException e) {
        // Si échec, parse seulement la date (yyyy-MM-dd)
        dateTime = LocalDate.parse(date).atStartOfDay();
    }

    // Début du mois
    LocalDateTime debut = dateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

    // Début du mois suivant (fin du mois courant)
    LocalDateTime fin = debut.plusMonths(1);

    Voyage voyage = voyageRepository.findById(idVoyage.longValue()).orElse(null);
    if (voyage == null) {
        return BigDecimal.ZERO;
    }

    // Récupère les diffusions pour ce voyage et cette période
    List<DiffusionPubliciteVoyage> diffusions = publiciteService.getDiffusionsByVoyage(voyage, debut, fin);

    // Calcule le total payé par les sociétés pour ce voyage
    BigDecimal total = publiciteService.MontantPayeSocietes(diffusions);

    return total;
}







}

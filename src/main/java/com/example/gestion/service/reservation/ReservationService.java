package com.example.gestion.service.reservation;

import com.example.gestion.repository.reservation.ReservationRepository;
import com.example.gestion.repository.voyage.VoyageRepository;
import com.example.gestion.repository.reservation.ReservationDetailRepository;
import com.example.gestion.repository.place.PlaceTypeRepository;
import com.example.gestion.repository.client.PassagerTypeRepository;
import com.example.gestion.repository.voyage.VoyageRepository;

import com.example.gestion.model.reservation.Reservation;
import com.example.gestion.model.reservation.ReservationDetail;
import com.example.gestion.model.place.PlaceType;
import com.example.gestion.model.client.PassagerType;
import com.example.gestion.dto.reservation.ReservationRequestDTO;

import com.example.gestion.service.tarif.TarificationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final ReservationDetailRepository detailRepo;
    private final PlaceTypeRepository placeRepo;
    private final PassagerTypeRepository passagerRepo;
    private final TarificationService tarificationService;
    private final VoyageRepository voyageRepo;

    public ReservationService(ReservationRepository reservationRepo, ReservationDetailRepository detailRepo,
                              PlaceTypeRepository placeRepo, PassagerTypeRepository passagerRepo,
                              TarificationService tarificationService, VoyageRepository voyageRepo) {
        this.reservationRepo = reservationRepo;
        this.detailRepo = detailRepo;
        this.placeRepo = placeRepo;
        this.passagerRepo = passagerRepo;
        this.tarificationService = tarificationService;
        this.voyageRepo = voyageRepo;
    }

    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public List<Reservation> findAllWithDetails() {
        return reservationRepo.findAllWithDetails();
    }


    public Optional<Reservation> findById(Long id) {
        return reservationRepo.findById(id);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    public void deleteById(Long id) {
        reservationRepo.deleteById(id);
    }

    // @Transactional
    // public Reservation creerReservation(ReservationRequestDTO dto) {

    //     Reservation res = new Reservation();
    //     res.setClientNom(dto.getClientNom());
    //     res.setClientTel(dto.getClientTel());

    //     // Liaison objet Voyage
    //     res.setVoyage(voyageRepo.findById(dto.getIdVoyage())
    //             .orElseThrow(() -> new RuntimeException("Voyage introuvable")));

    //     BigDecimal total = BigDecimal.ZERO;

    //     // Ne sauvegarde pas encore res ici !

    //     for (ReservationRequestDTO.ReservationDetailDTO detDto : dto.getDetails()) {
    //         PlaceType placeType = placeRepo.findById(detDto.getIdPlaceType())
    //                 .orElseThrow(() -> new RuntimeException("PlaceType introuvable"));
    //         PassagerType passagerType = passagerRepo.findById(detDto.getIdPassagerType())
    //                 .orElseThrow(() -> new RuntimeException("PassagerType introuvable"));

    //         BigDecimal prixUnitaire = tarificationService.calculerPrix(placeType, passagerType);
    //         BigDecimal prixDetail = prixUnitaire.multiply(BigDecimal.valueOf(detDto.getNombrePlaces()));
    //         total = total.add(prixDetail);

    //         ReservationDetail detail = new ReservationDetail();
    //         detail.setReservation(res);          
    //         detail.setPlaceType(placeType);      
    //         detail.setPassagerType(passagerType);
    //         detail.setNombrePlaces(detDto.getNombrePlaces());

    //         detailRepo.save(detail);
    //     }

    //     int totalPlaces = dto.getDetails().stream()
    //         .mapToInt(ReservationRequestDTO.ReservationDetailDTO::getNombrePlaces)
    //         .sum();

    //     res.setNombrePlaces(totalPlaces);
    //     res.setTotalPayer(total);  // <-- total est calculé
    //     return reservationRepo.save(res);  // sauvegarde finale après calcul
    // }

    @Transactional
    public Reservation creerReservation(ReservationRequestDTO dto) {

        // Création de l'objet réservation
        Reservation res = new Reservation();
        res.setClientNom(dto.getClientNom());
        res.setClientTel(dto.getClientTel());

        // Liaison avec le voyage
        res.setVoyage(voyageRepo.findById(dto.getIdVoyage())
                .orElseThrow(() -> new RuntimeException("Voyage introuvable")));

        // Initialisation du total à 0
        BigDecimal total = BigDecimal.ZERO;

        // Assure-toi que la liste de détails est initialisée
        if (res.getDetails() == null) {
            res.setDetails(new ArrayList<>());
        }

        // Boucle sur chaque détail pour calculer le prix et créer les objets
        for (ReservationRequestDTO.ReservationDetailDTO detDto : dto.getDetails()) {
            PlaceType placeType = placeRepo.findById(detDto.getIdPlaceType())
                    .orElseThrow(() -> new RuntimeException("PlaceType introuvable"));
            PassagerType passagerType = passagerRepo.findById(detDto.getIdPassagerType())
                    .orElseThrow(() -> new RuntimeException("PassagerType introuvable"));

            BigDecimal prixUnitaire = tarificationService.calculerPrix(placeType, passagerType);
            BigDecimal prixDetail = prixUnitaire.multiply(BigDecimal.valueOf(detDto.getNombrePlaces()));
            total = total.add(prixDetail);

            // Création du détail et liaison avec la réservation
            ReservationDetail detail = new ReservationDetail();
            detail.setReservation(res);          
            detail.setPlaceType(placeType);      
            detail.setPassagerType(passagerType);
            detail.setNombrePlaces(detDto.getNombrePlaces());
            detail.setPrixApplique(prixUnitaire); 

            // Ajout du détail à la réservation (cascade gérée)
            res.getDetails().add(detail);
        }

        // Calcul du nombre total de places
        int totalPlaces = dto.getDetails().stream()
                .mapToInt(ReservationRequestDTO.ReservationDetailDTO::getNombrePlaces)
                .sum();

        res.setNombrePlaces(totalPlaces);
        res.setTotalPayer(total);  // total calculé

        // Sauvegarde finale : Hibernate gère les détails grâce au cascade
        return reservationRepo.save(res);
    }
    
    public List<Reservation> findAllWithDetailsByVoyage(Integer idVoyage) {
        if (idVoyage == null) {
            // Si aucun voyage spécifié, retourne toutes les réservations
            return reservationRepo.findAllWithDetails();
        }

        Long idVoyageLong = idVoyage.longValue();

        // Sinon, on filtre par idVoyage
        return reservationRepo.findAllWithDetails()
                            .stream()
                            .filter(r -> r.getVoyage() != null 
                                    && r.getVoyage().getIdVoyage().equals(idVoyageLong))
                            .toList();
    }

        public BigDecimal getCAVoyage(List<Reservation> reservations) {
            BigDecimal caTotal = BigDecimal.ZERO;

            for (Reservation reservation : reservations) {
                if (reservation.getDetails() == null) continue;

                for (ReservationDetail detail : reservation.getDetails()) {
                    PlaceType placeType = detail.getPlaceType();
                    PassagerType passagerType = detail.getPassagerType();
                    Integer nombrePlaces = detail.getNombrePlaces();

                    if (placeType != null && passagerType != null && nombrePlaces != null) {
                        BigDecimal prixUnitaire = tarificationService.calculerPrix(placeType, passagerType);
                        BigDecimal montant = prixUnitaire.multiply(BigDecimal.valueOf(nombrePlaces));
                        caTotal = caTotal.add(montant);
                    }
                }
            }

            return caTotal;
        }


}

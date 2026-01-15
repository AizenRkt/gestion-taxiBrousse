package com.example.gestion.controller.reservation.api;

import com.example.gestion.model.reservation.Reservation;
import com.example.gestion.model.voyage.Voyage;
import com.example.gestion.service.reservation.ReservationService;
import com.example.gestion.service.voyage.VoyageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationApiController {

    private final ReservationService reservationService;
    private final VoyageService voyageService;

    public ReservationApiController(
            ReservationService reservationService,
            VoyageService voyageService
    ) {
        this.reservationService = reservationService;
        this.voyageService = voyageService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findAllWithDetails());
    }

    @PostMapping("/achat")
    public ResponseEntity<Reservation> achatBillet(@RequestBody Map<String, Object> body) {

        Long idVoyage = Long.valueOf(body.get("idVoyage").toString());
        String clientNom = body.get("clientNom").toString();
        String clientTelephone = body.get("clientTelephone").toString();
        Integer nombrePlaces = Integer.valueOf(body.get("nombrePlaces").toString());
        BigDecimal totalPayer = BigDecimal.valueOf(Double.valueOf(body.get("totalPayer").toString()));

        Voyage voyage = voyageService.getVoyageById(idVoyage)
                .orElseThrow(() -> new RuntimeException("Voyage introuvable"));

        Reservation reservation = new Reservation();
        reservation.setVoyage(voyage);
        reservation.setClientNom(clientNom);
        reservation.setClientTel(clientTelephone);
        reservation.setNombrePlaces(nombrePlaces);
        reservation.setTotalPayer(totalPayer);
        reservation.setDateReservation(LocalDateTime.now());

        Reservation saved = reservationService.save(reservation);

        return ResponseEntity.ok(saved);
    }
}

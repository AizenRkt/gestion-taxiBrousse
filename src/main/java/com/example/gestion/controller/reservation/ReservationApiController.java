package com.example.gestion.controller.reservation;

import com.example.gestion.model.Reservation;
import com.example.gestion.service.reservation.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

    private final ReservationService reservationService;

    public ReservationApiController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // GET /api/reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // GET /api/reservations/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/reservations
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // PUT /api/reservations/{id}
    @PutMapping("/{id}")
    public Reservation updateReservation(
            @PathVariable Long id,
            @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    // DELETE /api/reservations/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

   


    // public List<Reservation> getVoyagesPossibles(@PathVariable Long voyageId) {
    //     return reservationService.getReservationsByVoyageId();
    // }
    @GetMapping("/reserver/{voyageId}/{nbrplaces}/{totalapayer}")
    public Reservation reserverPlaceVoyage(@PathVariable Long voyageId,@PathVariable int nbrplaces ,@PathVariable double totalapayer) {
        Reservation reservation = new Reservation();
        reservation.setIdVoyage(voyageId);
        reservation.setNbPlaceReserve(nbrplaces);
        reservation.setTotalMontant(java.math.BigDecimal.valueOf(totalapayer));
        reservation.setDateReservation(java.time.LocalDateTime.now());
        return reservationService.createReservation(reservation);
    }
}


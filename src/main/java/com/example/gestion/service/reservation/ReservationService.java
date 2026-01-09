package com.example.gestion.service.reservation;

import com.example.gestion.model.Reservation;
import com.example.gestion.repository.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation non trouvée"));

        reservation.setIdVoyage(reservationDetails.getIdVoyage());
        reservation.setNbPlaceReserve(reservationDetails.getNbPlaceReserve());
        reservation.setDateReservation(reservationDetails.getDateReservation());
        reservation.setTotalMontant(reservationDetails.getTotalMontant());

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

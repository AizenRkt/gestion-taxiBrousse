package com.example.gestion.repository.reservation;

import com.example.gestion.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
package com.example.gestion.repository.reservation;

import com.example.gestion.model.reservation.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r JOIN FETCH r.voyage v JOIN FETCH v.trajet t JOIN FETCH v.vehicule veh")
    List<Reservation> findAllWithDetails();
}
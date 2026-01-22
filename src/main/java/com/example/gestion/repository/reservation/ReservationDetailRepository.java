package com.example.gestion.repository.reservation;

import com.example.gestion.model.reservation.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {
}

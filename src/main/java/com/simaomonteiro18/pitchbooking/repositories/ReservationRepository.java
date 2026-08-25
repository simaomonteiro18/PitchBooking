package com.simaomonteiro18.pitchbooking.repositories;

import com.simaomonteiro18.pitchbooking.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

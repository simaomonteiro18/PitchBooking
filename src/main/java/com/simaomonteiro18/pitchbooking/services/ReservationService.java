package com.simaomonteiro18.pitchbooking.services;

import com.simaomonteiro18.pitchbooking.entities.Pitch;
import com.simaomonteiro18.pitchbooking.entities.Reservation;
import com.simaomonteiro18.pitchbooking.entities.User;
import com.simaomonteiro18.pitchbooking.exceptions.InvalidTimeException;
import com.simaomonteiro18.pitchbooking.exceptions.ReservationConflictException;
import com.simaomonteiro18.pitchbooking.exceptions.ResourceNotFoundException;
import com.simaomonteiro18.pitchbooking.repositories.PitchRepository;
import com.simaomonteiro18.pitchbooking.repositories.ReservationRepository;
import com.simaomonteiro18.pitchbooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PitchRepository pitchRepository;

    public Reservation createReservation(Long userId, Long pitchId, Instant moment, LocalDateTime startTime, LocalDateTime endTime) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, userId));

        Pitch pitch = pitchRepository.findById(pitchId)
                .orElseThrow(() -> new ResourceNotFoundException(Pitch.class, pitchId));

        Duration duration = Duration.between(startTime, endTime);
        long minutes = duration.toMinutes();

        System.out.println("MINUTES = " + minutes);

        if (minutes < 60L) {
            throw new InvalidTimeException("Tempo inferior a 1 hora!");
        } else if (minutes % 60 != 0) {
            throw new InvalidTimeException("Tempo inválido!");
        }

        boolean exists = reservationRepository.existsOverlappingReservation(pitch, startTime, endTime);

        System.out.println("EXISTS = " + exists);

        if (exists) {
            throw new ReservationConflictException("Já existe uma reserva nesse horário.");
        }

        Reservation reservation = new Reservation(user, pitch, moment, startTime, endTime);

        return reservationRepository.save(reservation);

    }

    public List<Reservation> findByUser(User user) {
        return reservationRepository.findByUser(user);
    }

}

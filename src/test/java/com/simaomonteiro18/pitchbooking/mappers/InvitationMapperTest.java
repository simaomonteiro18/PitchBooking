package com.simaomonteiro18.pitchbooking.mappers;

import com.simaomonteiro18.pitchbooking.dtos.InvitationDTO;
import com.simaomonteiro18.pitchbooking.entities.Invitation;
import com.simaomonteiro18.pitchbooking.entities.Pitch;
import com.simaomonteiro18.pitchbooking.entities.Reservation;
import com.simaomonteiro18.pitchbooking.entities.User;
import com.simaomonteiro18.pitchbooking.entities.enums.PitchType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvitationMapperTest {

    User organizer = new User("Simão", "sm18@gmail.com", "912345678", "Sintra");
    User guest = new User("Mafalda", "mf@gmail.com", "987654321", "Lisboa");

    Pitch pitch = new Pitch("Sintrense", "Sintra", 20.0, PitchType.ELEVEN);

    Reservation reservation = new Reservation(organizer, pitch, Instant.now(), LocalDateTime.parse("2026-09-04T20:00:00"), LocalDateTime.parse("2026-09-04T21:00:00"));

    Invitation i1 = new Invitation(guest, reservation);

    @Test
    @DisplayName("Teste de Conversão para DTO")
    void convertsToDTOSuccessfully() {

        InvitationDTO invitationDTO = InvitationMapper.toDTO(i1);

        assertEquals(i1.getId(), invitationDTO.getId());
        assertEquals(i1.getGuest().getName(), invitationDTO.getGuestName());
        assertEquals(i1.getReservation().getId(), invitationDTO.getReservationId());
        assertEquals(i1.getStatus(), invitationDTO.getStatus());

    }

}

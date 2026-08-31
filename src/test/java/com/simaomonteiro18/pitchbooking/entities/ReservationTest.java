package com.simaomonteiro18.pitchbooking.entities;

import com.simaomonteiro18.pitchbooking.entities.enums.PitchType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;

public class ReservationTest {

    User u1 = new User("Simão", "sm@gmail.com", "912345678", "Sintra");
    User u2 = new User("João", "joao.silva@gmail.com", "913456789", "Lisboa");
    User u3 = new User("Marta", "marta.costa@gmail.com", "914567890", "Cascais");
    User u4 = new User("Pedro", "pedro.santos@gmail.com", "915678901", "Oeiras");
    User u5 = new User("Inês", "ines.ferreira@gmail.com", "916789012", "Amadora");
    User u6 = new User("Diogo", "diogo.rodrigues@gmail.com", "917890123", "Mafra");
    User u7 = new User("Mafalda", "mafalda.rodrigues@gmail.com", "912846123", "Mafra");

    Pitch pitch = new Pitch("Estádio da Luz", "Benfica", 50.0, PitchType.ELEVEN);

    Reservation reservation = new Reservation(u1, pitch, Instant.now(), LocalDateTime.parse("2026-08-31T20:00:00"), LocalDateTime.parse("2026-08-31T21:00:00"));

    Invitation i1 = new Invitation(u2, reservation);
    Invitation i2 = new Invitation(u3, reservation);
    Invitation i3 = new Invitation(u4, reservation);
    Invitation i4 = new Invitation(u5, reservation);
    Invitation i5 = new Invitation(u6, reservation);
    Invitation i6 = new Invitation(u7, reservation);

    @Test
    @DisplayName("Convites")
    void inviteDecisions() {

        reservation.getInviteList().add(i1);
        reservation.getInviteList().add(i2);
        reservation.getInviteList().add(i3);
        reservation.getInviteList().add(i4);
        reservation.getInviteList().add(i5);
        reservation.getInviteList().add(i6);

        i1.accept();
        i2.accept();
        i3.accept();
        i4.accept();
        i5.reject();

        assertEquals(4, reservation.invitesAccepted());

        assertEquals(10.0, reservation.pricePerPerson());

    }

}

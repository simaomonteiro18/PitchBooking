package com.simaomonteiro18.pitchbooking.services;

import com.simaomonteiro18.pitchbooking.entities.Invitation;
import com.simaomonteiro18.pitchbooking.entities.Pitch;
import com.simaomonteiro18.pitchbooking.entities.Reservation;
import com.simaomonteiro18.pitchbooking.entities.User;
import com.simaomonteiro18.pitchbooking.entities.enums.InvitationStatus;
import com.simaomonteiro18.pitchbooking.entities.enums.PitchType;
import com.simaomonteiro18.pitchbooking.repositories.InvitationRepository;
import com.simaomonteiro18.pitchbooking.repositories.ReservationRepository;
import com.simaomonteiro18.pitchbooking.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class InvitationServiceTest {

    @Mock UserRepository userRepository;

    @Mock ReservationRepository reservationRepository;

    @Mock InvitationRepository invitationRepository;

    @InjectMocks private InvitationService invitationService;

    @Test
    @DisplayName("Teste Positivo")
    void createsInviteWithSuccess() {

        User guest = new User("Simão", "sm18@gmail.com", "921323536","Sintra");

        User user = new User("Carolina", "carol@gmail.com", "912345678", "Massamá");

        // Os dois setId seguintes são necessários porque, sem eles, ambos os User
        // ficam com id null. O equals() de User compara por id, e null == null,
        // logo dois utilizadores diferentes eram considerados iguais.
        // Isto causava um falso positivo na validação de auto-convite dentro do
        // InvitationService (reservation.getOrganizer().equals(guest)), fazendo
        // o teste falhar mesmo com guest e organizador a serem pessoas distintas.
        guest.setId(1L);
        user.setId(2L);

        Pitch pitch = new Pitch("Real", "Massamá", 20.0, PitchType.ELEVEN);

        Reservation reservation = new Reservation(user, pitch, Instant.now(), LocalDateTime.parse("2026-09-04T20:00:00"), LocalDateTime.parse("2026-09-04T21:00:00"));

        Invitation invitation = new Invitation(guest, reservation);

        when(userRepository.findById(1L)).thenReturn(Optional.of(guest));

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        when(invitationRepository.existsByGuestAndReservation(guest, reservation)).thenReturn(false);

        when(invitationRepository.save(any(Invitation.class))).thenReturn(invitation);

        Invitation invitationFinal = invitationService.createInvitation(1L, 1L);

        assertEquals(InvitationStatus.PENDING, invitationFinal.getStatus());

        assertNotNull(invitationFinal);

        assertEquals(guest, invitationFinal.getGuest());

    }

}

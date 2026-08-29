package com.simaomonteiro18.pitchbooking.services;

import com.simaomonteiro18.pitchbooking.entities.Invitation;
import com.simaomonteiro18.pitchbooking.entities.Reservation;
import com.simaomonteiro18.pitchbooking.entities.User;
import com.simaomonteiro18.pitchbooking.exceptions.InvalidGuestException;
import com.simaomonteiro18.pitchbooking.exceptions.InvitationConflictException;
import com.simaomonteiro18.pitchbooking.exceptions.ResourceNotFoundException;
import com.simaomonteiro18.pitchbooking.repositories.InvitationRepository;
import com.simaomonteiro18.pitchbooking.repositories.ReservationRepository;
import com.simaomonteiro18.pitchbooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Invitation createInvitation(Long userId, Long reservationId) {

        User guest = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, userId));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException(Reservation.class, reservationId));

        if (invitationRepository.existsByGuestAndReservation(guest, reservation)) {
            throw new InvitationConflictException("O utilizador já foi convidado previamente");
        }

        if (reservation.getOrganizer().equals(guest)) {
            throw new InvalidGuestException("O Organizador/a não se pode convidar a ele/a próprio/a");
        }

        Invitation invitation = new Invitation(guest, reservation);

        return invitationRepository.save(invitation);

    }

    public Invitation acceptInvitation(Long invitationId) {

        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new ResourceNotFoundException(Invitation.class, invitationId));

        invitation.accept();

        return invitationRepository.save(invitation);

    }

    public Invitation rejectInvitation(Long invitationId) {

        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new ResourceNotFoundException(Invitation.class, invitationId));

        invitation.reject();

        return invitationRepository.save(invitation);

    }


}

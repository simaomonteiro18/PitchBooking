package com.simaomonteiro18.pitchbooking.dtos;

import com.simaomonteiro18.pitchbooking.entities.enums.InvitationStatus;

public class InvitationDTO {

    private Long id;
    private String guestName;
    private Long reservationId;
    private InvitationStatus status;

    public InvitationDTO() {
    }

    public InvitationDTO(Long id, String guestName, Long reservationId, InvitationStatus status) {
        this.id = id;
        this.guestName = guestName;
        this.reservationId = reservationId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public InvitationStatus getStatus() {
        return status;
    }
    
}

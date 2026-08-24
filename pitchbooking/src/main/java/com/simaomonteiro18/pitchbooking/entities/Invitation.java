package com.simaomonteiro18.pitchbooking.entities;

import com.simaomonteiro18.pitchbooking.entities.enums.InvitationStatus;

import java.util.Objects;

public class Invitation {

    private Long id;
    private User user;
    private Reservation reservation;
    private InvitationStatus status = InvitationStatus.PENDING;

    public Invitation() {
    }

    public Invitation(User user, Reservation reservation) {
        this.user = user;
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void accept() {
        this.status = InvitationStatus.ACCEPTED;
    }

    public void reject() {
        this.status = InvitationStatus.REJECTED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invitation that = (Invitation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

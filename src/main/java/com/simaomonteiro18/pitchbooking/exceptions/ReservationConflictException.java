package com.simaomonteiro18.pitchbooking.exceptions;

public class ReservationConflictException extends RuntimeException {
    public ReservationConflictException(String message) {
        super(message);
    }
}

package com.simaomonteiro18.pitchbooking.requests;

import java.time.LocalDateTime;

public record CreateReservationRequest(Long userId, Long pitchId, LocalDateTime startTime, LocalDateTime endTime) {

}

package com.simaomonteiro18.pitchbooking.controllers;

import com.simaomonteiro18.pitchbooking.entities.Pitch;
import com.simaomonteiro18.pitchbooking.entities.Reservation;
import com.simaomonteiro18.pitchbooking.entities.User;
import com.simaomonteiro18.pitchbooking.entities.enums.PitchType;
import com.simaomonteiro18.pitchbooking.requests.CreateReservationRequest;
import com.simaomonteiro18.pitchbooking.services.ReservationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReservationService reservationService;

    CreateReservationRequest request = new CreateReservationRequest(1L, 1L, LocalDateTime.parse("2026-09-28T16:00:00"), LocalDateTime.parse("2026-09-28T18:00:00"));

    User organizer = new User("Simão", "sm@gmail.com", "912345678", "Sintra");
    Pitch pitch = new Pitch("Sintrense", "Sintra", 20.0, PitchType.ELEVEN);
    Reservation reservation = new Reservation(organizer, pitch, Instant.now(), request.startTime(), request.endTime());

    @Test
    @DisplayName("Teste a createReservation() com sucesso")
    public void testCreateReservationWithSuccess() throws Exception {

        when(reservationService.createReservation(1L, 1L, request.startTime(), request.endTime())).thenReturn(reservation);

        mockMvc.perform(post("/reservations").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated());

    }

}

package com.simaomonteiro18.pitchbooking.controllers;

import com.simaomonteiro18.pitchbooking.dtos.PitchSummaryDTO;
import com.simaomonteiro18.pitchbooking.entities.Pitch;
import com.simaomonteiro18.pitchbooking.mappers.PitchMapper;
import com.simaomonteiro18.pitchbooking.services.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pitches")
public class PitchController {

    @Autowired
    private PitchService pitchService;

    @GetMapping
    public ResponseEntity<List<PitchSummaryDTO>> findAll() {

        List<Pitch> list = pitchService.findAll();

        List<PitchSummaryDTO> finalListPitches = list.stream()
                .map(PitchMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(finalListPitches);

    }

    @GetMapping(params = "city")
    public ResponseEntity<List<PitchSummaryDTO>> findByCity(@RequestParam("city") String city) {

        List<Pitch> list = pitchService.findByCity(city);

        List<PitchSummaryDTO> finalListPitches = list.stream()
                .map(PitchMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(finalListPitches);

    }

}

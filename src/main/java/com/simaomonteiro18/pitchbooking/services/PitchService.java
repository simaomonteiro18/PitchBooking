package com.simaomonteiro18.pitchbooking.services;

import com.simaomonteiro18.pitchbooking.entities.Pitch;
import com.simaomonteiro18.pitchbooking.repositories.PitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PitchService {


    @Autowired
    private PitchRepository pitchRepository;

    public List<Pitch> findAll() {
        return pitchRepository.findAll();
    }

    public List<Pitch> findByCity(String city){
        return pitchRepository.findByCity(city);
    }


}

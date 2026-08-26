package com.simaomonteiro18.pitchbooking.repositories;

import com.simaomonteiro18.pitchbooking.entities.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PitchRepository extends JpaRepository<Pitch, Long> {

    List<Pitch> findByCity(String city);

}

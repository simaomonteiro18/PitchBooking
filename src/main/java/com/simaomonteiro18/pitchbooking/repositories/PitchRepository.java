package com.simaomonteiro18.pitchbooking.repositories;

import com.simaomonteiro18.pitchbooking.entities.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitchRepository extends JpaRepository<Pitch, Long> {
}

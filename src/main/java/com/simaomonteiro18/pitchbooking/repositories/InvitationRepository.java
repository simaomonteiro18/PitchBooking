package com.simaomonteiro18.pitchbooking.repositories;

import com.simaomonteiro18.pitchbooking.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}

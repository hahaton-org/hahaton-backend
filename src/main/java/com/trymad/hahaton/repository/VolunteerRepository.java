package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.Volunteer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VolunteerRepository extends JpaRepository<Volunteer, UUID> {

    @EntityGraph(attributePaths = {
        "achievements",
        "achievements.bonuses",
        "achievements.bonusArchives"
    })
    Optional<Volunteer> findById(UUID id);
}

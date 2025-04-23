package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.Achievement;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AchievementRepository extends JpaRepository<Achievement, UUID> {

    @EntityGraph(attributePaths = {
        "volunteer",
        "bonuses",
        "bonusArchives"
    })
    Optional<Achievement> findById(UUID id);
}

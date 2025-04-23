package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.BonusArchive;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BonusArchiveRepository extends JpaRepository<BonusArchive, UUID> {

    @EntityGraph(attributePaths = {
        "achievement",
        "category",
        "givenBonuses"
    })
    Optional<BonusArchive> findById(UUID id);
}

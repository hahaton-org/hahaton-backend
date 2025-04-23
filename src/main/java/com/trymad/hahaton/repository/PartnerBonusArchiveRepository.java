package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.PartnerBonusArchive;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PartnerBonusArchiveRepository extends JpaRepository<PartnerBonusArchive, UUID> {

    @EntityGraph(attributePaths = {
        "partner",
        "category",
        "givenBonuses"
    })
    Optional<PartnerBonusArchive> findById(UUID id);
}

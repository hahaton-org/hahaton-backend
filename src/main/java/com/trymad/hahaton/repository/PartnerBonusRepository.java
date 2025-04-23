package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.PartnerBonus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PartnerBonusRepository extends JpaRepository<PartnerBonus, UUID> {

    @EntityGraph(attributePaths = {
        "partner",
        "category",
    })
    Optional<PartnerBonus> findById(UUID id);
}

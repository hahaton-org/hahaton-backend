package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.GivenBonus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GivenBonusRepository extends JpaRepository<GivenBonus, UUID> {

    @EntityGraph(attributePaths = {
        "bonus",
        "partnerBonus"
    })
    Optional<GivenBonus> findFetchById(UUID id);

}

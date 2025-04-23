package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.Bonus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BonusRepository extends JpaRepository<Bonus, UUID> {

    @EntityGraph(attributePaths = {
        "achievement",
        "category",
    })
    Optional<Bonus> findById(UUID id);
}

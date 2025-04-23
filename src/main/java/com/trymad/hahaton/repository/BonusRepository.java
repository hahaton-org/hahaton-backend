package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.Bonus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BonusRepository extends JpaRepository<Bonus, UUID> {

    @EntityGraph(attributePaths = {
        "achievement",
        "category",
    })
    Optional<Bonus> findFetchById(UUID id);

    @Query("""
        SELECT b FROM Bonus b
        WHERE b.active = true
    """)
    List<Bonus> findActual();
}

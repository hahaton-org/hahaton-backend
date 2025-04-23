package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.Bonus;
import com.trymad.hahaton.entity.PartnerBonus;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PartnerBonusRepository extends JpaRepository<PartnerBonus, UUID> {

    @EntityGraph(attributePaths = {
        "partner",
        "category",
    })
    Optional<PartnerBonus> findFetchById(UUID id);

    @Query("""
        SELECT pb FROM PartnerBonus pb
        WHERE pb.active = true
    """)
    List<Bonus> findActual();
}

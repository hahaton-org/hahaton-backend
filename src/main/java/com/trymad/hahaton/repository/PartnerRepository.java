package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.Partner;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PartnerRepository extends JpaRepository<Partner, UUID> {

    @EntityGraph(attributePaths = {
        "partnerBonuses",
        "partnerBonusArchives"
    })

    Optional<Partner> findFetchById(UUID id);

}

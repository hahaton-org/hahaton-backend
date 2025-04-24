package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.PartnerBonus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PartnerBonusRepository extends JpaRepository<PartnerBonus, UUID> {


    Optional<PartnerBonus> findById(UUID id);

    @Query("""
        SELECT pb FROM PartnerBonus pb
        WHERE pb.active = true
    """)
    List<PartnerBonus> findActual();

    @Query("""
        SELECT pb FROM PartnerBonus pb
        WHERE pb.partner.id = :id
    """)
    List<PartnerBonus> findByPartnerId(@Param("id") UUID id);
}

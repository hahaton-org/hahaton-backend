package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BonusRepository extends JpaRepository<Bonus, UUID> {

    @Query("""
        SELECT b FROM Bonus b
        WHERE b.active = true
    """)
    List<Bonus> findActual();

    @Query("""
        SELECT b FROM Bonus b
        WHERE b.achievement.volunteer.id = :id
    """)
    List<Bonus> findByVolunteerId(@Param("id") UUID id);

    @Modifying
    @Query("""
        UPDATE Bonus b
        SET b.active = false
        WHERE b.active = true
    """)
    void deactivateAllActiveBonuses();
}

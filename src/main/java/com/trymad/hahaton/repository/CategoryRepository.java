package com.trymad.hahaton.repository;

import com.trymad.hahaton.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @EntityGraph(attributePaths = {
        "bonuses",
        "bonusArchives",
        "partnerBonuses",
        "partnerBonusArchives"
    })
    Optional<Category> findById(Integer id);
}

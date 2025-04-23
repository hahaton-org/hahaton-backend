package com.trymad.hahaton.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bonuses_archive")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonusArchive {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "bonus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GivenBonus> givenBonuses;
}

package com.trymad.hahaton.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "given_bonuses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GivenBonus {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bonus_id", nullable = false)
    private BonusArchive bonus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_bonus_id", nullable = false)
    private PartnerBonusArchive partnerBonus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
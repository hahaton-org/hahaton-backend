package com.trymad.hahaton.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "given_bonuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GivenBonus {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bonus_id")
    private BonusArchive bonus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_bonus_id")
    private PartnerBonusArchive partnerBonus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

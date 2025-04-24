package com.trymad.hahaton.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "partner_bonuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerBonus {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "partnerBonus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GivenBonus> givenBonuses;
}

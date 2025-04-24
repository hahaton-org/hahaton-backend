package com.trymad.hahaton.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.trymad.hahaton.entity.CategoryType;

public record PartnerBonusDTO(
    UUID id,
    UUID partnerId,
    String partnerName,
    CategoryType category,
    String description,
    boolean active,
    LocalDateTime createdAt
) {}
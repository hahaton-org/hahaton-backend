package com.trymad.hahaton.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PartnerBonusDTO(
    UUID id,
    UUID partnerId,
    Integer categoryId,
    String description,
    boolean active,
    LocalDateTime createdAt
) {}
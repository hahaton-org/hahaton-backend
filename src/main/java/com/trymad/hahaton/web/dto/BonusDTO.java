package com.trymad.hahaton.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BonusDTO (
    UUID id,
    UUID achievementId,
    Integer categoryId,
    boolean active,
    LocalDateTime createdAt
) {}
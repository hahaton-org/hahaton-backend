package com.trymad.hahaton.web.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BonusDTO (
    UUID id,
    UUID achievementId,
    Integer categoryId,
    LocalDateTime createdAt
) {}
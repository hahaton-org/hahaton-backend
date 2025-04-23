package com.trymad.hahaton.web.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AchievementDTO(
    UUID id,
    UUID volunteerId,
    String description,
    LocalDateTime createdAt
) {}

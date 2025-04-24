package com.trymad.hahaton.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AchievementDTO(
    UUID id,
    UUID volunteerId,
    String volunteerFirstName,
    String volunteerLastName,
    String volunteerMiddleName,
    String description,
    LocalDateTime createdAt
) {}

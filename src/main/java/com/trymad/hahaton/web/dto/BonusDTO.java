package com.trymad.hahaton.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.trymad.hahaton.entity.CategoryType;

public record BonusDTO (
    UUID id,
    UUID achievementId,
    String achievementDesc,
    UUID volunteerId,
    String volunteerFirstName,
    String volunteerLastName,
    String volunteerMiddleName,
    CategoryType category,
    boolean active,
    LocalDateTime createdAt
) {}
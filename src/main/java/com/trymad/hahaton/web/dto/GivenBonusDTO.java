package com.trymad.hahaton.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.trymad.hahaton.entity.CategoryType;

public record GivenBonusDTO(
    UUID id,
    UUID bonusId,
    UUID volunteerId,
    String volunteerFirstName,
    String volunteerLastName,
    String volunteerMiddleName,
    UUID partnerId,
    String partnerName,
    String partnerType,
    CategoryType category,
    UUID partnerBonusId,
    LocalDateTime createdAt
) {}

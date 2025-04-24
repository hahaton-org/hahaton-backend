package com.trymad.hahaton.web.dto.create;

import java.util.UUID;

import com.trymad.hahaton.entity.CategoryType;

public record BonusCreateDTO (
    UUID achievementId,
    CategoryType category
) {}

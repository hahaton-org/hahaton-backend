package com.trymad.hahaton.web.dto.create;

import java.util.UUID;

public record BonusCreateDTO (
    UUID achievementId,
    Integer categoryId
) {}

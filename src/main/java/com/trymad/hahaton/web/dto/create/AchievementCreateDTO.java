package com.trymad.hahaton.web.dto.create;

import java.util.UUID;

public record AchievementCreateDTO(
    UUID volunteerId,
    String description
) {}


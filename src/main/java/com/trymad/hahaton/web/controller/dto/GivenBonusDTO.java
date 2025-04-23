package com.trymad.hahaton.web.controller.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record GivenBonusDTO(
    UUID id,
    UUID bonusId,
    UUID partnerBonusId,
    LocalDateTime createdAt
) {}

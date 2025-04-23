package com.trymad.hahaton.web.dto.create;

import java.util.UUID;

public record GivenBonusCreateDTO(
    UUID bonusId,
    UUID partnerBonusId
) {}

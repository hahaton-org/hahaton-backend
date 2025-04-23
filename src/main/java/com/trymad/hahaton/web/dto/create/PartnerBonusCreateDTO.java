package com.trymad.hahaton.web.dto.create;

import java.util.UUID;

public record PartnerBonusCreateDTO(
    UUID partnerId,
    Integer categoryId,
    String description
) {} 
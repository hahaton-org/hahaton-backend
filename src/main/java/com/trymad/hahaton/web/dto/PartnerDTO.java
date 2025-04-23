package com.trymad.hahaton.web.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PartnerDTO(
    UUID id,
    String name,
    String type,
    String inn,
    String mail,
    boolean active,
    LocalDateTime createdAt
) {}


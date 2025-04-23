package com.trymad.hahaton.web.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record VolunteerDTO(
    UUID id,
    String inn,
    String phone,
    String mail,
    LocalDate birthdayDate,
    boolean active,
    LocalDateTime createdAt
) {}

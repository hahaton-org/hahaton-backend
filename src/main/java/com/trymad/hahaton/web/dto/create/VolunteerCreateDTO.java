package com.trymad.hahaton.web.dto.create;

import java.time.LocalDate;

public record VolunteerCreateDTO (
    String inn,
    String phone,
    String firstName,
    String lastName,
    String middleName,
    String mail,
    LocalDate birthdayDate
) {}

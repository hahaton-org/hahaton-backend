package com.trymad.hahaton.web.dto.update;

import java.time.LocalDate;

public record VolunteerUpdateDTO (
    String inn,
    String phone,
    String firstName,
    String lastName,
    String middleName,
    String mail,
    LocalDate birthdayDate
) {}
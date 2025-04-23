package com.trymad.hahaton.web.dto;

import com.trymad.hahaton.entity.CategoryType;

public record CategoryDTO(
    Integer id,
    CategoryType name
) {}
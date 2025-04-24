package com.trymad.hahaton.web.dto.create;

public record PartnerCreateDTO(
    String name,
    String type,
    String inn,
    String mail,
    String password  
) {}

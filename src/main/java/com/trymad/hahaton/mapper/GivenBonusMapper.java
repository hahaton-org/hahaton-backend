package com.trymad.hahaton.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.hahaton.entity.GivenBonus;
import com.trymad.hahaton.web.dto.GivenBonusDTO;

@Mapper(componentModel = "spring")
public interface GivenBonusMapper {

	@Mapping(source = "bonus.id", target = "bonusId")
	@Mapping(source = "partnerBonus.id", target = "partnerBonusId")
	GivenBonusDTO toDto(GivenBonus entity);

    List<GivenBonusDTO> toDto(List<GivenBonus> entities);
}

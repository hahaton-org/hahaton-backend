package com.trymad.hahaton.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.hahaton.entity.PartnerBonus;
import com.trymad.hahaton.web.dto.PartnerBonusDTO;

@Mapper(componentModel = "spring")
public interface PartnerBonusMapper {

	@Mapping(source = "partner.id", target = "partnerId")
	@Mapping(source = "partner.name", target = "partnerName")
	@Mapping(source = "partner.type", target = "partnerType")
	@Mapping(source = "category.name", target = "category")
	PartnerBonusDTO toDto(PartnerBonus entity);

    List<PartnerBonusDTO> toDto(List<PartnerBonus> entities);
}

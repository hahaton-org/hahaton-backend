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
	@Mapping(source = "bonus.achievement.volunteer.firstName", target = "volunteerFirstName")
	@Mapping(source = "bonus.achievement.volunteer.lastName", target = "volunteerLastName")
	@Mapping(source = "bonus.achievement.volunteer.middleName", target = "volunteerMiddleName")
	@Mapping(source = "bonus.achievement.volunteer.id", target = "volunteerId")
	@Mapping(source = "bonus.category.name", target = "category")
	@Mapping(source = "partnerBonus.partner.id", target = "partnerId")
	@Mapping(source = "partnerBonus.partner.name", target = "partnerName")
	@Mapping(source = "partnerBonus.partner.type", target = "partnerType")
	GivenBonusDTO toDto(GivenBonus entity);

    List<GivenBonusDTO> toDto(List<GivenBonus> entities);
}

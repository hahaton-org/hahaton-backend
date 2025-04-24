package com.trymad.hahaton.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.hahaton.entity.Bonus;
import com.trymad.hahaton.web.dto.BonusDTO;

@Mapper(componentModel = "spring")
public interface BonusMapper {

	@Mapping(source = "achievement.id", target = "achievementId")
	@Mapping(source = "achievement.description", target = "achievementDesc")
	@Mapping(source = "category.name", target = "category")
	@Mapping(source = "achievement.volunteer.firstName", target = "volunteerFirstName")
	@Mapping(source = "achievement.volunteer.lastName", target = "volunteerLastName")
	@Mapping(source = "achievement.volunteer.middleName", target = "volunteerMiddleName")
	@Mapping(source = "achievement.volunteer.id", target = "volunteerId")
	BonusDTO toDto(Bonus entity);

    List<BonusDTO> toDto(List<Bonus> entities);
}

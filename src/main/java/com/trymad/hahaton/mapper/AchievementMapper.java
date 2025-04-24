package com.trymad.hahaton.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.hahaton.entity.Achievement;
import com.trymad.hahaton.web.dto.AchievementDTO;

@Mapper(componentModel = "spring")
public interface AchievementMapper {

	@Mapping(source = "volunteer.id", target = "volunteerId")
	@Mapping(source = "volunteer.firstName", target = "volunteerFirstName")
	@Mapping(source = "volunteer.lastName", target = "volunteerLastName")
	@Mapping(source = "volunteer.middleName", target = "volunteerMiddleName")
	AchievementDTO toDto(Achievement entity);

    List<AchievementDTO> toDto(List<Achievement> entities);
}

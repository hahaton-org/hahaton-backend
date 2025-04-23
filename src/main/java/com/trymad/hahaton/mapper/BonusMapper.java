package com.trymad.hahaton.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.hahaton.entity.Bonus;
import com.trymad.hahaton.web.dto.BonusDTO;

@Mapper(componentModel = "spring")
public interface BonusMapper {

	@Mapping(source = "achievement.id", target = "achievementId")
	@Mapping(source = "category.name", target = "category")
	BonusDTO toDto(Bonus entity);

    List<BonusDTO> toDto(List<Bonus> entities);
}

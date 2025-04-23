package com.trymad.hahaton.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.trymad.hahaton.entity.Partner;
import com.trymad.hahaton.web.dto.PartnerDTO;

@Mapper(componentModel = "spring")
public interface PartnerMapper {
	
	PartnerDTO toDto(Partner partner);

	List<PartnerDTO> toDto(List<Partner> partner);

}

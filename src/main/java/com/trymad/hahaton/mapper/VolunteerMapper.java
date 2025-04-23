package com.trymad.hahaton.mapper;

import com.trymad.hahaton.entity.Volunteer;
import com.trymad.hahaton.web.dto.VolunteerDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VolunteerMapper {

    VolunteerDTO toDto(Volunteer entity);

    List<VolunteerDTO> toDto(List<Volunteer> entities);

}

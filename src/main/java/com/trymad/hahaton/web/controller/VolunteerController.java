package com.trymad.hahaton.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.hahaton.mapper.BonusMapper;
import com.trymad.hahaton.mapper.VolunteerMapper;
import com.trymad.hahaton.service.BonusService;
import com.trymad.hahaton.service.VolunteerService;
import com.trymad.hahaton.web.dto.BonusDTO;
import com.trymad.hahaton.web.dto.VolunteerDTO;
import com.trymad.hahaton.web.dto.update.VolunteerUpdateDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/volunteers")
public class VolunteerController {
	
	private final VolunteerService volunteerService;
	private final VolunteerMapper volunteerMapper;
	private final BonusService bonusService;
	private final BonusMapper bonusMapper;


	@GetMapping
	public List<VolunteerDTO> getAll() {
		return volunteerMapper.toDto(volunteerService.getAll());
	}

	@GetMapping("/{id}")
	public VolunteerDTO getById(@PathVariable UUID id) {
		return volunteerMapper.toDto(volunteerService.getFetchById(id));
	}

	@GetMapping("/{id}/bonuses")
	public List<BonusDTO> getBonusesByVolunteer(
		@PathVariable UUID id,
		@RequestParam(required = false, defaultValue = "true") boolean actual) {
		return bonusMapper.toDto(bonusService.getBonusesByVolunteerId(id, actual));
	}

	@PutMapping("/{id}")
	public VolunteerDTO updateById(@RequestBody VolunteerUpdateDTO dto, @PathVariable UUID id) {
		return volunteerMapper.toDto(volunteerService.update(id, dto));
	}
	

}

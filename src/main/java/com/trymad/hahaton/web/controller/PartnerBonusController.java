package com.trymad.hahaton.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.hahaton.entity.CategoryType;
import com.trymad.hahaton.mapper.PartnerBonusMapper;
import com.trymad.hahaton.service.PartnerBonusService;
import com.trymad.hahaton.web.dto.PartnerBonusDTO;
import com.trymad.hahaton.web.dto.update.PartnerBonusUpdateDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/partners/bonuses")
public class PartnerBonusController {
	
	private final PartnerBonusService partnerBonusService;
	private final PartnerBonusMapper partnerBonusMapper;

	@GetMapping
	public List<PartnerBonusDTO> getAll(
		@RequestParam(required = false, defaultValue = "true") boolean actual,
		@RequestParam(required = false) CategoryType category) {
		return partnerBonusMapper.toDto(partnerBonusService.getAll(actual, category));
	}

	@PutMapping("/{id}")
	public PartnerBonusDTO update(@PathVariable UUID id, @RequestBody PartnerBonusUpdateDTO dto) {
		return partnerBonusMapper.toDto(partnerBonusService.update(id, dto));
	}

}

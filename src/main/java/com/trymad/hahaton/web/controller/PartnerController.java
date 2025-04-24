package com.trymad.hahaton.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.hahaton.mapper.PartnerBonusMapper;
import com.trymad.hahaton.mapper.PartnerMapper;
import com.trymad.hahaton.service.PartnerBonusService;
import com.trymad.hahaton.service.PartnerService;
import com.trymad.hahaton.web.dto.PartnerBonusDTO;
import com.trymad.hahaton.web.dto.PartnerDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/partners")
public class PartnerController {
	
	private final PartnerService partnerService;
	private final PartnerMapper partnerMapper;
	private final PartnerBonusService partnerBonusService;
	private final PartnerBonusMapper partnerBonusMapper;

	@GetMapping
	public List<PartnerDTO> getAll() {
		return partnerMapper.toDto(partnerService.getAll());
	}

	@GetMapping("/{id}")
	public PartnerDTO getById(@PathVariable UUID id) {
		return partnerMapper.toDto(partnerService.getFetchById(id));
	}

	@GetMapping("/{id}/bonuses")
	public List<PartnerBonusDTO> getBonusesByPartner(
		@PathVariable UUID id,
		@RequestParam(required = false, defaultValue = "true") boolean actual) {
		return partnerBonusMapper.toDto(partnerBonusService.getBonusesByPartnerId(id, actual));
	}
	
}

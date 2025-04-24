package com.trymad.hahaton.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.hahaton.entity.CategoryType;
import com.trymad.hahaton.mapper.BonusMapper;
import com.trymad.hahaton.service.BonusService;
import com.trymad.hahaton.web.dto.BonusDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/volunteers/bonuses")
public class BonusController {
	
	private final BonusService bonusService;
	private final BonusMapper bonusMapper;

	@GetMapping
	public List<BonusDTO> getAll(
		@RequestParam(required = false, defaultValue = "true") boolean actual,
		@RequestParam(required = false) CategoryType category) {
		return bonusMapper.toDto(bonusService.getAll(actual, category));
	}
}

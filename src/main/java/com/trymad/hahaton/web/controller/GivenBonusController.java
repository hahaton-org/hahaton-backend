package com.trymad.hahaton.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.hahaton.mapper.GivenBonusMapper;
import com.trymad.hahaton.service.GivenBonusService;
import com.trymad.hahaton.web.dto.GivenBonusDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bonuses/history")
public class GivenBonusController {
	
	private final GivenBonusService givenBonusService;
	private final GivenBonusMapper givenBonusMapper;

	@GetMapping
	public List<GivenBonusDTO> getAll() {
		return givenBonusMapper.toDto(givenBonusService.getAll());
	}
}

package com.trymad.hahaton.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.hahaton.mapper.AchievementMapper;
import com.trymad.hahaton.service.AchievementService;
import com.trymad.hahaton.web.dto.AchievementDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/volunteers/achievements")
public class AchievementController {
	
	private final AchievementService achievementService;
	private final AchievementMapper achievementMapper;

	@GetMapping("/{id}")
	public AchievementDTO getById(@PathVariable UUID id) {
		return achievementMapper.toDto(achievementService.getFetchById(id));
	}
	
	@GetMapping
	public List<AchievementDTO> getAll(@RequestParam(required = false) UUID volunteerId) {
		return achievementMapper.toDto(achievementService.getAll(volunteerId));
	}

}

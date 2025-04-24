package com.trymad.hahaton.service;

import com.trymad.hahaton.entity.Achievement;
import com.trymad.hahaton.entity.Bonus;
import com.trymad.hahaton.entity.Category;
import com.trymad.hahaton.entity.CategoryType;
import com.trymad.hahaton.repository.BonusRepository;
import com.trymad.hahaton.web.dto.create.BonusCreateDTO;
import com.trymad.hahaton.web.dto.update.BonusUpdateDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BonusService {

    private final BonusRepository bonusRepository;
    private final AchievementService achievementService;
    private final CategoryService categoryService;

	private final static boolean DEFAULT_CREATE_ACTIVE = true;

    @Transactional(readOnly = true)
    public Bonus getById(UUID id) {
        return bonusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bonus not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Bonus getFetchById(UUID id) {
        return bonusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bonus not found with id: " + id));
    }

	@Transactional(readOnly = true)
	public List<Bonus> getAll(boolean actual, CategoryType category) {
		List<Bonus> result = bonusRepository.findAll();
		if(actual) result = result.stream().filter(curBonus -> curBonus.isActive()).toList();
		if(category != null) result = result.stream().filter(curBonus -> curBonus.getCategory().getName().equals(category)).toList();
		return result;
	}

	public List<Bonus> getBonusesByVolunteerId(UUID volunteerId, boolean actual) {
		final List<Bonus> volunteerBonuses = bonusRepository.findByVolunteerId(volunteerId);
		List<Bonus> result = volunteerBonuses;
		if(actual) result = result.stream().filter(bonus -> bonus.isActive()).collect(Collectors.toList());
		return result;
	}

    public Bonus create(BonusCreateDTO dto) {
        final LocalDateTime now = LocalDateTime.now();
        final UUID bonusId = UUID.randomUUID();

        final Achievement achievement = achievementService.getById(dto.achievementId());
        final Category category = categoryService.getByName(dto.category());

        final Bonus bonus = Bonus.builder()
                .id(bonusId)
                .achievement(achievement)
                .category(category)
                .createdAt(now)
				.active(DEFAULT_CREATE_ACTIVE)
                .updatedAt(now)
                .build();

        final Bonus savedBonus = bonusRepository.save(bonus);

        return savedBonus;
    }

    public List<Bonus> createAll(List<BonusCreateDTO> dtos) {
        Category max = categoryService.getByName(CategoryType.MAXIMUM);
        Category medium = categoryService.getByName(CategoryType.MEDIUM);
        Category minimum = categoryService.getByName(CategoryType.MINIMUM);
        List<Bonus> entities = new ArrayList<>();
        
        final LocalDateTime now = LocalDateTime.now();
        dtos.forEach(dto -> {
            
            final UUID bonusId = UUID.randomUUID();
            Category category;
            if(dto.category() == CategoryType.MAXIMUM) {
                category = max;
            } else if(dto.category() == CategoryType.MEDIUM) {
                category = medium;
            } else {
                category = minimum;
            }

            Achievement achievement = achievementService.getReference(dto.achievementId());
            final Bonus bonus = Bonus.builder()
            .id(bonusId)
                .achievement(achievement)
                .category(category)
                .createdAt(now)
                .active(DEFAULT_CREATE_ACTIVE)
                .updatedAt(now)
            .build();

            entities.add(bonus);
        });
        
        return entities;
    }

	public Bonus update(UUID id, BonusUpdateDTO dto) {
		Bonus bonus = getById(id);
	
		bonus.setActive(dto.active());
		bonus.setUpdatedAt(LocalDateTime.now());
	
		Bonus updatedBonus = bonusRepository.save(bonus);
	
		return updatedBonus;
	}

	@Transactional(readOnly = true)
	public List<Bonus> getActual() {
		return bonusRepository.findActual();
	}

	public void deleteAll() {
		bonusRepository.deleteAll();
	}

    public void delete(UUID id) {
        final Bonus bonus = this.getById(id);

        bonusRepository.delete(bonus);
    }

    public void deactivateBonuses() {
        bonusRepository.deactivateAllActiveBonuses();
    }
}

package com.trymad.hahaton.service;

import com.trymad.hahaton.entity.Achievement;
import com.trymad.hahaton.entity.Bonus;
import com.trymad.hahaton.entity.Category;
import com.trymad.hahaton.repository.BonusRepository;
import com.trymad.hahaton.web.dto.create.BonusCreateDTO;
import com.trymad.hahaton.web.dto.update.BonusUpdateDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

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
        return bonusRepository.findFetchById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bonus not found with id: " + id));
    }

    public Bonus create(BonusCreateDTO dto) {
        final LocalDateTime now = LocalDateTime.now();
        final UUID bonusId = UUID.randomUUID();

        final Achievement achievement = achievementService.getById(dto.achievementId());
        final Category category = categoryService.getById(dto.categoryId());

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

	public Bonus update(UUID id, BonusUpdateDTO dto) {
		Bonus bonus = getById(id);
	
		bonus.setActive(dto.active());
		bonus.setUpdatedAt(LocalDateTime.now());
	
		Bonus updatedBonus = bonusRepository.save(bonus);
	
		return updatedBonus;
	}

    public void delete(UUID id) {
        final Bonus bonus = this.getById(id);

        bonusRepository.delete(bonus);
    }
}

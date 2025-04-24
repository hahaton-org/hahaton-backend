package com.trymad.hahaton.service;

import com.trymad.hahaton.entity.Achievement;
import com.trymad.hahaton.entity.Volunteer;
import com.trymad.hahaton.repository.AchievementRepository;
import com.trymad.hahaton.web.dto.create.AchievementCreateDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final VolunteerService volunteerService;

    @Transactional(readOnly = true)
    public Achievement getById(UUID id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Achievement not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Achievement getFetchById(UUID id) {
        return achievementRepository.findFetchById(id)
                .orElseThrow(() -> new EntityNotFoundException("Achievement not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Achievement getReference(UUID id) {
        return achievementRepository.getReferenceById(id);
    }

	public List<Achievement> getAll(UUID volunteerId) {
		List<Achievement> result = achievementRepository.findAll();
		if(volunteerId != null) result = result.stream().filter(a -> a.getVolunteer().getId().equals(volunteerId)).toList();
		return result;
	}

    public Achievement create(AchievementCreateDTO dto) {
        final LocalDateTime now = LocalDateTime.now();

        final Volunteer volunteer = volunteerService.getById(dto.volunteerId());

        final Achievement achievement = Achievement.builder()
                .id(UUID.randomUUID())
                .volunteer(volunteer)
                .description(dto.description())
                .createdAt(now)
                .updatedAt(now)
                .build();

        return achievementRepository.save(achievement);
    }

    public Map<Integer, Achievement> createAll(Map<Integer, AchievementCreateDTO> dto) {
        final Map<Integer, Achievement> ach = new HashMap<>();
        final LocalDateTime now = LocalDateTime.now();
        dto.forEach( (key, values) -> {
            final Volunteer volunteer = volunteerService.getReference(values.volunteerId());
            final Achievement achievement = Achievement.builder()
                .id(UUID.randomUUID())
                .volunteer(volunteer)
                .description(values.description())
                .createdAt(now)
                .updatedAt(now)
                .build();
            Achievement entity = achievementRepository.save(achievement);
            ach.put(key, entity);
        });

        return ach;
    }

    public Achievement update(UUID id, AchievementCreateDTO dto) {
        final Achievement achievement = this.getById(id);

        final Volunteer volunteer = volunteerService.getById(dto.volunteerId());

        achievement.setVolunteer(volunteer);
        achievement.setDescription(dto.description());
        achievement.setUpdatedAt(LocalDateTime.now());

        return achievementRepository.save(achievement);
    }

    public void delete(UUID id) {
        final Achievement achievement = this.getById(id);

        achievementRepository.delete(achievement);
    }
}

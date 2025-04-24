package com.trymad.hahaton.service;

import com.trymad.hahaton.entity.Bonus;
import com.trymad.hahaton.entity.GivenBonus;
import com.trymad.hahaton.entity.PartnerBonus;
import com.trymad.hahaton.repository.GivenBonusRepository;
import com.trymad.hahaton.web.dto.create.GivenBonusCreateDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GivenBonusService {

    private final GivenBonusRepository givenBonusRepository;
    private final BonusService bonusArchiveService;
    private final PartnerBonusService partnerBonusArchiveService;

    @Transactional(readOnly = true)
    public GivenBonus getById(UUID id) {
        return givenBonusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GivenBonus not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public GivenBonus getFetchById(UUID id) {
        return givenBonusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GivenBonus not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<GivenBonus> getAll() {
        return givenBonusRepository.findAll();
    }

    public GivenBonus create(GivenBonusCreateDTO dto) {
        Bonus bonus = bonusArchiveService.getById(dto.bonusId());
        PartnerBonus partnerBonus = partnerBonusArchiveService.getById(dto.partnerBonusId());
        LocalDateTime now = LocalDateTime.now();

        GivenBonus givenBonus = GivenBonus.builder()
                .id(UUID.randomUUID())
                .bonus(bonus)
                .partnerBonus(partnerBonus)
                .createdAt(now)
                .build();

        return givenBonusRepository.save(givenBonus);
    }

    public void delete(UUID id) {
        GivenBonus givenBonus = getById(id);
        givenBonusRepository.delete(givenBonus);
    }
}

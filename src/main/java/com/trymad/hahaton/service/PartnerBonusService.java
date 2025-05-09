package com.trymad.hahaton.service;

import com.trymad.hahaton.entity.CategoryType;
import com.trymad.hahaton.entity.PartnerBonus;
import com.trymad.hahaton.repository.PartnerBonusRepository;
import com.trymad.hahaton.web.dto.create.PartnerBonusCreateDTO;
import com.trymad.hahaton.web.dto.update.PartnerBonusUpdateDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PartnerBonusService {

    private final PartnerBonusRepository partnerBonusRepository;
    private final PartnerService partnerService;
    private final CategoryService categoryService;

	private final static boolean DEFAULT_CREATE_ACTIVE = true;

    @Transactional(readOnly = true)
    public PartnerBonus getById(UUID id) {
        return partnerBonusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PartnerBonus not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public PartnerBonus getFetchById(UUID id) {
        return partnerBonusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PartnerBonus not found with id: " + id));
    }

	@Transactional(readOnly = true)
	public List<PartnerBonus> getAll(boolean actual, CategoryType category) {
		List<PartnerBonus> result = partnerBonusRepository.findAll();
		if(actual) result = result.stream().filter(pb -> pb.isActive()).toList();
		if(category != null) result.stream().filter(pb -> pb.getCategory().getName().equals(category)).toList();
		return result;
	}

	public List<PartnerBonus>getBonusesByPartnerId(UUID partnerId, boolean actual) {
		final List<PartnerBonus> partnerBonus = partnerBonusRepository.findByPartnerId(partnerId);
		List<PartnerBonus> result = partnerBonus;
		if(actual) result = result.stream().filter(pb -> pb.isActive()).collect(Collectors.toList());
		return result;
	}

    public PartnerBonus create(PartnerBonusCreateDTO dto) {
        final LocalDateTime now = LocalDateTime.now();
        final UUID bonusId = UUID.randomUUID();

        final var partner = partnerService.getById(dto.partnerId());
        final var category = categoryService.getByName(dto.category());

        final PartnerBonus partnerBonus = PartnerBonus.builder()
                .id(bonusId)
                .partner(partner)
                .category(category)
                .description(dto.description())
                .createdAt(now)
				.active(DEFAULT_CREATE_ACTIVE)
                .updatedAt(now)
                .build();

        final PartnerBonus savedBonus = partnerBonusRepository.save(partnerBonus);

        return savedBonus;
    }

	public PartnerBonus update(UUID id, PartnerBonusUpdateDTO dto) {
		PartnerBonus partnerBonus = getById(id);

		LocalDateTime now = LocalDateTime.now();

		if(dto.description() != null) {
			partnerBonus.setDescription(dto.description());
		}

		if(dto.active() != null) {
			partnerBonus.setActive(dto.active());
		}

		if(dto.active() != null || dto.description() != null) {
			partnerBonus.setUpdatedAt(now);
		}

		PartnerBonus updatedBonus = partnerBonusRepository.save(partnerBonus);

		return updatedBonus;
	}

	@Transactional(readOnly = true)
	public List<PartnerBonus> getActual() {
		return partnerBonusRepository.findActual();
	}


    public void delete(UUID id) {
        final PartnerBonus partnerBonus = this.getById(id);

        partnerBonusRepository.delete(partnerBonus);
    }
}

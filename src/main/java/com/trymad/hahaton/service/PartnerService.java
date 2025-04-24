package com.trymad.hahaton.service;

import com.trymad.hahaton.entity.Partner;
import com.trymad.hahaton.repository.PartnerRepository;
import com.trymad.hahaton.web.dto.create.PartnerCreateDTO;
import com.trymad.hahaton.web.dto.update.PartnerUpdateDTO;

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
public class PartnerService {

	private final static boolean DEFAULT_CREATE_ACTIVE = true;

    private final PartnerRepository partnerRepository;

    @Transactional(readOnly = true)
    public Partner getById(UUID id) {
        return partnerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Partner not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Partner getFetchById(UUID id) {
        return partnerRepository.findFetchById(id)
                .orElseThrow(() -> new EntityNotFoundException("Partner not found with id: " + id));
    }

	@Transactional(readOnly = true)
	public List<Partner> getAll() {
		return partnerRepository.findAll();
	}

    public Partner create(PartnerCreateDTO dto) {
        final LocalDateTime now = LocalDateTime.now();

        final Partner partner = Partner.builder()
                .id(UUID.randomUUID())
                .name(dto.name())
                .type(dto.type())
                .inn(dto.inn())
                .mail(dto.mail())
                .active(DEFAULT_CREATE_ACTIVE)
                .createdAt(now)
                .updatedAt(now)
                .build();

        return partnerRepository.save(partner);
    }

    public Partner update(UUID id, PartnerUpdateDTO dto) {
        final Partner partner = this.getById(id);

        partner.setName(dto.name());
        partner.setType(dto.type());
        partner.setInn(dto.inn());
        partner.setMail(dto.mail());
        partner.setUpdatedAt(LocalDateTime.now());

        return partnerRepository.save(partner);
    }

    public void delete(UUID id) {
        final Partner partner = this.getById(id);

        partnerRepository.delete(partner);
    }
}

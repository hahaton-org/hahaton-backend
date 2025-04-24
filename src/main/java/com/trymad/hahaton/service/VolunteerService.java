package com.trymad.hahaton.service;

import com.trymad.hahaton.entity.Volunteer;
import com.trymad.hahaton.repository.VolunteerRepository;
import com.trymad.hahaton.web.dto.create.VolunteerCreateDTO;
import com.trymad.hahaton.web.dto.update.VolunteerUpdateDTO;

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
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

	private final static boolean DEFAULT_CREATE_ACTIVE = true;

    @Transactional(readOnly = true)
    public Volunteer getById(UUID id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Volunteer not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Volunteer getFetchById(UUID id) {
        return volunteerRepository.findFetchById(id)
                .orElseThrow(() -> new EntityNotFoundException("Volunteer not found with id: " + id));
    }

	@Transactional(readOnly = true)
	public List<Volunteer> getAll() {
		return volunteerRepository.findAll();
	}

    public Volunteer create(VolunteerCreateDTO dto) {
        final LocalDateTime now = LocalDateTime.now();

        final Volunteer volunteer = Volunteer.builder()
                .id(UUID.randomUUID())
                .inn(dto.inn())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .middleName(dto.middleName())
                .phone(dto.phone())
                .mail(dto.mail())
                .birthdayDate(dto.birthdayDate())
                .active(DEFAULT_CREATE_ACTIVE)
                .createdAt(now)
                .updatedAt(now)
                .build();

        return volunteerRepository.save(volunteer);
    }

    public Volunteer update(UUID id, VolunteerUpdateDTO dto) {
        final Volunteer volunteer = this.getById(id);

        volunteer.setInn(dto.inn());
        volunteer.setFirstName(dto.firstName());
        volunteer.setLastName(dto.lastName());
        volunteer.setMiddleName(dto.middleName());
        volunteer.setPhone(dto.phone());
        volunteer.setMail(dto.mail());
        volunteer.setBirthdayDate(dto.birthdayDate());
        volunteer.setUpdatedAt(LocalDateTime.now());

        return volunteerRepository.save(volunteer);
    }

    public void delete(UUID id) {
        final Volunteer volunteer = this.getById(id);

        volunteerRepository.delete(volunteer);
    }
}

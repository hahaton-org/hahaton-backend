package com.trymad.hahaton.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvValidationException;
import com.trymad.hahaton.config.security.AuthenticationService;
import com.trymad.hahaton.entity.Achievement;
import com.trymad.hahaton.entity.CategoryType;
import com.trymad.hahaton.entity.Volunteer;
import com.trymad.hahaton.web.dto.CsvDTO;
import com.trymad.hahaton.web.dto.LoginDTO;
import com.trymad.hahaton.web.dto.create.AchievementCreateDTO;
import com.trymad.hahaton.web.dto.create.BonusCreateDTO;
import com.trymad.hahaton.web.dto.create.VolunteerCreateDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final CsvParser parser;
	private final VolunteerService volunteerService;
	private final AchievementService achievementService;
	private final BonusService bonusService;
	private final AuthenticationService authenticationService;

	public void updateBonuses(MultipartFile csv) throws CsvValidationException, IllegalStateException, IOException {
		final Map<Integer, CsvDTO> csvList = parser.parse(csv);
		bonusService.deactivateBonuses();

		final Map<Integer, Volunteer> volunteers = new HashMap<>();
		final Map<Integer, AchievementCreateDTO> achievementsDto = new HashMap<>();
		final List<BonusCreateDTO> bonuses = new ArrayList<>();
		csvList.forEach( (key, csvDto) -> {
			final Volunteer volunteer = registry(csvDto);
			volunteers.put(key, volunteer);
		});

		int counter = 1;
		csvList.forEach( (key, csvDto) -> {
			final AchievementCreateDTO achievement = addAchievement(csvDto, volunteers.get(counter));
			achievementsDto.put(key, achievement);
		});

		final Map<Integer, Achievement> ach = achievementService.createAll(achievementsDto);
		ach.forEach( (key, value) -> {
			CategoryType cagetory;
			if(key <= 50) cagetory = CategoryType.MAXIMUM;
			else if(key <= 100) cagetory = CategoryType.MEDIUM;
			else cagetory = CategoryType.MINIMUM;
			final BonusCreateDTO bonus = createBonus(cagetory, value);
			bonuses.add(bonus);
		});

		bonusService.createAll(bonuses);



	}

	private Volunteer registry(CsvDTO csvDto) {
		if(volunteerService.existsByMail(csvDto.getMail())) {
			return volunteerService.getByMail(csvDto.getMail());
		}

		authenticationService.registryVolunteer(new LoginDTO(csvDto.getMail(), "12345"));


		String[] fio = csvDto.getFio().split(" ");
		VolunteerCreateDTO createDto = new VolunteerCreateDTO(
			csvDto.getInn(), 
			csvDto.getPhone(),
			fio[1], 
			fio[0], 
			fio[2], 
			csvDto.getMail(),
			csvDto.getBirthday());
		
		return volunteerService.create(createDto);
	}

	private AchievementCreateDTO addAchievement(CsvDTO csvDTO, Volunteer volunteer) {
		return new AchievementCreateDTO(volunteer.getId(), csvDTO.getAchievements());
	}

	private BonusCreateDTO createBonus(CategoryType type, Achievement achievement) {
		return new BonusCreateDTO(achievement.getId(), type);
	}

}

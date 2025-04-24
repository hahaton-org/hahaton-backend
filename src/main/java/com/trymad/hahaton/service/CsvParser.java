package com.trymad.hahaton.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.trymad.hahaton.web.dto.CsvDTO;

@Service
public class CsvParser {
	
	public Map<Integer, CsvDTO> parse(MultipartFile file) throws IllegalStateException, IOException, CsvValidationException {
        Map<Integer, CsvDTO> csvList = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextLine;
			reader.readNext();

			int counter = 1;
            while ((nextLine = reader.readNext()) != null) {
				if(nextLine.length <= 1) break;
                CsvDTO csvDTO = new CsvDTO();
				csvDTO.setFio(nextLine[0]);
				csvDTO.setInn(nextLine[1]);
				csvDTO.setPhone(nextLine[2]);
				csvDTO.setMail(nextLine[3]);
				csvDTO.setBirthday(LocalDate.parse(nextLine[4], formatter));
				csvDTO.setAchievements(nextLine[5]);

				csvList.put(counter++, csvDTO);
            }
        }

		return csvList;
	}
}

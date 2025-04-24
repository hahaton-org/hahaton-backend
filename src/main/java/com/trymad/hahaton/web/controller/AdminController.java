package com.trymad.hahaton.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvValidationException;
import com.trymad.hahaton.service.CsvParser;
import com.trymad.hahaton.web.dto.CsvDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

	private final CsvParser csvParser;

	@PostMapping("/csvload")
	public List<CsvDTO> uploadCsv(@RequestParam MultipartFile file) throws CsvValidationException, IllegalStateException, IOException  {
    	return csvParser.parse(file);
	}
}

package com.trymad.hahaton.web.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvValidationException;
import com.trymad.hahaton.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	@PostMapping("/csvload")
	public void uploadCsv(@RequestParam MultipartFile file) throws CsvValidationException, IllegalStateException, IOException  {
    	adminService.updateBonuses(file);
	}
}

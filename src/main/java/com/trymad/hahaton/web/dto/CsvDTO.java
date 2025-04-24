package com.trymad.hahaton.web.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CsvDTO {
	
	String fio;
 
	String inn;

	String phone;

	String mail;

	LocalDate birthday;
	
	String achievements;
}

package com.trymad.hahaton.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.hahaton.config.security.AuthenticationService;
import com.trymad.hahaton.web.dto.JwtResponse;
import com.trymad.hahaton.web.dto.LoginDTO;
import com.trymad.hahaton.web.dto.create.PartnerCreateDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

	private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginDTO dto) {
		return new JwtResponse(authenticationService.createJwtToken(dto));
    }

	@PostMapping("/registry/partner")
	public JwtResponse registry(@RequestBody PartnerCreateDTO createDto) {
		return new JwtResponse(authenticationService.registryPartner(createDto));
	}

}

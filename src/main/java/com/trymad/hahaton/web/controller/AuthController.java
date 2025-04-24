package com.trymad.hahaton.web.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.hahaton.config.security.AuthenticationService;
import com.trymad.hahaton.config.security.JwtTokenProvider;
import com.trymad.hahaton.config.service.MyUserDetailService;
import com.trymad.hahaton.web.dto.JwtResponse;
import com.trymad.hahaton.web.dto.LoginDTO;
import com.trymad.hahaton.web.dto.create.PartnerCreateDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

	private final AuthenticationManager authManager;
    private final MyUserDetailService userDetailsService;
    private final JwtTokenProvider jwtService;
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

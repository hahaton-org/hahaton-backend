package com.trymad.hahaton.config.security;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trymad.hahaton.config.entity.MyRole;
import com.trymad.hahaton.config.entity.MyUser;
import com.trymad.hahaton.config.entity.MyUserDetails;
import com.trymad.hahaton.config.service.RoleRepository;
import com.trymad.hahaton.config.service.UserRepository;
import com.trymad.hahaton.entity.Partner;
import com.trymad.hahaton.entity.Volunteer;
import com.trymad.hahaton.service.PartnerService;
import com.trymad.hahaton.service.VolunteerService;
import com.trymad.hahaton.web.dto.LoginDTO;
import com.trymad.hahaton.web.dto.create.PartnerCreateDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authManager;
	private final UserRepository userRepository;
	private final PartnerService partnerService;
	private final RoleRepository roleRepository;
	private final VolunteerService volunteerService;

    public String createJwtToken(LoginDTO loginDTO) {
        final MyUser user = userRepository.findByMail(loginDTO.mail()).orElseThrow( () -> new RuntimeException());
		if(!user.getPassword().equals(loginDTO.password())) {
			throw new RuntimeException("incorrect password");
		}

        return jwtTokenProvider.generateToken(new MyUserDetails(user));
    }

    public Authentication authenticate(String token) {
        final Authentication authentication = new UsernamePasswordAuthenticationToken(
                jwtTokenProvider.getMail(token),
                null,
                jwtTokenProvider.getRoles(token).stream().map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toSet()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public String registryPartner(PartnerCreateDTO userCreateDTO) {
		Partner partner = partnerService.create(userCreateDTO);
		final LoginDTO dto = new LoginDTO(userCreateDTO.mail(), userCreateDTO.password());
		final MyUser user = registryUser(dto, partner.getId(), MyRole.ROLE_PARTNER);
        return jwtTokenProvider.generateToken(new MyUserDetails(user));
    }

    public String registryVolunteer(LoginDTO dto, UUID id) {
		final MyUser user = registryUser(dto, id, MyRole.ROLE_VOLUNTEER);
        return jwtTokenProvider.generateToken(new MyUserDetails(user));
    }

	private MyUser registryUser(LoginDTO loginDTO, UUID id, MyRole role) {
		final MyUser user = MyUser.builder()
		.mail(loginDTO.mail())
		.password(loginDTO.password())
		.roles(Set.of(roleRepository.findByName(role).get()))
		.id(id).build();
		
		return userRepository.save(user);
	}

    public String refreshTokenFromHeader(String authHeader) {
        return jwtTokenProvider.refresh(authHeader.substring(7));
    }
}
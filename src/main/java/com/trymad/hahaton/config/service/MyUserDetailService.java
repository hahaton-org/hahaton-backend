package com.trymad.hahaton.config.service;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.trymad.hahaton.config.entity.MyUser;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        MyUser user = userRepository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getMail())
                .password(user.getPassword())
                .roles(new HashSet<>(user.getRoles()).stream().map(r -> r.getName().name()).toArray(String[]::new))
                .build();
    }
}

package com.trymad.hahaton.config.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {

    private final MyUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(user.getRoles()).stream()
                .map(Role::getName)
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getMail();
    }

    public UUID getId() {
        return user.getId();
    }

}
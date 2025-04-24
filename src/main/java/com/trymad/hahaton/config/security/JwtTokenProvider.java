package com.trymad.hahaton.config.security;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.trymad.hahaton.config.entity.MyRole;
import com.trymad.hahaton.config.entity.MyUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private final Duration expiration;
    private final SecretKey secret;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Duration expiration) {
        this.expiration = expiration;
        this.secret = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public String generateToken(UserDetails userDetails) {
        MyUserDetails user = (MyUserDetails) userDetails;
        final Map<String, Object> claims = new HashMap<>();
        final List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roles);
        claims.put("id", user.getId());

        final Date now = new Date();
        final Date expiredDate = new Date(now.getTime() + expiration.toMillis());
        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(expiredDate)
                .signWith(secret)
                .compact();
    }

    public String refresh(String token) {
        final Claims claims = this.getClaims(token);
        final String subject = this.getMail(token);

        final Date now = new Date();
        final Date expiredDate = new Date(now.getTime() + expiration.toMillis());
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiredDate)
                .signWith(secret)
                .compact();
    }

    public String getMail(String token) {
        return this.getClaims(token).getSubject();
    }

    public Set<MyRole> getRoles(String token) {
        @SuppressWarnings("unchecked")
        List<String> roles = this.getClaims(token).get("roles", List.class);
        return roles.stream().map(MyRole::valueOf).collect(Collectors.toSet());
    }

    public boolean isExpired(String token) {
        return this.getExpirationDate(token).after(new Date());
    }

    public Date getExpirationDate(String token) {
        return this.getClaims(token).getExpiration();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
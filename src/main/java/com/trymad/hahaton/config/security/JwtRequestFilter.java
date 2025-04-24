package com.trymad.hahaton.config.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final AuthenticationService authService;

    private final RequestMatcher publicPaths = new AntPathRequestMatcher("/api/auth/**");
    private final RequestMatcher doc = new AntPathRequestMatcher("/api/doc/**");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {
        if (publicPaths.matches(request) || doc.matches(request)) {
            doFilter(request, response, filterChain);
            return;
        }
         
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authService.authenticate(authHeader.substring(7));
        }

        filterChain.doFilter(request, response);
    }

}
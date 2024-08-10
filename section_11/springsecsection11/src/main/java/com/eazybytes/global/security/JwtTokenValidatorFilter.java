package com.eazybytes.global.security;

import com.eazybytes.domain.auth.application.JwtTokenService;
import com.eazybytes.domain.auth.dto.AccessTokenDto;
import com.eazybytes.global.common.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessTokenValue = request.getHeader(SecurityConstants.ACCESS_TOKEN_HEADER);

        if (accessTokenValue != null) {
            AccessTokenDto accessTokenDto = jwtTokenService.retrieveAccessToken(accessTokenValue);
            if (accessTokenDto != null) {
                try {
                    jwtTokenService.setAuthenticationToken(accessTokenDto.customerEmail(), accessTokenDto.role());
                } catch (Exception e) {
                    throw new BadCredentialsException("Invalid Token received");
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/user");
    }
}

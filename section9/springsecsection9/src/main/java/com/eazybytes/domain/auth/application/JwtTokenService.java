package com.eazybytes.domain.auth.application;

import com.eazybytes.domain.auth.dto.AccessTokenDto;
import com.eazybytes.domain.customer.domain.CustomerRole;
import com.eazybytes.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final JwtUtil jwtUtil;

    public String createAccessToken(String customerEmail, CustomerRole role) {
        return jwtUtil.generateAccessToken(customerEmail, role);
    }

    public AccessTokenDto retrieveAccessToken(String accessTokenValue) {
        try {
            return jwtUtil.parseAccessToken(accessTokenValue);
        } catch (Exception e) {
            return null;
        }
    }

    public void setAuthenticationToken(String customerEmail, CustomerRole role) {
        UserDetails userDetails =
                User.withUsername(customerEmail)
                        .password("")
                        .authorities(role.toString())
                        .build();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
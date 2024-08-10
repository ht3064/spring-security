package com.eazybytes.global.security;

import com.eazybytes.domain.auth.application.JwtTokenService;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.global.util.CustomerUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.eazybytes.global.common.constants.SecurityConstants.ACCESS_TOKEN_HEADER;

@Component
@RequiredArgsConstructor
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final CustomerUtil customerUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Customer customer = customerUtil.getCurrentCustomer();
        String token = jwtTokenService.createAccessToken(customer.getEmail(), customer.getRole());
        response.setHeader(ACCESS_TOKEN_HEADER, token);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/user");
    }
}
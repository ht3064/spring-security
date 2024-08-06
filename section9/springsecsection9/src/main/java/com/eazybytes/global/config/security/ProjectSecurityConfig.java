package com.eazybytes.global.config.security;

import com.eazybytes.domain.auth.application.JwtTokenService;
import com.eazybytes.global.security.*;
import com.eazybytes.global.util.CustomerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ProjectSecurityConfig {

    private final JwtTokenService jwtTokenService;
    private final CustomerUtil customerUtil;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();

        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .csrf(
                        csrf -> csrf
                                .csrfTokenRequestHandler(requestHandler)
                                .ignoringRequestMatchers("/contact", "/register")
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(jwtTokenGeneratorFilter(jwtTokenService, customerUtil), BasicAuthenticationFilter.class)
                .addFilterBefore(jwtTokenValidatorFilter(jwtTokenService), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(
                        requests -> requests
                                .requestMatchers("/myAccount", "/myBalance", "myLoans", "myCards", "/user")
                                .authenticated()
                                .requestMatchers("/notices", "/contact", "/register")
                                .permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        config.setExposedHeaders(List.of("Authorization"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public JwtTokenGeneratorFilter jwtTokenGeneratorFilter(
            JwtTokenService jwtTokenService, CustomerUtil customerUtil) {
        return new JwtTokenGeneratorFilter(jwtTokenService, customerUtil);
    }

    @Bean
    public JwtTokenValidatorFilter jwtTokenValidatorFilter(
            JwtTokenService jwtTokenService) {
        return new JwtTokenValidatorFilter(jwtTokenService);
    }
}
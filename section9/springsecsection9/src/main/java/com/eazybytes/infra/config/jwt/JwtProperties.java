package com.eazybytes.infra.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String accessTokenSecret,
        Long accessTokenExpirationTime,
        String issuer) {
    public Long accessTokenExpirationMilliTime() {
        return accessTokenExpirationTime * 1000;
    }
}
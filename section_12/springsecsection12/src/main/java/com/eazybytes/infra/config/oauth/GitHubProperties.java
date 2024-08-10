package com.eazybytes.infra.config.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.github.client")
public record GitHubProperties(
        String id,
        String secret) {
}
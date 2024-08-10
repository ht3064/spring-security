package com.eazybytes.infra.config.properties;

import com.eazybytes.infra.config.oauth.GitHubProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({
        GitHubProperties.class
})
@Configuration
public class PropertiesConfig {
}
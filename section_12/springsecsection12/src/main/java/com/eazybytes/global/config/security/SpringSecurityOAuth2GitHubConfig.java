package com.eazybytes.global.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@RequiredArgsConstructor
public class SpringSecurityOAuth2GitHubConfig {

//    private final GitHubProperties gitHubProperties;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        requests -> requests
                                .anyRequest()
                                .authenticated())
                .oauth2Login(Customizer.withDefaults());

        return http.build();
    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        ClientRegistration clientRegistration = clientRegistration();
//        return new InMemoryClientRegistrationRepository(clientRegistration);
//    }
//
//    private ClientRegistration clientRegistration() {
//        return CommonOAuth2Provider.GITHUB
//                .getBuilder("github")
//                .clientId(gitHubProperties.id())
//                .clientSecret(gitHubProperties.secret())
//                .build();
//    }
}
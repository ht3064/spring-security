package com.eazybytes.global.security;

import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.domain.customer.domain.CustomerRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class EazyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Customer customer = customerRepository
                .findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("No user registered with this details"));

        if (passwordEncoder.matches(pwd, customer.getPwd())) {
            return new UsernamePasswordAuthenticationToken(
                    username, pwd, getAuthorities(customer.getRole()));
        } else {
            throw new BadCredentialsException("Invalid password!");
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(CustomerRole role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

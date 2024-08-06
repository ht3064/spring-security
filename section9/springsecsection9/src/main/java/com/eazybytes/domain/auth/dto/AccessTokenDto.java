package com.eazybytes.domain.auth.dto;

import com.eazybytes.domain.customer.domain.CustomerRole;

public record AccessTokenDto(String customerEmail, CustomerRole role, String tokenValue) {
}

package com.eazybytes.domain.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerRole {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    ;

    private final String value;
}

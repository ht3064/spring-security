package com.eazybytes.domain.customer.dto.request;

public record RegisterCustomerRequest(
        String name, String email, String mobileNumber, String pwd, String role) {
}

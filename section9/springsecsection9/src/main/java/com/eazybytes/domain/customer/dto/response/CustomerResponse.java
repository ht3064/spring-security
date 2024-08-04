package com.eazybytes.domain.customer.dto.response;

import com.eazybytes.domain.customer.domain.Customer;

public record CustomerResponse(
        Long id,
        String name,
        String email,
        String mobileNumber,
        String role) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                customer.getRole());
    }
}
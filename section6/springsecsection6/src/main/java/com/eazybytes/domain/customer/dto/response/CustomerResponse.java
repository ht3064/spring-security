package com.eazybytes.domain.customer.dto.response;

import com.eazybytes.domain.customer.domain.Customer;

public record CustomerResponse(
        String name, String email, String mobileNumber,
        String pwd, String role) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                customer.getPwd(),
                customer.getRole());
    }
}
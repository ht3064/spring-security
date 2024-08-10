package com.eazybytes.global.util;

import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.global.error.exception.CustomException;
import com.eazybytes.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerUtil {

    private final SecurityUtil securityUtil;
    private final CustomerRepository customerRepository;

    public Customer getCurrentCustomer() {
        return customerRepository
                .findByEmail(securityUtil.getCurrentCustomer())
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));
    }
}
package com.eazybytes.domain.account.application;

import com.eazybytes.domain.account.dao.AccountRepository;
import com.eazybytes.domain.account.domain.Account;
import com.eazybytes.domain.account.dto.response.AccountResponse;
import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.global.error.exception.CustomException;
import com.eazybytes.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class AccountService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public AccountResponse getAccountDetails(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));

        Account account = accountRepository.findByCustomer(customer)
                .orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND));

        return AccountResponse.from(account);
    }
}
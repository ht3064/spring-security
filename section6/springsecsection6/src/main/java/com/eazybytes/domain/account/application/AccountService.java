package com.eazybytes.domain.account.application;

import com.eazybytes.domain.account.dao.AccountRepository;
import com.eazybytes.domain.account.domain.Account;
import com.eazybytes.domain.account.dto.response.AccountResponse;
import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class AccountService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public AccountResponse getAccountDetails(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));

        Account account = accountRepository.findByCustomer(customer)
                .orElseThrow(() -> null);

        return AccountResponse.from(account);
    }
}
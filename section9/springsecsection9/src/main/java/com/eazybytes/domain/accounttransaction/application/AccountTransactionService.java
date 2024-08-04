package com.eazybytes.domain.accounttransaction.application;

import com.eazybytes.domain.accounttransaction.dao.AccountTransactionRepository;
import com.eazybytes.domain.accounttransaction.domain.AccountTransaction;
import com.eazybytes.domain.accounttransaction.dto.response.AccountTransactionResponse;
import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.global.error.exception.CustomException;
import com.eazybytes.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final CustomerRepository customerRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    public List<AccountTransactionResponse> getBalanceDetails(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));

        List<AccountTransaction> accountTransactions =
                accountTransactionRepository
                        .findByCustomerOrderByTransactionDtDesc(customer);

        return accountTransactions.stream()
                .map(AccountTransactionResponse::from)
                .toList();
    }
}
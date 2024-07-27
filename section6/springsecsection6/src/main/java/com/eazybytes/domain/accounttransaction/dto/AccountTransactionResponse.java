package com.eazybytes.domain.accounttransaction.dto;

import com.eazybytes.domain.account.domain.Account;
import com.eazybytes.domain.accounttransaction.domain.AccountTransaction;
import com.eazybytes.domain.customer.domain.Customer;

import java.time.LocalDateTime;

public record AccountTransactionResponse(
        Account account, Customer customer, LocalDateTime transactionDt,
        String transactionSummary, String transactionType, int transactionAmt,
        int closingBalance, LocalDateTime createdDt) {
    public static AccountTransactionResponse from(AccountTransaction accountTransaction) {
        return new AccountTransactionResponse(
                accountTransaction.getAccount(),
                accountTransaction.getCustomer(),
                accountTransaction.getTransactionDt(),
                accountTransaction.getTransactionSummary(),
                accountTransaction.getTransactionType(),
                accountTransaction.getTransactionAmt(),
                accountTransaction.getClosingBalance(),
                accountTransaction.getCreatedDt());
    }
}

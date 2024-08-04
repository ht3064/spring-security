package com.eazybytes.domain.accounttransaction.dto.response;

import com.eazybytes.domain.accounttransaction.domain.AccountTransaction;

import java.time.LocalDateTime;

public record AccountTransactionResponse(
        LocalDateTime transactionDt,
        String transactionSummary,
        String transactionType,
        int transactionAmt,
        int closingBalance) {
    public static AccountTransactionResponse from(AccountTransaction accountTransaction) {
        return new AccountTransactionResponse(
                accountTransaction.getTransactionDt(),
                accountTransaction.getTransactionSummary(),
                accountTransaction.getTransactionType(),
                accountTransaction.getTransactionAmt(),
                accountTransaction.getClosingBalance());
    }
}

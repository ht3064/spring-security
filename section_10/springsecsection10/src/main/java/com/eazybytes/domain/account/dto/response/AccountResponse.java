package com.eazybytes.domain.account.dto.response;

import com.eazybytes.domain.account.domain.Account;

public record AccountResponse(
        Long accountNumber,
        String accountType,
        String branchAddress) {
    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBranchAddress());
    }
}
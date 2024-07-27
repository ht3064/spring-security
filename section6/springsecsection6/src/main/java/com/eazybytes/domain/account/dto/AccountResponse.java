package com.eazybytes.domain.account.dto;

import com.eazybytes.domain.account.domain.Account;
import com.eazybytes.domain.customer.domain.Customer;

public record AccountResponse(Customer customer, String accountType, String branchAddress) {
    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getCustomer(), account.getAccountType(), account.getBranchAddress());
    }
}
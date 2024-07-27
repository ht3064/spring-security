package com.eazybytes.domain.loan.dto;

import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.domain.loan.domain.Loan;

import java.time.LocalDateTime;

public record LoanResponse(
        Customer customer, LocalDateTime startDt, String loanType,
        int totalLoan, int amountPaid, int outstandingAmount, LocalDateTime createdDt) {
    public static LoanResponse from(Loan loan) {
        return new LoanResponse(
                loan.getCustomer(),
                loan.getStartDt(),
                loan.getLoanType(),
                loan.getTotalLoan(),
                loan.getAmountPaid(),
                loan.getOutstandingAmount(),
                loan.getCreatedDt());
    }
}

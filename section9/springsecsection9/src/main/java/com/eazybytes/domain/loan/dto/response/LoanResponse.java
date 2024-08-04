package com.eazybytes.domain.loan.dto.response;

import com.eazybytes.domain.loan.domain.Loan;

import java.time.LocalDateTime;

public record LoanResponse(
        LocalDateTime startDt,
        String loanType,
        int totalLoan,
        int amountPaid,
        int outstandingAmount) {
    public static LoanResponse from(Loan loan) {
        return new LoanResponse(
                loan.getStartDt(),
                loan.getLoanType(),
                loan.getTotalLoan(),
                loan.getAmountPaid(),
                loan.getOutstandingAmount());
    }
}
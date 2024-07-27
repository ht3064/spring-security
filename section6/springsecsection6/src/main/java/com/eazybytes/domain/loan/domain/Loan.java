package com.eazybytes.domain.loan.domain;

import com.eazybytes.domain.common.model.BaseTimeEntity;
import com.eazybytes.domain.customer.domain.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_number")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime startDt;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

    private LocalDateTime createdDt;

    @Builder(access = AccessLevel.PRIVATE)
    private Loan(
            Customer customer, LocalDateTime startDt, String loanType,
            int totalLoan, int amountPaid, int outstandingAmount, LocalDateTime createdDt) {
        this.customer = customer;
        this.startDt = startDt;
        this.loanType = loanType;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmount = outstandingAmount;
        this.createdDt = createdDt;
    }

    public static Loan createLoan(
            Customer customer, LocalDateTime startDt, String loanType,
            int totalLoan, int amountPaid, int outstandingAmount, LocalDateTime createdDt) {
        return Loan.builder()
                .customer(customer)
                .startDt(startDt)
                .loanType(loanType)
                .totalLoan(totalLoan)
                .amountPaid(amountPaid)
                .outstandingAmount(outstandingAmount)
                .createdDt(createdDt)
                .build();
    }
}
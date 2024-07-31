package com.eazybytes.domain.accounttransaction.domain;

import com.eazybytes.domain.account.domain.Account;
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
public class AccountTransaction {

    @Id
    @Column(name = "transaction_id")
    private String id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_number")
    private Account account;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime transactionDt;

    private String transactionSummary;

    private String transactionType;

    private int transactionAmt;

    private int closingBalance;

    private LocalDateTime createDt;

    @Builder(access = AccessLevel.PRIVATE)
    private AccountTransaction(
            String id,
            Account account,
            Customer customer,
            LocalDateTime transactionDt,
            String transactionSummary,
            String transactionType,
            int transactionAmt,
            int closingBalance,
            LocalDateTime createDt) {
        this.id = id;
        this.account = account;
        this.customer = customer;
        this.transactionDt = transactionDt;
        this.transactionSummary = transactionSummary;
        this.transactionType = transactionType;
        this.transactionAmt = transactionAmt;
        this.closingBalance = closingBalance;
        this.createDt = createDt;
    }

    public static AccountTransaction createAccountTransaction(
            String id,
            Account account,
            Customer customer,
            LocalDateTime transactionDt,
            String transactionSummary,
            String transactionType,
            int transactionAmt,
            int closingBalance,
            LocalDateTime createDt) {
        return AccountTransaction.builder()
                .id(id)
                .account(account)
                .customer(customer)
                .transactionDt(transactionDt)
                .transactionSummary(transactionSummary)
                .transactionType(transactionType)
                .transactionAmt(transactionAmt)
                .closingBalance(closingBalance)
                .createDt(createDt)
                .build();
    }
}
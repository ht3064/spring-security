package com.eazybytes.domain.account.domain;

import com.eazybytes.domain.customer.domain.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    private Long accountNumber;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String accountType;

    private String branchAddress;

    private LocalDateTime createDt;

    @Builder(access = AccessLevel.PRIVATE)
    private Account(
            Long accountNumber,
            Customer customer,
            String accountType,
            String branchAddress,
            LocalDateTime createDt) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
        this.createDt = createDt;
    }

    public static Account createAccount(
            Long accountNumber,
            Customer customer,
            String accountType,
            String branchAddress) {
        return Account.builder()
                .accountNumber(accountNumber)
                .customer(customer)
                .accountType(accountType)
                .branchAddress(branchAddress)
                .createDt(LocalDateTime.now())
                .build();
    }
}
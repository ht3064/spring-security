package com.eazybytes.domain.account.domain;

import com.eazybytes.domain.common.model.BaseTimeEntity;
import com.eazybytes.domain.customer.domain.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity {

    @Id
    @Column(name = "account_number")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String accountType;

    private String branchAddress;

    @Builder(access = AccessLevel.PRIVATE)
    private Account(Long id, Customer customer, String accountType, String branchAddress) {
        this.id = id;
        this.customer = customer;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }

    public static Account createAccount(
            Long id, Customer customer, String accountType, String branchAddress) {
        return Account.builder()
                .id(id)
                .customer(customer)
                .accountType(accountType)
                .branchAddress(branchAddress)
                .build();
    }
}
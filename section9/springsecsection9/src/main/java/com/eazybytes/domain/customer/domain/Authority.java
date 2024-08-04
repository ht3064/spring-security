package com.eazybytes.domain.customer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String name;

    @Builder(access = AccessLevel.PRIVATE)
    private Authority(Customer customer, String name) {
        this.customer = customer;
        this.name = name;
    }

    public static Authority createAuthority(
            Customer customer, String name) {
        return Authority.builder()
                .customer(customer)
                .name(name)
                .build();
    }
}
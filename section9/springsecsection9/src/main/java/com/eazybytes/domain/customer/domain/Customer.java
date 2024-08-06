package com.eazybytes.domain.customer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    private String email;

    private String mobileNumber;

    private String pwd;

    private CustomerRole role;

    private LocalDateTime createDt;

    @Builder(access = AccessLevel.PRIVATE)
    private Customer(
            String name,
            String email,
            String mobileNumber,
            String pwd,
            CustomerRole role,
            LocalDateTime createDt) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.pwd = pwd;
        this.role = role;
        this.createDt = createDt;
    }

    public static Customer createCustomer(
            String name,
            String email,
            String mobileNumber,
            String pwd,
            CustomerRole role) {
        return Customer.builder()
                .name(name)
                .email(email)
                .mobileNumber(mobileNumber)
                .pwd(pwd)
                .role(role)
                .createDt(LocalDateTime.now())
                .build();
    }
}
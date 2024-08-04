package com.eazybytes.domain.customer.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

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

    private String role;

    private LocalDateTime createDt;

    @OneToMany(mappedBy = "customer", fetch = EAGER)
    private List<Authority> authorities = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private Customer(
            String name,
            String email,
            String mobileNumber,
            String pwd,
            String role,
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
            String role) {
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
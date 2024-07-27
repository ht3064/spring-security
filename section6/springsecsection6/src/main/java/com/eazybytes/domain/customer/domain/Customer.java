package com.eazybytes.domain.customer.domain;

import com.eazybytes.domain.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String name;

    private String email;

    private String mobileNumber;

    private String pwd;

    private String role;

    @Builder(access = AccessLevel.PRIVATE)
    private Customer(
            String name, String email, String mobileNumber,
            String pwd, String role) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.pwd = pwd;
        this.role = role;
    }

    public static Customer createCustomer(
            String name, String email, String mobileNumber,
            String pwd, String role) {
        return Customer.builder()
                .name(name)
                .email(email)
                .mobileNumber(mobileNumber)
                .pwd(pwd)
                .role(role)
                .build();
    }
}
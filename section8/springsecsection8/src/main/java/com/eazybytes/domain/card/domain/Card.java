package com.eazybytes.domain.card.domain;

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
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    private String cardNumber;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String cardType;

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;

    private LocalDateTime createDt;

    @Builder(access = AccessLevel.PRIVATE)
    private Card(
            String cardNumber,
            Customer customer,
            String cardType,
            int totalLimit,
            int amountUsed,
            int availableAmount,
            LocalDateTime createDt) {
        this.cardNumber = cardNumber;
        this.customer = customer;
        this.cardType = cardType;
        this.totalLimit = totalLimit;
        this.amountUsed = amountUsed;
        this.availableAmount = availableAmount;
        this.createDt = createDt;
    }

    public static Card createCard(
            String cardNumber,
            Customer customer,
            String cardType,
            int totalLimit,
            int amountUsed,
            int availableAmount) {
        return Card.builder()
                .cardNumber(cardNumber)
                .customer(customer)
                .cardType(cardType)
                .totalLimit(totalLimit)
                .amountUsed(amountUsed)
                .availableAmount(availableAmount)
                .createDt(LocalDateTime.now())
                .build();
    }
}

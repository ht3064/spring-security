package com.eazybytes.domain.card.dto;

import com.eazybytes.domain.card.domain.Card;
import com.eazybytes.domain.customer.domain.Customer;

public record CardResponse(
        String cardNumber, Customer customer, String cardType,
        int totalLimit, int amountUsed, int availableAmount) {
    public static CardResponse from(Card card) {
        return new CardResponse(
                card.getCardNumber(),
                card.getCustomer(),
                card.getCardType(),
                card.getTotalLimit(),
                card.getAmountUsed(),
                card.getAvailableAmount());
    }
}
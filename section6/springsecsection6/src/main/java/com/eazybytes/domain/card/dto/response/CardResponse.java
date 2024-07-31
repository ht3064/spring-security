package com.eazybytes.domain.card.dto.response;

import com.eazybytes.domain.card.domain.Card;

public record CardResponse(
        String cardNumber,
        String cardType,
        int totalLimit,
        int amountUsed,
        int availableAmount) {
    public static CardResponse from(Card card) {
        return new CardResponse(
                card.getCardNumber(),
                card.getCardType(),
                card.getTotalLimit(),
                card.getAmountUsed(),
                card.getAvailableAmount());
    }
}
package com.eazybytes.domain.card.application;

import com.eazybytes.domain.card.dao.CardRepository;
import com.eazybytes.domain.card.domain.Card;
import com.eazybytes.domain.card.dto.response.CardResponse;
import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.global.error.exception.CustomException;
import com.eazybytes.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CardService {

    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;

    public List<CardResponse> getCardDetails(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));

        List<Card> cards = cardRepository.findByCustomer(customer);

        return cards.stream()
                .map(CardResponse::from)
                .toList();
    }
}

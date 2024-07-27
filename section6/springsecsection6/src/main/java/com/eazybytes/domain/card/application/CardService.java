package com.eazybytes.domain.card.application;

import com.eazybytes.domain.card.dao.CardRepository;
import com.eazybytes.domain.card.domain.Card;
import com.eazybytes.domain.card.dto.CardResponse;
import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class CardService {

    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;

    public List<CardResponse> getCardDetails(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));

        List<Card> cards = cardRepository.findByCustomer(customer);

        return cards.stream()
                .map(CardResponse::from)
                .toList();
    }
}

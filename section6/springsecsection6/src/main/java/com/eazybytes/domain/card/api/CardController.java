package com.eazybytes.domain.card.api;

import com.eazybytes.domain.card.application.CardService;
import com.eazybytes.domain.card.dto.response.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/myCards")
    public List<CardResponse> getCardDetails(@RequestParam Long id) {
        return cardService.getCardDetails(id);
    }
}
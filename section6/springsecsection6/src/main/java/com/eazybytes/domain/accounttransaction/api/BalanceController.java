package com.eazybytes.domain.accounttransaction.api;

import com.eazybytes.domain.accounttransaction.application.AccountTransactionService;
import com.eazybytes.domain.accounttransaction.dto.AccountTransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountTransactionService accountTransactionService;

    @GetMapping("/myBalance")
    public List<AccountTransactionResponse> getBalanceDetails(@RequestParam Long id) {
        return accountTransactionService.getBalanceDetails(id);
    }
}
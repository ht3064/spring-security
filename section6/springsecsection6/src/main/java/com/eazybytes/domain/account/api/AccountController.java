package com.eazybytes.domain.account.api;

import com.eazybytes.domain.account.application.AccountService;
import com.eazybytes.domain.account.dto.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/myAccount")
    public AccountResponse getAccountDetails(@RequestParam Long id) {
        return accountService.getAccountDetails(id);
    }
}
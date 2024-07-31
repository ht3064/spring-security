package com.eazybytes.domain.loan.api;

import com.eazybytes.domain.loan.application.LoanService;
import com.eazybytes.domain.loan.dto.response.LoanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/myLoans")
    public List<LoanResponse> getLoanDetails(@RequestParam Long id) {
        return loanService.getLoanDetails(id);
    }
}

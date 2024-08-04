package com.eazybytes.domain.loan.application;

import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.domain.loan.dao.LoanRepository;
import com.eazybytes.domain.loan.domain.Loan;
import com.eazybytes.domain.loan.dto.response.LoanResponse;
import com.eazybytes.global.error.exception.CustomException;
import com.eazybytes.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LoanService {

    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    public List<LoanResponse> getLoanDetails(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));

        List<Loan> loans = loanRepository.findByCustomerOrderByStartDtDesc(customer);

        return loans.stream()
                .map(LoanResponse::from)
                .toList();
    }
}

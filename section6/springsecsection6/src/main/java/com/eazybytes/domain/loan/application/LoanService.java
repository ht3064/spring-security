package com.eazybytes.domain.loan.application;

import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.domain.loan.dao.LoanRepository;
import com.eazybytes.domain.loan.domain.Loan;
import com.eazybytes.domain.loan.dto.LoanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class LoanService {

    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    public List<LoanResponse> getLoanDetails(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("회원이 존재하지 않습니다."));

        List<Loan> loans = loanRepository.findByCustomerOrderByStartDtDesc(customer);

        return loans.stream()
                .map(LoanResponse::from)
                .toList();
    }
}

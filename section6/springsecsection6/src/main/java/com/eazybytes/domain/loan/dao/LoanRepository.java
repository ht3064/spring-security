package com.eazybytes.domain.loan.dao;

import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.domain.loan.domain.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByCustomerOrderByStartDtDesc(Customer customer);
}
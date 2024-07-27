package com.eazybytes.domain.account.dao;

import com.eazybytes.domain.account.domain.Account;
import com.eazybytes.domain.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByCustomer(Customer customer);
}
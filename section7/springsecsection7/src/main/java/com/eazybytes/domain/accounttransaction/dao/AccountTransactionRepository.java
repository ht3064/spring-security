package com.eazybytes.domain.accounttransaction.dao;

import com.eazybytes.domain.accounttransaction.domain.AccountTransaction;
import com.eazybytes.domain.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, Long> {
    List<AccountTransaction> findByCustomerOrderByTransactionDtDesc(Customer customer);
}

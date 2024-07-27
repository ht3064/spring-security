package com.eazybytes.domain.card.dao;

import com.eazybytes.domain.card.domain.Card;
import com.eazybytes.domain.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByCustomer(Customer customer);
}

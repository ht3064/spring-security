package com.eazybytes.domain.customer.application;

import com.eazybytes.domain.customer.dao.CustomerRepository;
import com.eazybytes.domain.customer.domain.Customer;
import com.eazybytes.domain.customer.dto.request.RegisterCustomerRequest;
import com.eazybytes.domain.customer.dto.response.CustomerResponse;
import com.eazybytes.global.util.CustomerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerUtil customerUtil;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> registerUser(RegisterCustomerRequest request) {
        try {
            String hashPwd = passwordEncoder.encode(request.pwd());
            Customer customer = Customer.createCustomer(
                    request.name(),
                    request.email(),
                    request.mobileNumber(),
                    hashPwd,
                    request.role());

            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() > 0) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("User registration failed");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to " + e.getMessage());
        }
    }

    public CustomerResponse getUserDetailsAfterLogin() {
        Customer customer = customerUtil.getCurrentCustomer();

        return CustomerResponse.from(customer);
    }
}
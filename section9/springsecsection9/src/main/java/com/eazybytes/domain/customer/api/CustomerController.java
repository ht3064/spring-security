package com.eazybytes.domain.customer.api;

import com.eazybytes.domain.customer.application.CustomerService;
import com.eazybytes.domain.customer.dto.request.RegisterCustomerRequest;
import com.eazybytes.domain.customer.dto.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterCustomerRequest request) {
        return customerService.registerUser(request);
    }

    @RequestMapping("/user")
    public CustomerResponse getUserDetailsAfterLogin() {
        return customerService.getUserDetailsAfterLogin();
    }
}
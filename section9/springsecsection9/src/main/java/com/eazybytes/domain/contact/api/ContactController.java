package com.eazybytes.domain.contact.api;

import com.eazybytes.domain.contact.application.ContactService;
import com.eazybytes.domain.contact.dto.request.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    public ResponseEntity<Void> saveContactInquiryDetails(@RequestBody ContactRequest request) {
        contactService.saveContactInquiryDetails(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
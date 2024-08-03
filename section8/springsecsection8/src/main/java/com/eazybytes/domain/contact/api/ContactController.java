package com.eazybytes.domain.contact.api;

import com.eazybytes.domain.contact.application.ContactService;
import com.eazybytes.domain.contact.dto.request.ContactRequest;
import com.eazybytes.domain.contact.dto.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    public ContactResponse saveContactInquiryDetails(@RequestBody ContactRequest request) {
        return contactService.saveContactInquiryDetails(request);
    }
}
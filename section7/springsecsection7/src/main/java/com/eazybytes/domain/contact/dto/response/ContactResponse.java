package com.eazybytes.domain.contact.dto.response;

import com.eazybytes.domain.contact.domain.Contact;

public record ContactResponse(
        String contactName,
        String contactEmail,
        String subject,
        String message) {
    public static ContactResponse from(Contact contact) {
        return new ContactResponse(
                contact.getContactName(),
                contact.getContactEmail(),
                contact.getSubject(),
                contact.getMessage());
    }
}
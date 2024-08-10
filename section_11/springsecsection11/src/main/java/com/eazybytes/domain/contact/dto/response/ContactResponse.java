package com.eazybytes.domain.contact.dto.response;

import com.eazybytes.domain.contact.domain.Contact;

import java.time.LocalDateTime;

public record ContactResponse(
        String id,
        String contactName,
        String contactEmail,
        String subject,
        String message,
        LocalDateTime createDt) {
    public static ContactResponse from(Contact contact) {
        return new ContactResponse(
                contact.getId(),
                contact.getContactName(),
                contact.getContactEmail(),
                contact.getSubject(),
                contact.getMessage(),
                contact.getCreateDt());
    }
}
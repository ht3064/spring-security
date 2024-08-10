package com.eazybytes.domain.contact.dto.request;

public record ContactRequest(
        String id,
        String contactName,
        String contactEmail,
        String subject,
        String message) {
}
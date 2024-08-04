package com.eazybytes.domain.contact.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contact {

    @Id
    @Column(name = "contact_id")
    private String id;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

    private LocalDateTime createDt;

    @Builder(access = AccessLevel.PRIVATE)
    private Contact(
            String id,
            String contactName,
            String contactEmail,
            String subject,
            String message,
            LocalDateTime createDt) {
        this.id = id;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.subject = subject;
        this.message = message;
        this.createDt = createDt;
    }

    public static Contact createContact(
            String id,
            String contactName,
            String contactEmail,
            String subject,
            String message) {
        return Contact.builder()
                .id(id)
                .contactName(contactName)
                .contactEmail(contactEmail)
                .subject(subject)
                .message(message)
                .createDt(LocalDateTime.now())
                .build();
    }
}
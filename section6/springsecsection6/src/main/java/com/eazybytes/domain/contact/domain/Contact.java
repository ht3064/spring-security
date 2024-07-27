package com.eazybytes.domain.contact.domain;

import com.eazybytes.domain.common.model.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contact extends BaseTimeEntity {

    @Id
    @Column(name = "contact_id")
    private String id;

    private String contactName;

    private String contactEmail;

    private String subject;

    private String message;

    @Builder(access = AccessLevel.PRIVATE)
    private Contact(
            String id, String contactName, String contactEmail, String subject, String message) {
        this.id = id;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.subject = subject;
        this.message = message;
    }

    public static Contact createContact(
            String id, String contactName, String contactEmail, String subject, String message) {
        return Contact.builder()
                .id(id)
                .contactName(contactName)
                .contactEmail(contactEmail)
                .subject(subject)
                .message(message)
                .build();
    }
}
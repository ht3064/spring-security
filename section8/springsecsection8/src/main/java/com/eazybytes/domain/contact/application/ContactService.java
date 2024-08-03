package com.eazybytes.domain.contact.application;

import com.eazybytes.domain.contact.dao.ContactRepository;
import com.eazybytes.domain.contact.domain.Contact;
import com.eazybytes.domain.contact.dto.request.ContactRequest;
import com.eazybytes.domain.contact.dto.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static com.eazybytes.domain.contact.domain.Contact.createContact;

@Transactional
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactResponse saveContactInquiryDetails(ContactRequest request) {
        Contact contact = createContact(
                getServiceReqNumber(),
                request.contactName(),
                request.contactEmail(),
                request.subject(),
                request.message());

        contactRepository.save(contact);

        return ContactResponse.from(contact);
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}

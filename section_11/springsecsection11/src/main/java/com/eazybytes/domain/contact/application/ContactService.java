package com.eazybytes.domain.contact.application;

import com.eazybytes.domain.contact.dao.ContactRepository;
import com.eazybytes.domain.contact.domain.Contact;
import com.eazybytes.domain.contact.dto.request.ContactRequest;
import com.eazybytes.domain.contact.dto.response.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.eazybytes.domain.contact.domain.Contact.createContact;

@Transactional
@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

//    @PreFilter("filterObject.contactName != 'Test'")
    @PostFilter("filterObject.contactName != 'Test'")
    public List<ContactResponse> saveContactInquiryDetails(List<ContactRequest> request) {
        ContactRequest contactRequest = request.get(0);

        Contact contact = createContact(
                getServiceReqNumber(),
                contactRequest.contactName(),
                contactRequest.contactEmail(),
                contactRequest.subject(),
                contactRequest.message());

        ContactResponse contactResponse = ContactResponse.from(contactRepository.save(contact));
        List<ContactResponse> returnContacts = new ArrayList<>();
        returnContacts.add(contactResponse);

        return returnContacts;
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}

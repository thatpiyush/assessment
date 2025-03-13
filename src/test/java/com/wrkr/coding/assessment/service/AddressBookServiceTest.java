package com.wrkr.coding.assessment.service;

import com.wrkr.coding.assessment.model.AddressBook;
import com.wrkr.coding.assessment.model.Contact;
import com.wrkr.coding.assessment.repository.AddressBookRepository;
import com.wrkr.coding.assessment.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressBookServiceTest {

    @Mock
    private AddressBookRepository addressBookRepository;

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private AddressBookService addressBookService;

    private Contact contact;
    private AddressBook addressBook;

    @BeforeEach
    void setUp() {
        contact = new Contact();
        contact.setName("Piyush");
        contact.setContactNumber("1234567890");

        addressBook = new AddressBook();
        addressBook.setName("Friends");
        addressBook.setContacts(new ArrayList<>());
    }

    @Test
    void testCreateAddressBook_Success() {
        when(addressBookRepository.findByName("Friends")).thenReturn(Optional.empty());
        when(contactRepository.saveAll(anyList())).thenReturn(Collections.singletonList(contact));
        when(addressBookRepository.save(any(AddressBook.class))).thenReturn(addressBook);

        AddressBook result = addressBookService.createAddressBook("Friends", Collections.singletonList(contact));

        assertNotNull(result);
        assertEquals("Friends", result.getName());
    }

    @Test
    void testCreateAddressBook_AlreadyExists() {
        when(addressBookRepository.findByName("Friends")).thenReturn(Optional.of(addressBook));

        assertThrows(ResponseStatusException.class, () -> addressBookService.createAddressBook("Friends", Collections.singletonList(contact)));
    }

    @Test
    void testSaveContact_NewAddressBook() {
        when(addressBookRepository.findByName("Friends")).thenReturn(Optional.empty());
        when(addressBookRepository.save(any(AddressBook.class))).thenReturn(addressBook);
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        AddressBook result = addressBookService.saveContact(contact, "Friends");

        assertNotNull(result);
        assertEquals("Friends", result.getName());
        assertTrue(result.getContacts().contains(contact));
    }

    @Test
    void testGetContactsByAddressBook_NotFound() {
        when(addressBookRepository.findByName("NonExistent")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> addressBookService.getContactsByAddressBook("NonExistent"));
    }

    @Test
    void testGetUniqueContacts() {
        Contact contact1 = new Contact();
        contact1.setName("Alice");

        Contact contact2 = new Contact();
        contact2.setName("Bob");

        Contact duplicateContact = new Contact();
        duplicateContact.setName("Alice");

        List<Contact> contacts = Arrays.asList(contact1, contact2, duplicateContact);
        when(contactRepository.findAll()).thenReturn(contacts);

        List<Contact> uniqueContacts = addressBookService.getUniqueContacts();

        assertEquals(1, uniqueContacts.size());
        assertEquals("Bob", uniqueContacts.get(0).getName());
    }
}

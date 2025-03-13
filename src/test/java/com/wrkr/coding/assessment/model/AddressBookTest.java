package com.wrkr.coding.assessment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    @InjectMocks
    private AddressBook addressBook;

    @Mock
    private Contact contact;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addressBook = new AddressBook();
    }

    @Test
    void testContactsInitialized() {
        assertNotNull(addressBook.getContacts(), "Contacts should be initialized to an empty list.");
    }

    @Test
    void testAddContact() {
        addressBook.getContacts().add(contact);
        assertTrue(addressBook.getContacts().contains(contact), "Contact should be added to the contacts list.");
    }

    @Test
    void testContactsListIsNotNull() {
        assertNotNull(addressBook.getContacts(), "Contacts list should not be null.");
    }

    @Test
    void testAddressBookName() {
        addressBook.setName("My Address Book");
        assertEquals("My Address Book", addressBook.getName(), "The name of the address book should be set correctly.");
    }

    @Test
    void testAddressBookId() {
        addressBook.setId(1L);
        assertEquals(1L, addressBook.getId(), "The ID of the address book should be set correctly.");
    }

    @Test
    void testAddressBookContactsNotEmpty() {
        Contact contact1 = new Contact();
        addressBook.getContacts().add(contact1);
        assertFalse(addressBook.getContacts().isEmpty(), "Contacts list should not be empty after adding a contact.");
    }

    @Test
    void testAddressBookContactsEmptyOnInitialization() {
        assertTrue(addressBook.getContacts().isEmpty(), "Contacts list should be empty initially.");
    }
}


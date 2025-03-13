package com.wrkr.coding.assessment.service;

import com.wrkr.coding.assessment.model.AddressBook;
import com.wrkr.coding.assessment.model.Contact;
import com.wrkr.coding.assessment.repository.AddressBookRepository;
import com.wrkr.coding.assessment.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ContactRepository contactRepository;

    // Create a new address book with a list of contacts
    public AddressBook createAddressBook(String name, List<Contact> contacts) {
        if (addressBookRepository.findByName(name).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Address book with name '" + name + "' already exists");
        }

        AddressBook addressBook = new AddressBook();
        addressBook.setName(name);

        List<Contact> savedContacts = contactRepository.saveAll(contacts);

        addressBook.setContacts(savedContacts);

        return addressBookRepository.save(addressBook);
    }

    // Save a contact to a specific address book (or default to "Uncategorized")
    public AddressBook saveContact(Contact contact, String addressBookName) {

        String bookName = (addressBookName == null || addressBookName.isEmpty()) ? "Uncategorized" : addressBookName;

        AddressBook addressBook = addressBookRepository.findByName(bookName).orElseGet(() -> {
            AddressBook newAddressBook = new AddressBook();
            newAddressBook.setName(bookName); // Use the effectively final variable
            return addressBookRepository.save(newAddressBook);
        });

        contactRepository.save(contact);

        addressBook.getContacts().add(contact);
        return addressBookRepository.save(addressBook);

    }

    public List<Contact> getContactsByAddressBook(String addressBookName) {
        return addressBookRepository.findByName(addressBookName)
                .map(addressBook -> addressBook.getContacts()
                        .stream()
                        .sorted(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER))
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("AddressBook not found"));
    }

    // Get unique contacts across all address books using Stream API
    public List<Contact> getUniqueContacts() {
        List<Contact> allContacts = contactRepository.findAll();

        // Group contacts by name (case-insensitive) and count their frequency
        Map<String, Long> contactNameFrequency = allContacts.stream().map(contact -> contact.getName().toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Filter contacts that appear only once
        return allContacts.stream().filter(contact -> contactNameFrequency.get(contact.getName().toLowerCase()) == 1).collect(Collectors.toList());
    }
}

package com.wrkr.coding.assessment.controller;

import com.wrkr.coding.assessment.dto.AddressBookRequest;
import com.wrkr.coding.assessment.model.AddressBook;
import com.wrkr.coding.assessment.model.Contact;
import com.wrkr.coding.assessment.service.AddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressbooks")
@Validated
@Tag(name = "Address Book APIs")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @Operation(description = "Create a new address book with a list of contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Created"),
            @ApiResponse(responseCode = "409", description = "Duplicate Address Book")
    })
    @PostMapping("/create")
    public ResponseEntity<AddressBook> createAddressBook(@RequestBody @Valid AddressBookRequest request) {
        AddressBook addressBook = addressBookService.createAddressBook(request.getName(), request.getContacts());
        return ResponseEntity.ok(addressBook);
    }

    @Operation(description = "Add a contact to an address book")
    @PostMapping("/add-contact")
    public ResponseEntity<AddressBook> addContactToAddressBook(@RequestParam(required = false) String addressBookName, @Valid @RequestBody Contact contact) {
        AddressBook addressBook = addressBookService.saveContact(contact, addressBookName);
        return ResponseEntity.ok(addressBook);
    }

    @Operation(description = "Get all contacts for a specific address book in alphabetical order")
    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContactsByAddressBook(@RequestParam(required = false, defaultValue = "Uncategorized") String addressBookName) {
        List<Contact> contacts = addressBookService.getContactsByAddressBook(addressBookName);
        return ResponseEntity.ok(contacts);
    }


    @Operation(description = "Get unique contacts across all address books")
    @GetMapping("/unique-contacts")
    public ResponseEntity<List<Contact>> getUniqueContacts() {
        List<Contact> uniqueContacts = addressBookService.getUniqueContacts();
        return ResponseEntity.ok(uniqueContacts);
    }
}
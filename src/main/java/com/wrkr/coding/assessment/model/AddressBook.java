package com.wrkr.coding.assessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "ID", example = "1", hidden = true)
    @JsonIgnore
    private Long id;

    private String name; // Name of the address book

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Contact> contacts; // List of contacts in the address book

    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        return contacts;
    }
}